const
indexPage = {

	init : function() {
		$("#btn-carregarNovamente").on("click", function() {
			window.location.reload();
		});
		$("#btn-gerarOS").on("click", function() {
			indexPage.gerarOS();
		});
		setTimeout(function() {
			quadroManutencao.init();
			quadroManutencao.listarCardsDaOS(indexPage.tratarCardsListados);
		}, 2000);
	},

	tratarCardsListados : function(cards) {
		const
		selectGerarOS = $('#select-gerarOS'), btnGerarOS = $("#btn-gerarOS"),
				alertMensagemAutorizacao = $("#alert-mensagemAutorizacao");

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
		alertMensagemAutorizacao.toggle("slow", function() {
			btnGerarOS.fadeOut(500).fadeIn(1000);
		});
	},
	
	gerarOS: function() {
		const form = $("#form-gerarOS");
		const action = form.attr("action");
		const imgLoading = $("#img-gerarOS");
		const btnGerarOS = $("#btn-gerarOS")

		imgLoading.toggleClass("hidden");
		btnGerarOS.attr("disabled", "disabled");
		$.post(action, form.serialize())
			.always(function (data) {
				imgLoading.toggleClass("hidden");
				btnGerarOS.removeAttr("disabled");

				if (data.message) {
					alert(data.message);
				}
				if (data.responseJSON) {
					if (data.responseJSON.message) {
						alert(data.responseJSON.message);
					}
					console.log(data.responseJSON);
				}
				console.log(data);
			});
	}

};

$(document).ready(function() {
	indexPage.init();
});