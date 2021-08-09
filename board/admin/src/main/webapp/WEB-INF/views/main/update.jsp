<%@ page language="java" session="true" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/template/constants.jsp"%>

<body>
	<article>
		<div class="container" role="main">
			<h2>게시판 상세</h2>
			<form name="form" id="form" role="form" method="post" action="${pageContext.request.contextPath}/board/saveBoard">
				<div class="mb-3">
					<label for="title">제목</label>
					<input type="text" class="form-control" id="boardTitle" name="boardTitle" placeholder="제목을 입력해 주세요" value="${boardInfo.boardTitle}" <c:if test="${sessUserInfo.authority != 'ADMIN'}">readonly</c:if>>
					<input type="hidden" id="boardIdx" value="${boardIdx}" />
				</div>
			
				<div class="mb-3">
					<label for="reg_id">작성자</label>
					<input type="text" class="form-control" id="regId" name="regId"  value="${boardInfo.regName}" readonly>
				</div>

				
				<div class="mb-3">
					<label for="content">내용</label>
					<textarea class="form-control" rows="5" id="boardContent" name="boardContent" placeholder="내용을 입력해 주세요" <c:if test="${sessUserInfo.authority != 'ADMIN'}">readonly</c:if>>${boardInfo.boardContent}</textarea>
				</div>	
			</form>
			
			<br>
			<c:if test="${sessUserInfo.authority == 'ADMIN'}">
				<div>
					<button onclick="writeSubmit()" type="button" class="btn btn-sm btn-primary" id="btnSave">저장</button>
					<button onclick="location.href='/'" type="button" class="btn btn-sm btn-primary" id="btnList">목록</button>
				</div>
			</c:if>
		</div>
	</article>
</body>
	
