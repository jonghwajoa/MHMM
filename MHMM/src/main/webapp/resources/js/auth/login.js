class login {
  constructor() {
    this.loginSubmitBtn = document.getElementById('login-submit-btn');
    this.id = document.getElementById('id');
    this.pw = document.getElementById('pw');
    this.eventInit();
  }

  eventInit() {
    this.loginSubmitBtn.addEventListener('click', async e => {
      e.preventDefault();
      let lengthCheck = this.lengthCheck;
      if (!lengthCheck(this.id.value, 5, 20)) {
        alert('아이디는 최소 5자리 최대 20자리 입니다.');
        return;
      } else if (!lengthCheck(this.pw.value, 5, 20)) {
        alert('비밀번호는 최소 5자리 최대 20자리 입니다.');
        return;
      }

      const params = {
        id: this.id.value,
        pw: this.pw.value,
      };

      let ajaxResult;
      try {
        ajaxResult = await ajaxUtil.sendPostAjax('/auth/login', params);
      } catch (e) {
    	  console.log(e);
        let message = '';
        let err = JSON.parse(e.message);
        message += err.message;
        if(err.errors != null) {
        	for (let error of err.errors) {
                message += `\n${error.reason}`;
              }	
        }
        alert(message);
        return;
      }

      window.location.href = '/';
    });
  }

  lengthCheck(target, min, max = Number.MAX_VALUE) {
    if (min > max) {
      [min, max] = [max, min];
    }

    const targetLen = target.length;
    if (targetLen < min || targetLen > max) {
      return false;
    }

    return true;
  }
}

new login();
