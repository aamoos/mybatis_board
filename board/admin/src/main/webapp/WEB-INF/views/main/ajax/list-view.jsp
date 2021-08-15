<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ include file="/WEB-INF/template/constants.jsp"%>

<form>
    <table class="table table-hover">
    	<colgroup>
		   <col width="2%" />
		   <col width="5%" />
		   <col width="20%" />
		   <col width="5%" />
		   <col width="5%" />
		   <col width="5%" />
		   <col width="5%" />
		</colgroup>
        <thead>
            <tr>
            	<th>
            		<label class="checkbox-inline">
						<input type="checkbox" id="allCheckBox" class="chk" onclick="allChecked(this)">
					</label>
            	</th>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>날짜</th>
                <th>조회수</th>
                <th>파일유무</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach var="board" items="${boardList.content}" varStatus="vs">
				<tr>
					<td>
						<label class="checkbox-inline">
							<input type="checkbox" class="chk" name="cchk" onclick="cchkClicked()" value="${board.boardIdx}">
						</label>
					<td>${resultDataTotal - (boardList.size * boardList.number) - vs.index}</td>
				    <td><a href="${ct:url('MAIN.MAIN_UPDATE')}/${board.boardIdx}">${board.boardTitle}</a></td>
				    <td>${board.regName}</td>
				    <td>
				    	<fmt:parseDate value="${board.regDate}" var="regDate" pattern="yyyy-MM-dd HH:mm:ss.s"/>
						<fmt:formatDate value="${board.regDate}" pattern="yyyy-MM-dd"/>
					</td>
				    <td>${board.viewCount}</td>
				    <td ${board.fileCount }>
				    	<c:if test="${board.fileCount != 0}">
				    		<img src="/images/file_icon.png" style="width:20px; height:auto; vertical-align: center; "/>
				    	</c:if>
				    </td>
				</tr>
			</c:forEach>
			
			<c:if test="${resultDataTotal == 0}">
				<tr>
					<center><td colspan="6" style="text-align: center;">등록된 게시판 리스트가 없습니다.</td></center>
				</tr>
			</c:if>
				
        </tbody>
    </table>
   	
   	<!-- ADMIN 권한일경우에만 글쓰기 권한있음 -->
   	<c:if test="${sessUserInfo.authority == 'ADMIN'}">
		<div class="text-right">            
			<a href='javascript:boardDelete();' class="btn btn-danger">글삭제</a>      
            <a href="${ct:url('MAIN.MAIN_WRITE')}" class="btn btn-primary">글쓰기</a>            
		</div>
    </c:if>
   	 
    <div class="text-center">
    	<c:if test="${resultDataTotal != 0}">
         <ul class="pagination">
            <paginator:print goPageScript="goPage" curPage="${boardList.number}" totPages="${boardList.totalPages}"/>
         </ul>
      </c:if>
    
    	<!--
    	<ul class="pagination">
    		<li><a href="#">prev</a></li>
    		<li><a href="#">1</a></li>
    		<li><a href="#">2</a></li>
    		<li><a href="#">3</a></li>
    		<li><a href="#">4</a></li>
    		<li><a href="#">5</a></li>
    		<li><a href="#">next</a></li>
    	</ul>
    	-->
    </div>	
</form>