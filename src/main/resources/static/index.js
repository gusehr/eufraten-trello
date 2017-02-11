const
quadroManutencao = {
	autenticacaoOK : false,
	listGerarOS : "56eb606f6daa325d8468ad07",
	cardExplicacaoGerarOS : "56ec3f240a9ce096cd473cd1",

	autenticar : function() {
		Trello.authorize({
			name : "Eufraten - Painel de manutenção",
			success : quadroManutencao.autenticacaoComSucesso,
			error : quadroManutencao.falhaAutenticacao,
			interactive : true,
			type : "popup",
		});
	},

	autenticacaoComSucesso : function() {
		quadroManutencao.autenticacaoOK = true;
		console.log("App autenticada no trello");
		quadroManutencao.listarCardsDaOS();
	},

	falhaAutenticacao : function(error) {
		quadroManutencao.autenticacaoOK = false;
		console.log("Falha na autenticacao do trello", error);
	},

	listarCardsDaOS : function() {
		if (quadroManutencao.autenticacaoOK) {
			Trello.rest("GET", "/lists/" + quadroManutencao.listGerarOS
					+ "/cards", quadroManutencao.cardsListados,
					quadroManutencao.erro)
		} else {
			quadroManutencao.autenticar();
		}
	},

	cardsListados : function(cards) {
		const
		selectGerarOS = $('#select-gerarOS'), btnGerarOS = $("#btn-gerarOS"),
				btnMensagemAutorizacao = $("#btn-MensagemAutorizacao");

		selectGerarOS.empty();
		btnGerarOS.attr("disabled", "disabled");

		if (cards.length <= 1) {
			selectGerarOS
					.append($(
							'<option>',
							{
								value : "",
								text : "Nenhum cartão encontrado nas listas de ordens de serviço"
							}));
			return;
		}

		selectGerarOS.append($('<option>', {
			value : "",
			text : "Selecione o cartão"
		}));
		$.each(cards, function(i, card) {
			if (card.id != quadroManutencao.cardExplicacaoGerarOS) {
				selectGerarOS.append($('<option>', {
					value : card.id,
					text : card.name
				}));
			}
		});

		btnGerarOS.removeAttr("disabled");
		btnMensagemAutorizacao.click();
	},

	erro : function(error) {
		console.log("erro de acesso", error);
	},

};

$(document).ready(function() {
	setTimeout(function() {
		quadroManutencao.autenticar();
	}, 2000);
})