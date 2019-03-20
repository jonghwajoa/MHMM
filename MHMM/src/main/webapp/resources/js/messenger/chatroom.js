class ChatRoom {
  constructor() {
    this.messangeSection = document.getElementById('chatroom-section');

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
      let divRoom = document.createElement('div');
      let img = document.createElement('img');
      img.alt = '사진';

      divRoom.className = 'chat-room';
      divRoom.innerText = `${e.from_user_id} ${e.to_user_id}님과 채팅`;
      divRoom.onclick = () => this.chatRoomOnclick(e.no);
      divRoom.appendChild(img);
      messangeSection.appendChild(divRoom);
    }
  }

  chatRoomOnclick(roomNo) {
    window.open(`/messenger/chatroom/${roomNo}`, 'ChatRoom', 'width=800, height=700');
  }
}

new ChatRoom();
