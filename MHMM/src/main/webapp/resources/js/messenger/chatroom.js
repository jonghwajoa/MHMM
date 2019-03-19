class ChatRoom {
  constructor() {
    this.messangeSection = document.getElementById('chat-room');

    this.getAllChatRoom().then(result => {
      console.log(result);
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
}

new ChatRoom();
