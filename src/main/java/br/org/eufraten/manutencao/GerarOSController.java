package br.org.eufraten.manutencao;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import br.org.eufraten.manutencao.ordemDeServico.ManutencaoServices;
import br.org.eufraten.manutencao.ordemDeServico.OrdemDeServico;
import br.org.eufraten.manutencao.ordemDeServico.RelatorioOrdemDeServico;

@Controller
public class GerarOSController {

	@PostMapping("/gerarOS")
	public void gerarOS(HttpServletRequest request, HttpServletResponse response, String cardId) throws IOException {
		ManutencaoServices manutencaoServices = new ManutencaoServices();
		OrdemDeServico ordemDeServico = manutencaoServices.gerarOrdemDeServico(cardId);
		RelatorioOrdemDeServico relatorioOrdemDeServico = new RelatorioOrdemDeServico(ordemDeServico);
		manutencaoServices.salvarRelatorio(relatorioOrdemDeServico);
		response.setStatus(HttpServletResponse.SC_OK);
	}
}
