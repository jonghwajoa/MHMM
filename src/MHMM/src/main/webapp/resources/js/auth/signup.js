class Signup {
  constructor() {
    this.userId = document.getElementById('userId');
    this.userPw = document.getElementById('userPw');
    this.userPwCheck = document.getElementById('userPwCheck');
    this.userEmail = document.getElementById('userEmail');
    this.userName = document.getElementById('userName');
    this.submitBtn = document.getElementById('signup-submit-btn');

    this.userIdMsg = document.getElementById('userId-Message');
    this.eventInit();
  }

  eventInit() {
    this.submitBtn.addEventListener('click', e => {
      e.preventDefault();
      console.log(this.userPw.value);
      console.log(this.userPwCheck.value);
      if (this.userPw.value !== this.userPwCheck.value) {
        alert('비밀번호가 일치하지 않습니다.');
        return;
      }

/*      let ajaxResult;
      try {
          ajaxResult =await ajaxUtil.sendPostAjax('/auth/signup')
      } catch(e) {
          console.log(e);
      }*/

      console.log(ajaxResult);
    });
  }
}

new Signup();
