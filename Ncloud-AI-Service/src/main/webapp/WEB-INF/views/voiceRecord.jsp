<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>음성 녹음</title>
		<script src="<c:url value='resources/js/jquery-3.6.0.min.js'/>"></script>
		<script src="<c:url value='resources/js/voiceRecord.js'/>"></script>
	</head>
	<body> 
		<button id="record">녹음</button> 
		<button id="stop">정지</button>
		<div id="sound-clips"></div>
	</body>
</html>