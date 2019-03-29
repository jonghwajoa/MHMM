class ChatRoomList {
  constructor() {
    this.messangeSection = document.getElementById('chatroom-section');
    this.userId = document.getElementById('userid');

    this.getAllChatRoom().then(chatRoomData => {
      console.log(chatRoomData);
      this.drawInit(chatRoomData);
    });
  }

  async getAllChatRoom() {
    let getAllChatRoomResult;
    const predicate = status => status === 200;
    try {
      getAllChatRoomResult = await ajaxUtil.sendGetAjax('/api/messenger/chatroom/', predicate);
    } catch (e) {
      alert(e.message);
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
        span.innerHTML = `${e.from_user_name}님과 채팅`;
      } else {
        span.innerHTML = `${e.to_user_name}님과 채팅`;
      }
      divRoom.onclick = () => this.chatRoomOnclick(e.no);
      divRoom.appendChild(img);
      divRoom.appendChild(span);
      messangeSection.appendChild(divRoom);
    }
  }

  chatRoomOnclick(roomNo) {
    window.open(`/messenger/chatroom/${roomNo}`,"_blank",'width=600, height=800',false);
  }
}

new ChatRoomList();
