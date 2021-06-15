/**
 * ocr.js
 */
 alert('ocr in');
 $(function(){
	$('#ocrForm').on('submit', function(event){
		event.preventDefault(); //submit 후에  reload 안 되게
		var formData = new FormData($('#ocrForm')[0]);
		
		$.ajax({
			type:"post",
			enctype:"multipart/form-data",
			url:"clovaOCR",
			data:formData,
			processData:false, //필수
			contentType:false, //필수
			success:function(result){
				var data = JSON.parse(result);
				var field1 = (data.images[0].fields[0].name)+":"+data.images[0].fields[0].inferText;
				var field2 = (data.images[0].fields[1].name)+":"+data.images[0].fields[1].inferText;
				$('#resultDiv').text(field1+","+field2);
			},
			error:function(e){
				alert("에러 발생 : " + e);
			}			
		});
	});
	
});