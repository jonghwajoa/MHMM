class ChatRoom {
  constructor() {
    this.messangeSection = document.getElementById('chatroom-section');
    this.userId = document.getElementById('userid');

    this.getAllChatRoom().then(chatRoomData => {
      this.drawInit(chatRoomData);
    });
  }

  async getAllChatRoom() {
    let getAllChatRoomResult;
    try {
      getAllChatRoomResult = await ajaxUtil.sendGetAjax('/api/messenger/chatroom/');
    } catch (e) {
      alert(e.messege);
      return;
    }
    return JSON.parse(getAllChatRoomResult);
  }

  drawInit(roomData) {
    const messangeSection = this.messangeSection;

    for (let e of roomData) {
      const divRoom = document.createElement('div');
      const img = document.createElement('img');
      const span = document.createElement('span');
      img.alt = '사진';

      divRoom.className = 'chat-room';

      if (this.userId === e.from_user_id) {
        span.innerHTML = `${e.from_user_id}님과 채팅`;
      } else {
        span.innerHTML = `${e.to_user_id}님과 채팅`;
      }
      divRoom.onclick = () => this.chatRoomOnclick(e.no);
      divRoom.appendChild(img);
      divRoom.appendChild(span);
      messangeSection.appendChild(divRoom);
    }
  }

  chatRoomOnclick(roomNo) {
    window.open(`/messenger/chatroom/${roomNo}`, 'ChatRoom', 'width=800, height=700');
  }
}

new ChatRoom();
