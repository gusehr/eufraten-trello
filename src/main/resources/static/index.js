const
quadroManutencao = {
	autenticacaoOK : false,
	listGerarOS: "56eb606f6daa325d8468ad07",
	
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
		
		
		console.log(cards);
	},

	erro : function(error) {
		console.log("erro de acesso", error);
	},

};

$(document).ready(function() {
	quadroManutencao.init();
})