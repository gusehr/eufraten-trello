package br.org.eufraten.manutencao;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping("/")
	public String abrir() {
		return "index";
	}
}
