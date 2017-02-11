package br.org.eufraten.manutencao;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class ControllerAdvicer {
	public static final String VERSAO;

	static {
		String herokuVersion = System.getenv("HEROKU_RELEASE_VERSION");
		if (StringUtils.isNotBlank(herokuVersion)) {
			VERSAO = herokuVersion;
		} else {
			VERSAO = "SNAPSHOT";
		}
	}

	@ModelAttribute("versao")
	public String versao() {
		return VERSAO;
	}

}
