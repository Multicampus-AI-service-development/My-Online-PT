/**
 *chatbot.js
 */
 
 $(function(){
	// 웰컴 메시지 받기 위해서 message 란에 입력 받기 전에
	//빈 값으로 서버에 전송하고 웰컴 메시지 받음
	cllAjax(); // message 값 없이 서버로 전송
	$('audio').hide();
	
	///////////////////////////////////////////////////////////////
	/* 음성 질문 녹음 */
	
	const record = document.getElementById("record");
        const stop = document.getElementById("stop");
        const soundClips = document.getElementById("sound-clips");

        const audioCtx = new(window.AudioContext || window.webkitAudioContext)(); // 오디오 컨텍스트 정의

        if (navigator.mediaDevices) {
            var constraints = {
                audio: true
            }
             let chunks = [];

            navigator.mediaDevices.getUserMedia(constraints)
                .then(stream => {
                    const  mediaRecorder = new MediaRecorder(stream);
              
                    record.onclick = () => {
                        mediaRecorder.start();
                        record.style.background = "red";
                        record.style.color = "black";
                    }

                    stop.onclick = () => {//정지 버튼 클릭 시
                        mediaRecorder.stop();//녹음 정지                       
                        record.style.background = "";
                        record.style.color = "";
                    }
                    
                    mediaRecorder.onstop = e => {
                        
                        const clipName = "voiceMsg";  // 파일명 : 확장자 안 붙었음
						//태그 3개 생성
                        const clipContainer = document.createElement('article');                     
                        //const audio = document.createElement('audio');
                        const a = document.createElement('a');
						// 속성/ 컨텐츠 설정
                        //clipContainer.classList.add('clip');
                        //audio.setAttribute('controls', '');                        
                        //clipContainer.appendChild(audio);
                       
                        clipContainer.appendChild(a);
                        soundClips.appendChild(clipContainer);                        
						
                        //chunks에 저장된 녹음 데이터를 audio 양식으로 설정
                       // audio.controls = true;
                        const blob = new Blob(chunks, {
                            'type': 'audio/mp3 codecs=opus'
                        }); 
                        
                        chunks = [];
                        const audioURL = URL.createObjectURL(blob);
                        //audio.src = audioURL;
                        //a.href=audio.src;
                       //blob:http://localhost:8011/6377d19d-2ca8-49b1-a37f-068d602ceb60    
                        a.href=audioURL;                   
                        a.download = clipName;                      
                       //a.innerHTML = "DOWN"
						//a.click(); // 다운로드 폴더에 저장하도록 클릭 이벤트 발생		
						
						//서버로 업로드: 다운로드 후 1초 대기
						/*setTimeout(function(){
							fileUpload(clipName + ".mp3"); //파일명
						}, 1000);*/
						//파일 다운로드 하지 않으니까 1초 대기할 필요 없음
						fileUpload(blob, clipName); //파일 데이터와 파일명 전달
						
										
                    }//mediaRecorder.onstop

                    //녹음 시작시킨 상태가 되면 chunks에 녹음 데이터를 저장하라 
                    mediaRecorder.ondataavailable = e => {
                        chunks.push(e.data)
                    }
                    
                })
                .catch(err => {
                    console.log('The following error occurred: ' + err)
                })
        }   
	
	
	
	///////////////////////////////////////////////////////////////
	
	/* 서버에 업로드 */
	function fileUpload(blob, clipName){
		// 파일 업로드 부분 추가
		var formData = new FormData();
		formData.append('uploadFile', blob, clipName+".mp3");
		
		$.ajax({
			type:"post",
			url:"clovaSTT2",
			data: formData, // 폼 데이터 전송
			processData:false, //필수
			contentType:false, //필수
			success:function(result){
				/* chatBox에 보낸 메시지 추가 (동적 요소 추가) */ /* 넌 누구니? */
				$('#chatBox').append('<div class="msgBox send"><span>' +
											result + '</span></div><br>');	
											
				//챗봇에게 전달
				$('#message').val(result);	
				cllAjax();		
				$('#message').val('');
			},
			error:function(e){
				alert("에러 발생 : " + e);
			}			
		});
	}
	
	
	///////////////////////////////////////////////////////////////
	
	$('#chatForm').on('submit', function(event){
		event.preventDefault(); //submit 후에  reload 안 되게
		
		/* chatBox에 보낸 메시지 추가 (동적 요소 추가) */ /* 넌 누구니? */
		if($('#message').val() != ""){
			$('#chatBox').append('<div class="msgBox send"><span>' +
											$('#message').val() + '</span></div><br>');		
		}
		
		cllAjax();
		/* 입력란 비우기 */
		$('#message').val('');
		
	}); //submit 끝
	
	function cllAjax(){
		$.ajax({
			type:"post",
			//dataType:'application/json;UTF-8',
			url:"chatbotCall",
			data:{message:$('#message').val()},
			success:function(result){	
				alert(result)
				console.log(result)			
				/*chatBox에 받은 메시지 출력 (챗봇의 답변))*/
				$('#chatBox').append('<div class="msgBox receive"><br>챗봇<br><span>' +
											result + '</span></div><br><br>');	
											
				/* 스크롤해서 올리기 */
				$('#chatBox').scrollTop($('#chatBox').prop("scrollHeight"));
				
				//챗봇으로 부터 받은 텍스트 답변을 음성으로 변환하기 위해  TTS  호출
				//callAjaxTTS(result);
			},
			error:function(e){
				alert("에러 발생 : " + e.message);
				console.log(e);
			}			
		});
	}
	
	///////////////////////////////////////////////////
	
	function callAjaxTTS(result){
		$.ajax({
			type:"post",
			//dataType:'application/json;UTF-8',
			url:"chatbotTTS",
			data:{message:result},
			success:function(result){				
				$('audio').prop("src", '/ai/' + result)[0].play();
				//$('audio').hide();
			},
			error:function(e){
				alert("에러 발생 : " + e);
			}			
		});
		
	}
	
}); // $(function()  끝


