<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="EUC-KR">
		<title>Translate</title>
		
		<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
		<script type="text/javascript">
			function sendWords() {
				words = $('#words').val();
				/* alert(words); */
				
				$.ajax({
					type: "get",
					url: "/nais/api/translate/nmt",
					contentType: "application/json",
					
					data: {"words": $("#words").val()},
					
					success: function (data, textStatus) {						
						parsed = JSON.parse(data);
						/* console.log(parsed.message.result.translatedText); */
						
						$('#message').text(parsed.message.result.translatedText);
						/* alert(textStatus); */
					},
					error: function (data, textStatus) {
						alert("Error");
						alert("textStatus : " + textStatus);
					},
					complete: function (data, textStatus) {
						
					}
				});
			}
		</script>
	</head>
	
	<body>
		<h1>번역페이지</h1>
		<form action="" method="get">
			번역할 문장 : <input type="text" name="" id="words">
			<input type="button" value="확인" onclick="sendWords()">
		</form>
		번역된 문장 : <span id="message"></span>
	</body>
</html>