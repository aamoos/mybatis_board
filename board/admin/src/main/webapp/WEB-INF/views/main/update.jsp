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
				
				<!-- 관리자가 아닐경우 -->
				<c:if test="${sessUserInfo.authority != 'ADMIN'}">
					<div class="mb-3">
						<div class="data_file_txt" id="data_file_txt">
							<span>첨부 파일</span>
							<br>
							<div id="articlefileChange">
								<c:forEach var="boardFileInfo" items="${boardFileInfo}" varStatus="vs">
										<div id="file${vs.index}">
											<a href="${ct:url('COMMON.FILE_DOWNLOAD')}/${boardFileInfo.fileId}">
												<font style="font-size:12px">${boardFileInfo.origNm}</font>
												<img src="/images/file_icon.png" style="width:20px; height:auto; vertical-align: middle; cursor: pointer;">
											</a>
										</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</c:if>
				
				<!-- 관리자일경우 -->
				<c:if test="${sessUserInfo.authority == 'ADMIN'}">
					<div class="mb-3">
						<form name="dataForm" id="dataForm" onsubmit="return registerAction()">
							<button id="btn-upload" onclick="fileClick()" type="button" style="border: 1px solid #ddd; outline: none;">파일 추가</button>
						  	<input id="input_file" onChange="fileChange(this)" multiple="multiple" type="file" style="display:none;">
						  	<span style="font-size:10px; color: gray;">※첨부파일은 최대 10개까지 등록이 가능합니다.</span>
						  	<br>
						  	<br>
						  	<br>
						  	<div class="data_file_txt" id="data_file_txt">
								<span>첨부 파일</span>
								<br />
								<div id="articlefileChange">
									<c:forEach var="boardFileInfo" items="${boardFileInfo}" varStatus="vs">
										<div class="attachDiv">
											<a href="${ct:url('COMMON.FILE_DOWNLOAD')}/${boardFileInfo.fileId}">
												<font style="font-size:12px">${boardFileInfo.origNm}</font>
												<img src="/images/file_icon.png" style="width:20px; height:auto; vertical-align: middle; cursor: pointer;">
											</a>
											
											<a class="beforeDeleteFile" data-attr="${boardFileInfo.fileId}">
												<img src="/images/icon_minus.png" style="width:20px; height:auto; vertical-align: middle; cursor: pointer;">
											</a>
										</div>
								</c:forEach>
								</div>
							</div>
						</form>
					</div>
				</c:if>
				
			</form>
			
			<br>
			<c:if test="${sessUserInfo.authority == 'ADMIN'}">
				<div>
					<button onclick="registerAction()" type="button" class="btn btn-sm btn-primary" id="btnSave">수정</button>
					<button onclick="boardDelete()" type="button" class="btn btn-sm btn-danger" >삭제</button>
					<button onclick="location.href='${ct:url('MAIN.MAIN')}'" type="button" class="btn btn-sm btn-primary" id="btnList">목록</button>
				</div>
			</c:if>
		</div>
	</article>
</body>
	
