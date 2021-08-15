<%@ page language="java" session="true" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/template/constants.jsp"%>

<body>
	<article>
		<div class="container" role="main">
			<h2>게시판 글쓰기</h2>
				<div class="mb-3">
					<label for="title">제목</label>
					<input type="text" class="form-control" id="boardTitle" name="boardTitle" placeholder="제목을 입력해 주세요">
				</div>
				<br>
				<div class="mb-3">
					<label for="reg_id">작성자</label>
					<input type="text" class="form-control" id="regId" name="regId"  value="${sessUserInfo.authorityNm}" readonly>
				</div>
				<br>
				<div class="mb-3">
					<label for="content">내용</label>
					<textarea class="form-control" rows="5" id="boardContent" name="boardContent" placeholder="내용을 입력해 주세요"></textarea>
				</div>	
				<br>
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
							</div>
						</div>
					</form>
				</div>
			<br>
			<div>
				<button onclick="registerAction()" type="button" class="btn btn-sm btn-primary" id="btnSave">저장</button>
				<!-- <button onclick="registerAction()" type="button" class="btn btn-sm btn-primary" id="btnSave">파입업로드 테스트</button> -->
				<button onclick="location.href='${ct:url('MAIN.MAIN')}'" type="button" class="btn btn-sm btn-primary" id="btnList">목록</button>
			</div>
		</div>
	</article>
</body>