package my.service.nais.api.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import my.service.nais.api.service.APIService;


@Controller
@RequestMapping(value="/api")
public class APIControllerImpl implements APIController {
	
	@Autowired
	private APIService apiService;
//	@Autowired
//	private CLOVAService clovaService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping("/")
	public String home(Locale locale, Model model) {
		return "API/home";
	}
	
	// // CLOVA Voice
	@Override
	@RequestMapping(value="voice", method=RequestMethod.GET)
	public String CLOVAVoice(Locale locale, Model model) {
		logger.info("Welcome APIController! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate);
		
		return "API/ttsResult";
	}
	
	// stt.js ajax request
	@Override
	@RequestMapping("/clovaTTS")
	@ResponseBody
	public String TTS(@RequestParam("uploadFile") MultipartFile file,
								@RequestParam("language") String language) {
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
			  
			  result = apiService.clovaTextToSpeech(filePathName, language);
			  System.out.println(result);
			  
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;  //음성 일 이름 반환
	}
	
	
	// // CLOVA OCR
	@Override
	@RequestMapping(value="ocr", method=RequestMethod.GET)
	public String CLOVAOCR(Locale locale, Model model) {
		logger.info("Welcome APIController! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate);
		
		return "API/ocrResult";
	}
	
	// ocr.js ajax request
	@Override
	@RequestMapping("/clovaOCR")
	@ResponseBody
	public String clovaOCR(@RequestParam("uploadFile") MultipartFile file) {
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
			  
			  result = apiService.clovaOCRService(filePathName);
			  System.out.println(result);
			  
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
	// // CLOVA Speech Recognition (CSR)
	@Override
	@RequestMapping(value="/csr", method=RequestMethod.GET)
	public String CLOVASpeechRecognition(Locale locale, Model model) {
		logger.info("Welcome APIController! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate);
		
		return "API/sttResult";
	}
	
	// stt.js ajax request
	@Override
	@RequestMapping(value="/clovaSTT", produces="application/text; charset=UTF-8")
	@ResponseBody
	public String stt(@RequestParam("uploadFile") MultipartFile file,
								@RequestParam("language") String language) {
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
			  
			  result = apiService.clovaSpeechToText(filePathName, language);
			  System.out.println("ai "+result);
			  
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	// //
	
	// // Papago Translation - Text
	@Override
	@RequestMapping(value="/translate", method=RequestMethod.GET)
	public String PapagoTextTranslation(Locale locale, Model model) {
		logger.info("Welcome APIController! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate);
		
		return "API/translate";
	}
	
	// translate.jsp ajax request
	@Override
	@RequestMapping(value="/translate/nmt", method=RequestMethod.GET)
	@ResponseBody
	public String nmt(@RequestParam("words") String words,
						HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		System.out.println(words);
		
		String result = words;
		result = apiService.translateWords(words);

		return result;
	}
	// //
	
}
