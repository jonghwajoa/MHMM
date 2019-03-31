class Userinfo {
  constructor() {
    const uploadBtn = document.getElementById('photo-upload-btn');
    const photoImg = document.getElementById('user-photo');
    const savePhotoBtn = document.getElementById('photo-save-btn');

    this.getData().then(result => {
      photoImg.src = `/img/userPhoto/${JSON.parse(result).fileFullName}`;
    });

    uploadBtn.addEventListener('change', event => {
      const target = event.target;
      if (target.files && target.files[0]) {
        const reader = new FileReader();
        reader.onload = function(e) {
          photoImg.setAttribute('src', e.target.result);
        };
        reader.readAsDataURL(target.files[0]);
      }
    });

    savePhotoBtn.addEventListener('click', async () => {
      if (uploadBtn.value === '') {
        alert('사진을 업로드 해주세요..');
        return;
      }

      if (confirm('사진을 저장하시겠습니까? ')) {
        try {
          const result = await ajaxUtil.saveFileAjax(`/api/mypage/photo`, uploadBtn.files[0]);
          photoImg.src = `/img/userPhoto/${JSON.parse(result).fileFullName}`;
        } catch (e) {
          alert(e.message);
          return;
        }
        alert('사진 변경 완료');
      }
    });
  }

  async getData() {
    const predicate = status => status === 200;
    try {
      return await ajaxUtil.sendGetAjax('/api/mypage/', predicate);
    } catch (e) {
      alert(e.message);
      return;
    }
  }
}

new Userinfo();
