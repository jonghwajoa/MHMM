class Userinfo {
  constructor() {
    const uploadBtn = document.getElementById('photo-upload-btn');
    const photoImg = document.getElementById('user-phto');
    const savePhotoBtn = document.getElementById('photo-save-btn');

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
          await ajaxUtil.saveFileAjax(`/api/mypage/photo`, uploadBtn.files[0]);
        } catch (e) {
          alert(e);
          return;
        }
      }
    });
  }

  saveUserPhoto() {}
}

new Userinfo();
