package my.service.nais.api.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface APIController {
	public String CLOVASpeechRecognition(Locale locale, Model model);
	public String stt(@RequestParam("uploadFile") MultipartFile file,
			@RequestParam("language") String language);
	
	public String PapagoTextTranslation(Locale locale, Model model);
	public String nmt(@RequestParam("words") String words,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
}
