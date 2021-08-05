<%@ page language="java" session="true" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/template/constants.jsp"%>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<link rel="stylesheet" type="text/css" href="${ctxPath}/css/loginForm.css"/>

<div class="wrapper fadeInDown">
    <div id="formContent">
        <!-- Tabs Titles -->

        <!-- Icon -->
        <div class="fadeIn first">
            로그인
        </div>

        <!-- Login Form -->
        <form>
            <input type="text" id="userId" class="fadeIn second" name="login" placeholder="아이디를 입력해주세요" required>
            <input type="text" id="password" class="fadeIn third" name="login" placeholder="비밀번호를 입력해주세요" required>
            <input type="submit" class="fadeIn fourth" value="Log In">
        </form>

        <!-- Remind Passowrd -->
        <div id="formFooter">
            <a class="underlineHover" href="/auth/join">회원가입</a>
        </div>

    </div>
</div>