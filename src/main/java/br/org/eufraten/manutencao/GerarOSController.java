package br.org.eufraten.manutencao;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.org.eufraten.manutencao.ordemDeServico.ManutencaoServices;
import br.org.eufraten.manutencao.ordemDeServico.OrdemDeServico;
import br.org.eufraten.manutencao.ordemDeServico.RelatorioOrdemDeServico;
import br.org.eufraten.web.JSendResponse;

@Controller
public class GerarOSController {

	private static Logger LOGGER = LoggerFactory.getLogger(GerarOSController.class);

	@PostMapping("/gerarOS")
	@ResponseBody
	public JSendResponse gerarOS(HttpServletRequest request, HttpServletResponse response, @RequestParam String cardId)
			throws IOException {
		try {
			ManutencaoServices manutencaoServices = new ManutencaoServices();
			OrdemDeServico ordemDeServico = manutencaoServices.gerarOrdemDeServico(cardId);
			RelatorioOrdemDeServico relatorioOrdemDeServico = new RelatorioOrdemDeServico(ordemDeServico);
			manutencaoServices.salvarRelatorio(relatorioOrdemDeServico);
			response.setStatus(HttpServletResponse.SC_OK);
			return JSendResponse.sucess("OS Gerada com sucesso!");
		} catch (Exception ex) {
			LOGGER.error("Erro ao gerar OS", ex);
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return JSendResponse.error(ex, "Falha ao gerar a OS!");
		}
	}
}
