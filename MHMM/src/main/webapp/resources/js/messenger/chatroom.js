class ChatRoomList {
  constructor() {
    const roomNo = document.getElementById('roomNo').value;
    const userId = document.getElementById('userId').value;
    const userNo = document.getElementById('userNo').value;
    this.messageScreen = document.getElementById('chat-view-area');

    this.getChatData(roomNo).then(data => {
      this.initDraw(JSON.parse(data).reverse(), userNo);
    });
    this.stompInit(roomNo, userNo);
    this.eventInit(roomNo, userId, userNo);
  }

  async getChatData(roomNo) {
    const predicate = status => status === 200;
    try {
      return await ajaxUtil.sendGetAjax(`/api/message/onetoone/${roomNo}`, predicate);
    } catch (e) {
      alert(e.message);
      window.close();
      return;
    }
  }

  initDraw(data, userNo) {
    for (let message of data) {
      this.viewDraw(message, userNo);
    }
  }

  viewDraw(message, userNo) {
    if (message.user_no == userNo) {
      this.messageScreen.innerHTML += `<li class="myMessage"><span class="message">${message.message}</span></li>`;
    } else {
      this.messageScreen.innerHTML += `<li class="theOtherMessage"><div class='writer'>${
        message.user_name
      }</div> <span class="message">${message.message}</span></li>`;
    }
    this.messageScreen.scrollTop = this.messageScreen.scrollHeight;
  }

  stompInit(roomNo, userNo) {
    const sock = new SockJS('/stomp-chat');
    const client = Stomp.over(sock);

    client.connect({}, () => {
      client.subscribe('/topic/chatroom.' + roomNo, chat => this.viewDraw(JSON.parse(chat.body), userNo));
    });

    this.client = client;
  }

  eventInit(roomNo, userId, userNo) {
    const message = document.getElementById('chat-content');
    const submit = document.getElementById('chat-send');

    submit.addEventListener('click', e => {
      if (message.value.trim() === '') {
        message.value = '';
        return;
      }

      this.client.send(
        '/publish/api/message',
        {},
        JSON.stringify({
          chatroom_no: roomNo,
          type: 'CHAT',
          message: message.value,
          user_name: userId,
          user_no: userNo
        })
      );
      message.value = '';
    });

    message.addEventListener('keypress', e => {
      const key = e.which || e.keyCode;
      if (key === 13) {
        e.preventDefault();
        submit.click();
      }
    });
  }
}

new ChatRoomList();
