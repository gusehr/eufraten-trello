package br.org.eufraten.manutencao.ordemDeServico;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.FileAttribute;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.julienvey.trello.domain.Action;
import com.julienvey.trello.domain.Card;

import br.org.eufraten.manutencao.trello.TrelloBoard;

public class ManutencaoServices {

	private final static String QUADRO_MANUTENCAO_ID = "56e48159f355aa7de6717f7b";
	private final static String CARD_OS_ID = "sjMFgC4m";
	private final static String LIST_FEITO_VALIDADO = "56e4824f47c5ae52e716e4b5";

	public OrdemDeServico gerarOrdemDeServico(String cardId) {
		TrelloBoard boardManutencao = new TrelloBoard(QUADRO_MANUTENCAO_ID);

		Card cardOrdemServico = boardManutencao.cardPorId(cardId);

		String numeroOS = gerarNumeroDaOS(boardManutencao);

		OrdemDeServico ordemDeServico = new OrdemDeServico(numeroOS, cardOrdemServico, boardManutencao);
		return ordemDeServico;
	}

	private final static Pattern REGEX_NUMERO_OS = Pattern.compile("^[ ]*OS ([0-9]+) Gerada.*$",
			Pattern.CASE_INSENSITIVE);

	private String gerarNumeroDaOS(TrelloBoard boardManutencao) {
		int ultimaOSGerada = 0;
		List<Action> comentariosDoCard = boardManutencao.comentariosDoCard(CARD_OS_ID);
		for (Action comentario : comentariosDoCard) {
			Matcher matcherNumeroOS = REGEX_NUMERO_OS.matcher(comentario.getData().getText());
			if (matcherNumeroOS.find()) {
				ultimaOSGerada = Integer.valueOf(matcherNumeroOS.group(1));
				break;
			}
		}
		ultimaOSGerada++;
		String novoComentario = "OS " + ultimaOSGerada + " Gerada";

		boardManutencao.adicionarComentario(CARD_OS_ID, novoComentario);

		return String.valueOf(ultimaOSGerada);
	}

	public void salvarRelatorio(RelatorioOrdemDeServico relatorioOrdemDeServico) throws IOException {
		String filePath = relatorioOrdemDeServico.gerarExcel(Files.createTempDirectory("br.org.eufraten").toString());
		TrelloBoard boardManutencao = new TrelloBoard(QUADRO_MANUTENCAO_ID);
		boardManutencao.adicionarAnexo(relatorioOrdemDeServico.getOrdemDeServico().getCardId(), filePath);
	}

}
