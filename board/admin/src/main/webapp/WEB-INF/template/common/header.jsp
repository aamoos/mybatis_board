<%@ page language="java" session="true" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/template/constants.jsp"%>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
 
<nav class="navbar navbar-default navbar-static-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar" aria-expanded="true" aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">게시판 예제</a>
        </div>
		<div id="navbar" class="navbar-collapse collapse in" aria-expanded="true" style="">
			<ul class="nav navbar-nav">
				<li class="active"><a href="#">게시판</a></li>
          	</ul>
          	
			<ul class="nav navbar-nav navbar-right">
				<c:choose>
					<c:when test="${empty sessUserInfo.authority}">
						<li><a href="#">회원가입</a></li>
						<li><a href="/auth/login">로그인</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="/auth/logout-proc">로그아웃</a></li>
					</c:otherwise>
				</c:choose>
				
			</ul>
		</div><!--/.nav-collapse -->
	</div>
</nav>