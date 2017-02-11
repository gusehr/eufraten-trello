package br.org.eufraten.manutencao;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GerarOSController {

	@PostMapping("/gerarOS")
	public void gerarOS(HttpServletRequest request, HttpServletResponse response, String cardId) {
		try {
			response.setContentType("text/plain");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + cardId + ".txt\"");

			IOUtils.write("cardID:" + cardId, response.getOutputStream(), Charset.defaultCharset());
			response.flushBuffer();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
