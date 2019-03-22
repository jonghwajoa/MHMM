class ChatRoomList {
  constructor() {
    const roomNo = document.getElementById('roomNo').value;
    const userId = document.getElementById('userId').value;
    this.stompInit(roomNo, userId);
    this.eventInit(roomNo, userId);
  }

  stompInit(roomNo, userId) {
    const messageScreen = document.getElementById('chat-view-area');

    const sock = new SockJS('/stomp-chat');
    const client = Stomp.over(sock);

    client.debug = function(e) {};

    client.connect({}, function() {
      client.send('/publish/chat/join', {}, JSON.stringify({ chatRoomId: roomNo, type: 'JOIN', writer: userId }));

      client.subscribe('/subscribe/chat/room/' + roomNo, function(chat) {
        let content = JSON.parse(chat.body);

        if (content.writer === userId) {
          messageScreen.innerHTML += `<li class="myMessage"><span class="message">${content.message}</span></li>`;
        } else {
          messageScreen.innerHTML += `<li class="theOtherMessage"><div class='writer'>${
            content.writer
          }</div> <span class="message">${content.message}</span></li>`;
        }
      });
    });

    this.client = client;
  }

  eventInit(roomNo, userId) {
    const message = document.getElementById('chat-content');
    const submit = document.getElementById('chat-send');

    submit.addEventListener('click', e => {
      if (message.value.trim() === '') {
    	  message.value = ''
        return;
      }

      this.client.send(
        '/publish/chat/message',
        {},
        JSON.stringify({ chatRoomId: roomNo, type: 'CHAT', message: message.value, writer: userId })
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
