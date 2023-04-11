package implementandoKOF;

import java.util.Scanner;

public class Tabuleiro {
	
	private String[] monstros = {"Cyber Bunny", "The King", "Alienoid", "Gigazaur", "Kraken ", "Meka Dragon"};
	private Personagem[] jogadores;
	private Personagem[] jogadoresVivos;
	private Personagem[] jogadoresMortos;
	private Dado[] dados;
	private Turno turno;
	private Scanner leEntrada;
	
	// Função Construtora
	
	public Tabuleiro(int quantosJogadores) {
		this.jogadores = new Personagem[quantosJogadores];
		this.jogadoresVivos = new Personagem[quantosJogadores];
		this.dados = new Dado[6];
		this.leEntrada = new Scanner(System.in);
	}
	
	// inicio do jogo
	
	public void instanciarDados() {
		for (int i = 0; i < this.dados.length; i++) {
			dados[i] = new Dado();
		}
	}
	
	// criando todos os jogadores
	
	public void colocandoJogadores() {
		for (int i = 0; i < this.jogadores.length; i++) {
			this.jogadores[i] = criandoJogador(i+1);
		}
	}
	
	// Iniciando Jogo (no caso, instanciando o primeiro turno)
	
	public void iniciandoJogo() {
		this.turno = new Turno(this.jogadores, this.jogadoresVivos[0], dados);
	}
	
	// Função Input
	
	private String input(String mensagem) {
		System.out.print(mensagem);
		return this.leEntrada.nextLine();
		
	}
	
	// CriandoJogador
	
	private Personagem criandoJogador(int posicao) {
		String[] monstrosDisponiveis = monstrosDisponiveis();
		String nome;
		String monstro;
		int numero;
		
		do {
			nome = input("Insira o nome do jogador nº "+ posicao + ": ");
		} while (permanenciaNome(nome ,nomesJaUsados()));
		
		do {
			imprimindoOpcoes(monstrosDisponiveis);
			numero = Integer.parseInt(input("Insira a opção do Monstro: "));
		} while (permanenciaMonstro(numero, monstrosDisponiveis));
		monstro = monstrosDisponiveis[numero-1];
		return new Personagem(nome, monstro);
	}
	
	// Mostra todos os monstros disponíveis
	
	private String[] monstrosDisponiveis() {
		String[] monstrosJaUsados = monstrosUsados();
		int naoUsados = this.monstros.length - monstrosJaUsados.length;
		String[] monstrosNaoUsados = new String[naoUsados];
		int index = 0;
		for (int i = 0; i < this.monstros.length; i++) {
			if (!(in(this.monstros[i], monstrosJaUsados))) {
				monstrosNaoUsados[index++] = this.monstros[i];
			}
		}
		return monstrosNaoUsados;
	}

	// Mostra todos os monstros já usados
	
	private String[] monstrosUsados() {
		String[] monstrosJaUsados;
		int usados = 0;
		for (int i = 0; i < this.jogadores.length; i++) {
			if (this.jogadores[i] == null) {
				break;
			}
			usados++;
		}
		monstrosJaUsados = new String[usados];
		for (int j = 0; j < usados; j++) {
			monstrosJaUsados[j] = this.jogadores[j].getMonstro();
		}
		return monstrosJaUsados;
	}

	// Bloco de código para impressão de opções
	
	private void imprimindoOpcoes(String[] monstrosDisponiveis) {
		for (int i = 0; i < monstrosDisponiveis.length; i++) {
			System.out.println((i+1) + ") " + monstrosDisponiveis[i]);
		}
	}

	// função in
	
	private boolean in(String valor, String[] listaDeValores) {
		for (String valorDaVez: listaDeValores) {
			if (valor.equals(valorDaVez)) {
				return true;
			}
		}
		return false;
	}

	// função para garantir permanência
	
	private boolean permanenciaNome(String entrada, String[] valoresPossiveis) {
		for (String valor: valoresPossiveis) {
			if (entrada.equals(valor)) {
				return true;
			}
		}
		return false;
	}
	
	// função para garantir a permanencia
	
	private boolean permanenciaMonstro(int numero, String[] monstrosDisponiveis) {
		return (numero >= 0 && numero < monstrosDisponiveis.length);
	}

	// função para pegar os nomes já usados
	
	private String[] nomesJaUsados() {
		int quantosNomes = 0;
		for (int i = 0; i < this.jogadores.length; i++) {
			if (this.jogadores[i] == null) {
				break;
			}
			quantosNomes++;
		}
		String[] nomesJaUsados = new String[quantosNomes];
		for (int j = 0; j < quantosNomes; j++) {
			nomesJaUsados[j] = this.jogadores[j].getJogador();
		}
		return nomesJaUsados;
	}
	
}
