<%@ page language="java" session="true" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE html>
<html lang="ko">
	<head>
		<tiles:insertAttribute name="header"/>
		<tiles:insertAttribute name="meta"/>
		<tiles:insertAttribute name="styles"/>
	</head>
	<body>
		<tiles:insertAttribute name="scripts"/>	
		<tiles:insertAttribute name="contents"/>
		<tiles:insertAttribute name="contents-js"/>
	</body>
</html>