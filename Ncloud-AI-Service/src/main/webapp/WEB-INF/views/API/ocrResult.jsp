<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>OCR</title>
		<script src="<c:url value='../resources/js/jquery-3.6.0.min.js'/>"></script>
		<script src="<c:url value='../resources/js/API/ocr.js'/>"></script>
		<script type="text/javascript">
			function getText(){
				var data = {"version":"V2","requestId":"f8b56f6e-efe1-47d2-85c7-b40e292c122a","timestamp":1622697273751,
						"images":[
							{
								"uid":"6ee77129723d4749a8c153bb6c428e31",
								"name":"demo","inferResult":"SUCCESS",
								"message":"SUCCESS",
								"matchedTemplate":{"id":8666,"name":"sample"},
								"validationResult":{"result":"NO_REQUESTED"},
								"title":
								{"name":"logo","boundingPoly":
								{"vertices":[
									{"x":28.983133,"y":37.988632},
									{"x":111.99004,"y":37.990356},
									{"x":111.99088,"y":110.9942},
									{"x":28.984303,"y":110.992966}]},
									"inferText":"","inferConfidence":0.0},
									"fields":[
										{"name":"url","valueType":"ALL",
											"boundingPoly":{
												"vertices":[
													{"x":375.0,"y":210.0},
													{"x":494.0,"y":210.0},
													{"x":494.0,"y":222.0},
													{"x":375.0,"y":222.0}]},
													"inferText":"www.lgservice.co.kr","inferConfidence":0.9379,"type":"NORMAL","subFields":[{"boundingPoly":{"vertices":[{"x":375.0,"y":210.0},{"x":494.0,"y":210.0},{"x":494.0,"y":222.0},{"x":375.0,"y":222.0}]},"inferText":"www.lgservice.co.kr","inferConfidence":0.9379,"lineBreak":true}]},{"name":"tel","valueType":"ALL","boundingPoly":{"vertices":[{"x":63.0,"y":407.0},{"x":150.0,"y":408.0},{"x":150.0,"y":419.0},{"x":63.0,"y":418.0}]},"inferText":"080-023-7777","inferConfidence":0.9999,"type":"NORMAL","subFields":[{"boundingPoly":{"vertices":[{"x":63.0,"y":407.0},{"x":150.0,"y":408.0},{"x":150.0,"y":419.0},{"x":63.0,"y":418.0}]},"inferText":"080-023-7777","inferConfidence":0.9999,"lineBreak":true}]}]}]};
				
			
				/* alert(data.images.length);
				alert(data.images[0].uid);
				alert('field size '+data.images[0].fields.length); */
				/* alert(data.images[0].fields[0].name);
				alert(data.images[0].fields[0].inferText);
				alert(data.images[0].fields[1].name);
				alert(data.images[0].fields[1].inferText); */
			}
			getText();
		</script>
	</head>
	<body> 
		<h2>OCR : 이미지에서 텍스트 추출</h2>
		
		<form id="ocrForm"  method="post" enctype="multipart/form-data">	         
         	파일 :  <input type="file" id="uploadFile" name="uploadFile"> 
           <input type="submit" value="결과 확인">
		</form><br><br>		
				
		<h2> OCR : 이미지에서 텍스트 추출 결과</h2>
		<div id="resultDiv"></div>
		
		<br><br>
	</body>
</html>
