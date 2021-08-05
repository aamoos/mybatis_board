<%@ page language="java" session="true" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/template/constants.jsp"%>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<link rel="stylesheet" type="text/css" href="${ctxPath}/css/loginForm.css"/>

<div class="wrapper">
  <div id="formContent">
    <!-- Tabs Titles -->

    <!-- Icon -->
    <div class="fadeIn first">
      회원가입
    </div>

    <!-- Login Form -->
    <form>
      <input type="text" id="userId" class="fadeIn second" name="login" placeholder="아이디를 입력해주세요." required>
      <input type="text" id="password" class="fadeIn third" name="login" placeholder="비밀번호를 입력해주세요." required>
      <input type="text" id="userName" class="fadeIn third" name="login" placeholder="이름을 입력해주세요.">
      <input type="text" id="email" class="fadeIn third" name="login" placeholder="이메일을 입력해주세요.">
      <input type="text" id="handPhoneNo" class="fadeIn third" name="login" placeholder="핸드폰번호를 입력해주세요.">
      <input type="button" class="fadeIn fourth" value="Join" onclick="join()">
    </form>

    <!-- Remind Passowrd -->
    <div id="formFooter">
      <a class="underlineHover" href="/auth/login">뒤로가기</a>
    </div>

  </div>
</div>