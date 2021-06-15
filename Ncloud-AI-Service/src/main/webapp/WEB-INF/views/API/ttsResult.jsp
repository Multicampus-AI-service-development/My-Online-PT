<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Text To Speech</title>
		<script src="<c:url value='../resources/js/jquery-3.6.0.min.js'/>"></script>
		<script src="<c:url value='../resources/js/API/tts.js'/>"></script>
	</head>
	<body> 
		<h2>CLOVA Voice : TTS (텍스트를 음성으로 변환)</h2>
		텍스트를 음성으로 변환<br><br>
		
		<form id="ttsForm"  method="post" enctype="multipart/form-data">	         
         	음성 파일 :  <input type="file" id="uploadFile" name="uploadFile"><br><br>
         	언어 : <select  name="language">
         				<option value="nara" selected>한국어, 여성</option>
         				<option value="jinho">한국어, 남성</option>
         				<option value="nhajun">한국어, 아동 (남)</option>
         				<option value="ndain">한국어, 아동(여)</option>
         				<option value="clara">영어, 여성</option>
         				<option value="matt">영어, 남성</option>
         				<option value="carmen">스페인어, 여성</option>
         				<option value="meimei">중국어, 여성</option>
         	 		</select><br><br>

           <input type="submit" value="결과 확인">
		</form><br><br>		
				
		<h2>TTS : 텍스트를 음성으로 변환한 결과</h2>	
		<div id="resultDiv"></div><br><br>	
		
		<div>
			<audio preload="auto" controls></audio>
		</div>
		
		<br><br>
		<a href="/">index 페이지로 이동</a>
	</body>
</html>