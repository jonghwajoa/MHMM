class Friend {
  constructor() {
    this.searchInput = document.getElementById('friend-search');
    this.searchInputBtn = document.getElementById('friend-search-btn');
    this.searchResult = document.getElementById('friend-search-result');
    this.friendList = document.getElementById('friend-list');

    this.getAllFriend();

    this.eventInit();
  }

  async getAllFriend() {
    let getAllFriendResult;
    const predicate = status => status === 200;
    try {
      getAllFriendResult = await ajaxUtil.sendGetAjax('/api/friend/', predicate);
    } catch (e) {
      alert(e.message);
      return;
    }
    this.allFriendData = JSON.parse(getAllFriendResult);
    this.drawFriend();
  }

  eventInit() {
    this.searchInputBtn.addEventListener('click', () => {
      this.searchFriend(this.searchInput.value);
    });

    this.searchInput.addEventListener('keypress', e => {
      let key = e.which || e.keyCode;
      if (key === 13) {
        this.searchFriend(this.searchInput.value);
      }
    });
  }

  async searchFriend(id) {
    let result = false;
    this.searchResult.style.display = 'none';
    const predicate = status => status === 200;
    try {
      result = await ajaxUtil.sendGetAjax(`/api/friend/${id}`, predicate);
    } catch (e) {
      this.drawSearchResult(false, e.message);
      return;
    }

    let searchResultParse = JSON.parse(result);
    if (this.isAlreadyFriend(searchResultParse.no)) {
      this.drawSearchResult(id, searchResultParse.photo);
    } else {
      this.drawSearchResult(false, '이미 등록된 친구 입니다.');
    }
  }

  isAlreadyFriend(no) {
    let data = this.allFriendData;

    for (let e of data) {
      if (e.no === no) {
        return false;
      }
    }
    return true;
  }

  drawFriend() {
    let friendData = this.allFriendData;
    friendData = friendData.sort((a, b) => a.name.localeCompare(b.name));
    this.friendList.innerHTML = '';

    for (let e of friendData) {
      let friendClass = document.createElement('div');
      let img = document.createElement('img');
      let friendName = document.createElement('span');
      let chatBtn = document.createElement('input');

      friendClass.className = 'friend';
      img.className = 'img';
      img.src = e.photo != null ? `/img/userPhoto/${e.photo}` : '/img/userPhoto/default.jpg';
      friendName.innerText = e.name;
      friendName.className = 'friend-name';
      chatBtn.type = 'button';
      chatBtn.value = '채팅하기';
      chatBtn.className = 'chatBtn';
      chatBtn.addEventListener('click', event => {
        this.chatEvent(e.no);
      });
      friendClass.appendChild(img);
      friendClass.appendChild(friendName);
      friendClass.appendChild(chatBtn);

      this.friendList.appendChild(friendClass);
    }
  }

  async chatEvent(userNo) {
    let createOrFind;
    const predicate = status => status === 201;
    try {
      createOrFind = await ajaxUtil.sendPostAjax('/api/messenger/chatroom/', { to_userno: userNo }, predicate);
    } catch (e) {
      alert(e.message);
      return;
    }

    window.open(`/messenger/chatroom/${JSON.parse(createOrFind).no}`, 'ChatRoom', 'width=800, height=700');
  }

  drawSearchResult(name, imgUrl) {
    let html = '';

    if (!name) {
      html += imgUrl;
    } else {
      html += `<img alt="프로필 사진" src=${
        imgUrl != null ? `/img/userPhoto/${imgUrl}` : '/img/userPhoto/default.jpg'
      }/>`;
      html += `<span class="friend-name">${name}</span>`;
      html += `<input type="button" value="친구등록" id="friendAddBtn" />`;
    }
    this.searchResult.innerHTML = html;

    if (name) {
      this.friendAddBtn = document.getElementById('friendAddBtn');
      this.friendAddBtn.addEventListener('click', () => {
        this.addFriend(name);
      });
    }

    this.searchResult.style.display = 'block';
  }

  async addFriend(name) {
    let param = { id: name };
    let result;
    const predicate = status => status === 201;
    try {
      result = await ajaxUtil.sendPostAjax(`/api/friend/`, param, predicate);
    } catch (e) {
      this.drawSearchResult(false, e.message);
      return;
    }
    this.drawSearchResult(false, '등록성공');

    this.allFriendData.push(JSON.parse(result));
    this.drawFriend();
  }

  errorParse(e) {
    let message = '';
    let err = JSON.parse(e.message);
    if (err.errors != null) {
      for (let error of err.errors) {
        message += `\n${error.reason}`;
      }
    }
    return message;
  }
}

new Friend();
