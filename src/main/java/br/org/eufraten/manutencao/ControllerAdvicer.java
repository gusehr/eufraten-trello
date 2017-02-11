package br.org.eufraten.manutencao;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class ControllerAdvicer {
	public static final String VERSAO;

	static {
		String versao = System.getenv("HEROKU_RELEASE_VERSION");
		if (versao == null || versao.equals("")) {
			versao = "SNAPSHOT";
		}
		VERSAO = versao;
	}

	@ModelAttribute("versao")
	public String versao() {
		return VERSAO;
	}

}
