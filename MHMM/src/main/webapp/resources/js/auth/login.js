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
        pw: this.pw.value
      };

      let ajaxResult;
      const predicate = status => status === 200;
      try {
        ajaxResult = await ajaxUtil.sendPostAjax('/api/auth/login', params, predicate);
      } catch (e) {
        alert(e.message);
        return;
      }

      window.location.href = '/';
    });

    this.pw.addEventListener('keypress', e => {
      let key = e.which || e.keyCode;
      if (key === 13) {
        this.loginSubmitBtn.click();
      }
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
