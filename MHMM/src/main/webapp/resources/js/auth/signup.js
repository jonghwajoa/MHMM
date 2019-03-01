class Signup {
  constructor() {
    this.userId = document.getElementById('id');
    this.userPw = document.getElementById('pw');
    this.userPwCheck = document.getElementById('pwCheck');
    this.userEmail = document.getElementById('email');
    this.userName = document.getElementById('name');
    this.submitBtn = document.getElementById('signup-submit-btn');

    this.userIdMsg = document.getElementById('userId-Message');
    this.eventInit();
  }

  eventInit() {
    this.submitBtn.addEventListener('click', async e => {
      e.preventDefault();
      
      let lengthCheck = this.lengthCheck;

      if (!lengthCheck(this.userId.value, 5, 20)) {
        alert('아이디는 최소 5자리 최대 20자리 입니다.');
        return;
      } else if (!lengthCheck(this.userPw.value, 5, 20)) {
        alert('비밀번호는 최소 5자리 최대 20자리 입니다.');
        return;
      } else if (!lengthCheck(this.userEmail.value, 5, 50)) {
        alert('이메일은 최소 5자리 이상 50자리 미만입니다.');
        return;
      } else if (!lengthCheck(this.userName.value, 2, 10)) {
        alert('이름은 최소 2글자 최대 10글자 입니다.');
        return;
      }

      if (this.userPw.value != this.userPwCheck.value) {
        alert('비밀번호가 일치하지 않습니다.');
        return;
      }

      if (this.userEmail.value.split('@').length !== 2) {
        alert('이메일 형식이 올바르지 않습니다.');
        return;
      }

      const params = {
        id: this.userId.value,
        pw: this.userPw.value,
        pwCheck: this.userPwCheck.value,
        email: this.userEmail.value,
        name: this.userName.value
      };

      let ajaxResult;
      try {
        ajaxResult = await ajaxUtil.sendPostAjax('/auth/signup', params);
      } catch (e) {
    	  let message = "";
    	  
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
      
      alert("회원가입 성공")
      window.location.href = '/auth/login';
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

new Signup();
