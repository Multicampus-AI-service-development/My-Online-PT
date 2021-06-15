package my.service.nais.clova.controller;

import java.util.Locale;

import org.springframework.ui.Model;

public interface CLOVAController {
	public String CLOVAChatbot(Locale locale, Model model);
}
