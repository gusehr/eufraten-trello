package br.org.eufraten.manutencao;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

	@GetMapping("/")
	public String abrir() {
		return "index";
	}

	@GetMapping("/versao")
	@ResponseBody
	public String versao() {
		String versao = System.getenv("HEROKU_RELEASE_VERSION");
		if (versao == null || versao.equals("")) {
			versao = "SNAPSHOT";
		}
		return versao;
	}

}
