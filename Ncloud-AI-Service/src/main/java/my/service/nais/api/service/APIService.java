package my.service.nais.api.service;

public interface APIService {
	public String clovaSpeechToText(String filePathName, String language);
	public String translateWords(String words) throws Exception;
}
