<%@ page language="java" session="true" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ include file="/WEB-INF/template/constants.jsp"%>

<style>
	/* paginate */
	.pagination a.on {color:#fff;font-weight:600;background:#a0afbf;}
</style>

<body>
	<div class="container">
		<h1 class="page-header">게시판 목록</h1>
		<br>
		<div class="input-group">
		<input type="text" id="searchVal" class="form-control" placeholder="Search">
		<div class="input-group-btn">
			<button class="btn btn-default" type="button" onclick="goPage(0)">
				<i class="glyphicon glyphicon-search"></i>
			</button>
		</div>
	</div>
	<br />
	
	<div id="list-div">
	</div>
</div>
</body>
