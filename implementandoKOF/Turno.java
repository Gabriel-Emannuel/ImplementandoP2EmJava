package implementandoKOF;


public class Turno {
	
	private Personagem[] personagensVivos;
	private Personagem quemTaJogando;
	private Dado[] dados;
	private int rolagemDados;
	private int turnosJogados;
	
	// Função Construtora:
	
	public Turno(Personagem[] todosJogadores, Personagem jogadorDaVez, Dado[] dados) {
		this.personagensVivos = todosJogadores;
		this.quemTaJogando = jogadorDaVez;
		this.dados = dados;
		this.rolagemDados = 3;
		this.turnosJogados = 0;
	}

	// Primeira Parte do Turno
	
	// Primeira parte do turno
	
	public String resultadoRolagem() {
		String resultados = new String();
		for (int i = 0; i < 6; i++) {
			resultados += new String(i+") "+dados[i].getFaceAtual());
			resultados += System.lineSeparator();
		}
		return resultados;
	}
	
	public void rolarDados() {
		for (Dado dadoDaVez: this.dados) {
			dadoDaVez.rolarDado();
		}
	}
	
	public void rolarDados(String quaisDados) {
		String[] dadosParaRolar = quaisDados.split(" ");
		for (String dadoDaVez: dadosParaRolar) {
			this.dados[Integer.parseInt(dadoDaVez)].rolarDado();
		}
	}
	
	public void primeiraRolagem() {
		rolarDados();
		this.rolagemDados--;
	}
	
	public void rolagensPosteriores(String quaisDados) {
		if (rolagemDados != 0) {
			rolarDados(quaisDados);
			this.rolagemDados--;
		}
		System.out.println("Erro! Não se pode ter mais de 3 rolagens.");
	}
	
	// Segunda Parte do Turno
	// Segunda parte do turno
	
	public void efeitosDados() {
		ganhaEstrelas();
		ganhaEnergia();
		ganhaCura();
		atacando();
	}
	
	public void ganhaEstrelas() {
		int quantosUm = 0;
		int quantosDois = 0;
		int quantosTres = 0;
		int total = 0;
		
		for (Dado dadoDaVez: this.dados) {
			if (dadoDaVez.getFaceAtual().equals("1")) {
				quantosUm++;
			}
			if (dadoDaVez.getFaceAtual().equals("2")) {
				quantosDois++;
			}
			if (dadoDaVez.getFaceAtual().equals("3")) {
				quantosTres++;
			}
		}
		
		if (quantosTres >= 3) {
			total += 3;
			quantosTres -= 3;
			total += quantosTres * 3 + quantosDois * 2 + quantosUm * 1;
		} else if (quantosDois >= 3) {
			total += 2;
			quantosDois -= 3;
			total += quantosTres * 3 + quantosDois * 2 + quantosUm * 1;
		} else if (quantosUm >= 1) {
			total += 1;
			quantosUm -= 3;
			total += quantosTres * 3 + quantosDois * 2 + quantosUm * 1;
		}
		this.quemTaJogando.ganhaEstrelas(total);
	}
	
	public void ganhaEnergia() {
		for (Dado dadoDaVez: this.dados) {
			if (dadoDaVez.getFaceAtual().equals("energia")) {
				this.quemTaJogando.ganhaEnergia();
			}
		}
	}
	
	public void ganhaCura() {
		for (Dado dadoDaVez: this.dados) {
			if (dadoDaVez.getFaceAtual().equals("vida")) {
				this.quemTaJogando.cura();
			}
		}
	}
	
	public void atacando() {
		for (Dado dadoDaVez: this.dados) {
			if (dadoDaVez.getFaceAtual().equals("pata")) {
				this.ataque();
			}
		}
	}
	
	public void ataque() {
		if ((this.quemTaJogando.getLocalizacao()).equals("fora")) {
			for (Personagem alvo: this.personagensVivos) {
				if (!((alvo.getLocalizacao()).equals("fora"))) {
					alvo.receberAtaque();
				}
			}
		} else {
			for (Personagem alvo: this.personagensVivos) {
				if ((alvo.getLocalizacao()).equals("fora")) {
					alvo.receberAtaque();
				}
			}
		}
	}

	// Terceira Parte do Turno
	// Terceira Parte do turno

	public void entrandoCidade() {
		this.quemTaJogando.entrandoNaCidade();
	}
	
	public void entrandoBaia() {
		this.quemTaJogando.entrandoNaBaia();
	}
	
	public void saindoCidade() {
		this.quemTaJogando.saindoDaCidade();
	}
	
	public void saindBaia() {
		this.quemTaJogando.saindoDaBaia();
	}
	
	// Novo Turno
	// Fim do turno
	
	public Personagem[] conferirVivos() {
		int quantosVivos = 0;
		for (Personagem jogador: this.personagensVivos) {
			if (jogador.getVida() > 0) {
				quantosVivos++;
			}
		}
		Personagem[] personagensVivos = new Personagem[quantosVivos];
		int index = 0;
		for (int i = 0; i < this.personagensVivos.length; i++) {
			if (this.personagensVivos[i].getVida() > 0) {
				personagensVivos[index++] = this.personagensVivos[i];
			}
		}
		return personagensVivos;
	}
	
	public void novoTurno() {
		this.personagensVivos = conferirVivos();
		this.quemTaJogando = this.personagensVivos[++this.turnosJogados%personagensVivos.length];
		this.rolagemDados = 3;
	}

	// Função de demonstração de status: 

	public String getStringPersonagem() {
		return this.quemTaJogando.toString();
	}
}
