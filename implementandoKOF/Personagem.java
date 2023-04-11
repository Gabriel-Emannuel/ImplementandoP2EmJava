package implementandoKOF;

import java.util.Arrays;

public class Personagem {
	
	private Cartas[] buffs;
	private String nomeJogador;
	private String monstro;
	private String ondeEsta;
	private int vida;
	private int energia;
	private int estrelas;
	
	//  Função Construtora
	
	public Personagem(String jogador, String monstro) {
		this.nomeJogador = jogador;
		this.monstro = monstro;
		this.vida = 10;
		this.energia = 0;
		this.estrelas = 0;
		this.buffs = new Cartas[3];
		this.ondeEsta = new String("fora");
	}
	
	// Gets de cada atributo
	
	public String getBuffs() {
		return Arrays.toString(buffs);
	}
	
	public String getMonstro() {
		return this.monstro;
	}
	
	public String getJogador() {
		return this.nomeJogador;
	}
	
	public String getLocalizacao() {
		return this.ondeEsta;
	}
	
	public int getVida() {
		return this.vida;
	}
	
	public int getEnergia() {
		return this.energia;
	}
	
	public int getEstrela() {
		return this.estrelas;
	}
	
	// Função de cura do personagem
	
	public void cura() {
		this.vida++;
	}
	
	// Função para o personagem receber um ponto de energia
	
	public void ganhaEnergia() {
		this.energia++;
	}
	
	// Funnção para o personagem ganhar uma quantidade inteira de estrelas
	
	public void ganhaEstrelas(int estrelas) {
		this.estrelas += estrelas;
	}
	
	// Função para reduzir a vida do personagem
	
	public void receberAtaque() {
		this.vida--;
	}
	
	// Funções para entrar/sair da cidade
	
	public void entrandoNaCidade() {
		this.ondeEsta = new String("cidade de tokyo");
	}
	
	public void saindoDaCidade() {
		this.ondeEsta = new String("fora");
	}
	
	// Funções para entrar/sair da baia
	public void entrandoNaBaia() {
		this.ondeEsta = new String("baia de tokyo");
	}
	
	public void saindoDaBaia() {
		this.ondeEsta = new String("fora");
	}
	
	// Método toString
	
	public String toString() {
		String Status = new String();
		Status += "Nome: " + this.nomeJogador + " ;" + System.lineSeparator();
		Status += "Monstro: " + this.monstro + " ;" + System.lineSeparator();
		Status += "Vida: " + this.vida + " ;" + System.lineSeparator();
		Status += "Energia: " + this.energia + " ;" + System.lineSeparator();
		Status += "Estrelas: " + this.estrelas + " ." + System.lineSeparator();
		return Status;
	}
}

