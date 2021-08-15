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
            <input type="text" id="loginId" class="fadeIn second" name="un" placeholder="아이디를 입력해주세요" onkeyup="enterkey()">
            <input type="text" id="loginPw" class="fadeIn third" name="up" placeholder="비밀번호를 입력해주세요" onkeyup="enterkey()">
            <input type="button" class="fadeIn fourth" value="Log In" onclick="loginSubmit()">
        </form>
		
        <!-- Remind Passowrd -->
        <div id="formFooter">
            <a class="underlineHover" href="${ct:url('AUTH.JOIN')}">회원가입</a>
        </div>

    </div>
</div>

	<script>

	//로그인 submit
	function loginSubmit(){
		
		var params = {
			 'un' : $.trim($("#loginId").val())
			,'up' : $("#loginPw").val()
		}
		
		console.log(params);
		
		if(params.un == ""){
			alert("아이디를 입력해주새요");
			return false;
		}
		
		else if(params.up == ""){
			alert("비밀번호를 입력해주새요");
			return false;
		}
		
		$.ajax({
	         type : 'POST'
	        ,url : '/auth/login-proc'
	        ,dataType : 'json'
	        ,data : params 
	        ,success : function(result) {
				console.log(result);
				if(result.resultCode != "00"){
					alert(result.resultMessage);
				}
				
				else{
					location.href=result.targetUrl;
				}
				
	        },
	        error: function(request, status, error) {
	          
	        }
	    }) 
	}
	
	function enterkey(){
		if (window.event.keyCode == 13) { 
			// 엔터키가 눌렸을 때
			loginSubmit(); 
		}
	}

</script>