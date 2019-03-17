class ChatRoom {
  constructor() {
    this.name = document.getElementById('nickName');
    this.message = document.getElementById('chat-content');
    this.submit = document.getElementById('chat-send');
    this.eventInit();
  }

  eventInit() {
    let sock = new SockJS('/ws/chat');

    sock.onopen = function() {
      sock.send(JSON.stringify({ chatRoomId: roomId, type: 'JOIN', writer: member }));
      sock.onmessage = function(e) {
        var content = JSON.parse(e.data);
        chatBox.append('<li>' + content.message + '(' + content.writer + ')</li>');
      };
    };

    this.submit.addEventListener('click', e => {
      sock.send(thos.message.value);
      this.message.value = '';
    });

  }
}

new ChatRoom();
