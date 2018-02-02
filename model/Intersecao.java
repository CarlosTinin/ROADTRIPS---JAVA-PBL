package model;

public class Intersecao extends Ponto{
	
	public enum Tipo {
		ROTULA, CRUZAMENTO, SEMAFORO;
	}
	
	private Tipo tipo;

	public Intersecao(Tipo tipo) {
		this.setTipo(tipo);
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
}
