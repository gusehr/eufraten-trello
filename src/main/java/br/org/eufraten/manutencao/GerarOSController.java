package br.org.eufraten.manutencao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GerarOSController {

	@PostMapping("/gerarOS")
	public void gerarOS(HttpServletRequest request, HttpServletResponse response, String cardId) {
		System.out.println(request);
		System.out.println(response);
		System.out.println(cardId);
	}
}
