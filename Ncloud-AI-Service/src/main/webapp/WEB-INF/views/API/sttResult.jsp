<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Speech To Text</title>
		<script src="<c:url value='../resources/js/jquery-3.6.0.min.js'/>"></script>
		<script src="<c:url value='../resources/js/API/stt.js'/>"></script>
	</head>
	<body> 
		<h2>CSR : STT (음성을 텍스트로 변환)</h2>
		음성파일(mp3)에서 음성을 추출해서 텍스트로 변환<br><br>
		
		<form id="sttForm"  method="post" enctype="multipart/form-data">	         
         	음성 파일 :  <input type="file" id="uploadFile" name="uploadFile"><br><br>
         	언어 : <select  name="language">
         				<option value="Kor" selected>한국어</option>
         				<option value="Eng">영어</option>
         				<option value="Jpn">일본어</option>
         				<option value="Chn">중국어</option>
         	 		</select>

           <input type="submit" value="결과 확인">
		</form><br><br>		
				
		<h2>STT : 음성 파일을 텍스트로 변환한 결과</h2>	
		<div id="resultDiv"></div><br><br>	
		
		<br><br>
	</body>
</html>