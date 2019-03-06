class Friend {
  constructor() {
    this.addInput = document.getElementById('friend-add');
    this.addInputBtn = document.getElementById('friend-add-btn');
    this.searchResult = document.getElementById('friend-search-result');
    this.eventInit();
  }

  eventInit() {
    this.addInputBtn.addEventListener('click', () => {
      this.searchFriend(this.addInput.value);
    });

    this.addInput.addEventListener('keypress', e => {
      let key = e.which || e.keyCode;
      if (key === 13) {
        this.searchFriend(this.addInput.value);
      }
    });
  }

  async searchFriend(id) {
	  let result = false;
	  this.searchResult.style.display = 'none';
	  try {
		  result = await ajaxUtil.sendGetAjax(`/api/auth/search?id=${id}`);
	  } catch (e) {
		  let message = '';
		  if(e.status === 404) {
			  message = '존재하지 않는 아이디 입니다.'
		  }else {
			  let err = JSON.parse(e.message);
		        if(err.errors != null) {
		        	for (let error of err.errors) {
		                message += `\n${error.reason}`;
		              }	
		        }
		  }
		  this.drawPost(false, message);
		  return;
	  }
	  
	  console.log(result)
	  console.log(result);
	  this.drawPost(id, 'url');
  }
  
  
  drawPost(name, imgUrl) {
    let html = '';
    
    if(!name) {
        html += imgUrl;	
    } else {
    	html += `<img alt=${imgUrl} />`;
        html += `<span class="friend-name">${name}</span>`;
        html += `<input type="button" value="친구등록" />`;
    }
    this.searchResult.innerHTML = html;
	  this.searchResult.style.display = 'block';
  }
}

new Friend();
