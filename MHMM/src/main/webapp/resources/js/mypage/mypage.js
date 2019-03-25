class MyPage {
  constructor() {
    this.curPw = document.getElementById('curPW');
    this.changePw = document.getElementById('changePw');
    this.changePwCheck = document.getElementById('changePwCheck');
    this.submitBtn = document.getElementById('change-pw-submit-btn');
  }

  eventInit() {
    const predicate = status => status === 204;
    this.submitBtn.addEventListener('click', async () => {
      let params = {
        curPW: this.curPw.value,
        changePw: this.changePw.value,
        changePwCheck: this.changePwCheck
      };

      try {
        await ajaxUtil.sendPathAjax(`/api/mypage/password`, params, predicate);
      } catch (e) {
        alert(e.message);
        return;
      }

      alert('비밀번호 변경 완료');
      window.location.href = '/';
    });
  }
}

new MyPage();
