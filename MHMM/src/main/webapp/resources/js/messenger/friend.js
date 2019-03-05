class Friend {
  constructor() {
    this.addInput = document.getElementById('friend-add');
    this.addInputBtn = document.getElementById('friend-add-btn');
    this.searchResult = document.getElementById('friend-search-result');
    this.eventInit();
  }

  eventInit() {
    this.addInputBtn.addEventListener('click', () => {
      this.searchResult.style.display = 'block';
      this.searchFriend(this.addInput.value);
    });

    this.addInput.addEventListener('keypress', e => {
      let key = e.which || e.keyCode;
      if (key === 13) {
        this.searchResult.style.display = 'block';
        this.searchFriend(this.addInput.value);
      }
    });
  }

  searchFriend(name) {
    // sendGetAjax("/api/auth/")

    this.drawPost(name, 'www.mhmm.xyz');
  }

  drawPost(name, imgUrl) {
    let html = '';

    html += `<img alt=${imgUrl} />`;
    html += `<span class="friend-name">${name}</span>`;
    html += `<input type="button" value="친구등록" />`;

    this.searchResult.innerHTML = html;
  }
}

new Friend();
