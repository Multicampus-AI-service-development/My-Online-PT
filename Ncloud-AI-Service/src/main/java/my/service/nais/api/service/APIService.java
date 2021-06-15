package my.service.nais.api.service;

import org.springframework.web.multipart.MultipartFile;

public interface APIService {
	public String clovaTextToSpeech(String filePathName, String language);
	public String clovaOCRService(String filePathName);
	public String clovaSpeechToText(String filePathName, String language);
	public String translateWords(String words) throws Exception;
}
