const
quadroManutencao = {
	autenticacaoOK : false,
	listGerarOS : "56eb606f6daa325d8468ad07",
	cardExplicacaoGerarOS : "56ec3f240a9ce096cd473cd1",

	init: function() {
		Trello.authorize({
			name : "Eufraten - Painel de manutenção",
			success : quadroManutencao.autenticacaoComSucesso,
			error : quadroManutencao.falhaAutenticacao,
			interactive : true,
		});
	},

	autenticacaoComSucesso : function() {
		quadroManutencao.autenticacaoOK = true;
		console.log("App autenticada no trello");
	},

	falhaAutenticacao : function(error) {
		quadroManutencao.autenticacaoOK = false;
		console.log("Falha na autenticacao do trello", error);
	},

	listarCardsDaOS : function(sucessCallback) {
		Trello.rest("GET", "/lists/" + quadroManutencao.listGerarOS + "/cards",
				sucessCallback, quadroManutencao.erro)
	},

	erro : function(error) {
		console.log("Erro geral", error.responseText, error);
	},

};
