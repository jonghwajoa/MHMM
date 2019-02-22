<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MHMM</title>
<link href="/css/common/reset.css" rel="stylesheet" type="text/css" />
<link href="/css/common/nav.css" rel="stylesheet" type="text/css" />
<link href="/css/auth/login.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<%@include file="../partitial/nav.jsp" %>

    <div class="login-wrap">
      <section class="login">
        <h3>로그인 정보</h3>
        <form action="/auth/login" method="POST">
          <label for="userId" class="ir_su">아이디</label>
          <input
            type="input"
            id="userId"
            name="userId"
            class="input_text"
            placeholder="아이디를 입력해주세요"
            autocomplete="off"
            maxlength="20"
          />
          <label for="userPw" class="ir_su">비밀번호</label>
          <input
            type="password"
            id="userPw"
            name="userPw"
            class="input_text"
            placeholder="비밀번호를 입력해주세요"
            autocomplete="off"
            maxlength="20"
          />
          <input type="submit" value="로그인" />
          <input type="button" id="signup-btn"" value="회원가입" />
          <input type="button" value="홈으로" onClick="location.href='/'" />
        </form>
      </section>
    </div>

    <div class="signup-wrap">
      <section class="signup">
        <h3>회원가입</h3>
        <form action="/auth/signup" method="POST">
          <label for="userId" class="ir_su">아이디</label>
          <input
            type="input"
            id="userId"
            name="userId"
            class="input_text"
            placeholder="아이디를 입력해주세요"
            autocomplete="off"
            maxlength="20"
          />
          <label for="userPw" class="ir_su">비밀번호</label>
          <input
            type="password"
            id="userPw"
            name="userPw"
            class="input_text"
            placeholder="비밀번호를 입력해주세요"
            autocomplete="off"
            maxlength="20"
          />
          <label for="userPw" class="ir_su">비밀번호 확인</label>
          <input
            type="password"
            id="userPw"
            name="userPw"
            class="input_text"
            placeholder="비밀번호를 한번더 입력해주세요"
            autocomplete="off"
            maxlength="20"
          />
          <label for="userPw" class="ir_su">이메일</label>
          <input
            type="input"
            id="userPw"
            name="userPw"
            class="input_text"
            placeholder="이메일을 입력해주세요"
            autocomplete="off"
            maxlength="50"
          />
          <label for="userPw" class="ir_su">이름</label>
          <input
            type="input"
            id="userPw"
            name="userPw"
            class="input_text"
            placeholder="이름을 입력해주세요"
            autocomplete="off"
            maxlength="10"
          />

          <input type="submit" value="회원가입" />
          <input type="button" id="signup-cancle" value="뒤로가기" />
          <input type="button" value="홈으로" onClick="location.href='/'" />
        </form>
      </section>
    </div>
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
	<script src="/js/common/nav.js"></script>
	<script src="/js/auth/login.js"></script>
    
</body>
</html>