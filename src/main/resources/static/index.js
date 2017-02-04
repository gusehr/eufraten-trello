const
quadroManutencao = {
	autenticacaoOK : false,
	listGerarOS : "56eb606f6daa325d8468ad07",
	cardExplicacaoGerarOS : "56ec3f240a9ce096cd473cd1",

	init : function() {
		Trello.authorize({
			name : "eufraten-manutencao-geral",
			interactive : false,
			success : quadroManutencao.autenticacaoComSucesso,
			error : quadroManutencao.falhaAutenticacao,
			interactive : true,
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
		Trello.rest("GET", "/lists/" + quadroManutencao.listGerarOS + "/cards",
				quadroManutencao.cardsListados, quadroManutencao.erro)
	},

	cardsListados : function(cards) {
		const
		selectGerarOS = $('#select-gerarOS'), btnGerarOS = $("#btn-gerarOS");

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
	},

	erro : function(error) {
		console.log("erro de acesso", error);
	},

};

$(document).ready(function() {
	quadroManutencao.init();
})