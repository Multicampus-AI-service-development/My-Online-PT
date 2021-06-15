package my.service.nais.clova.controller;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import my.service.nais.HomeController;
import my.service.nais.clova.service.CLOVAServiceImpl;
import my.service.nais.clova.service.STTService;
import my.service.nais.clova.service.TTSService;

@Controller
@RequestMapping(value="/clova")
public class CLOVAControllerImpl implements CLOVAController {
	@Autowired
	private TTSService ttsService;
	@Autowired
	private STTService sttService;
	@Autowired
	private CLOVAServiceImpl chatService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	// // CLOVA Chatbot
	@Override
	@RequestMapping(value="/chatbot", method=RequestMethod.GET)
	public String CLOVAChatbot(Locale locale, Model model) {
		// TODO Auto-generated method stub
		return "CLOVA/chatForm2";
	}
	
	// chatbot2.js callAjax() ajax request
	@RequestMapping(value="/chatbotCall",method = RequestMethod.POST,produces ="application/text; charset=UTF-8")
	@ResponseBody
	public String chatbot(@RequestParam("message") String message) {
		String result = chatService.main(message);			
		return result;  
	}
	
	// chatbot2.js callAjaxTTS() ajax request
	@RequestMapping(value="/chatbotTTS",method = RequestMethod.POST,produces ="application/text; charset=UTF-8")
	@ResponseBody
	public String chatbotTTS(@RequestParam("message") String message) {
		String result = "";
		
		result = ttsService.chatbotTextToSpeech(message);	

		return result;
	}
	
	// chatbot2.js fileUpload() ajax request
	@RequestMapping(value="/clovaSTT2",produces ="application/text; charset=UTF-8")
	@ResponseBody
	public String STT2(@RequestParam("uploadFile") MultipartFile file) {
		String result = "";
		
		try {
			//1. 파일 저장 경로 설정 : 실제 서비스 되는 위치 (프로젝트 외부에 저장)
			  String uploadPath =  "c:/ai/";
			  
			  //2.원본 파일 이름
			  String originalFileName = file.getOriginalFilename();  
			  
			  //3. 파일 생성 
			  String filePathName = uploadPath + originalFileName;
			  File file1 = new File(filePathName);
			  
			  //4. 서버로 전송
			  file.transferTo(file1);
			  
			  result = sttService.clovaSpeechToText2(filePathName);
			  System.out.println(result);
			  
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

		return result;
	}
		
}
