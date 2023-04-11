package implementandoKOF;

public class Dado {
	
	private String[] faces = {"1", "2", "3", "energia", "vida", "pata"};
	private String faceAtual;
	
	// Função Construtora
	
	public Dado() {
		this.faceAtual = faces[(int) (Math.random() * 6) + 1];
	}
	
	// Função para rolar o dado
	
	public void rolarDado() {
		this.faceAtual = faces[(int) (Math.random() * 6) + 1];
	}
	
	// Função para mostrar a face atual
	
	public String getFaceAtual() {
		return this.faceAtual;
	}
}
