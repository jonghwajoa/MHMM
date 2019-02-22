      let signupBtn = document.getElementById('signup-btn');
      let signupCancleBtn = document.getElementById('signup-cancle');

      signupBtn.addEventListener('click', () => {
        $('.login-wrap section.login').animate({ right: '-5000px' }, 600);
        $('.signup-wrap section.signup').animate({ left: '0px' }, 600);
      });

      signupCancleBtn.addEventListener('click', () => {
        $('.signup-wrap section.signup').animate({ left: '-5000px' }, 600);
        $('.login-wrap section.login').animate({ right: '0px' }, 600);
      });