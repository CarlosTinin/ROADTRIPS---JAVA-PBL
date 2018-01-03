package model;

public class ShoPathEntry<T> {
	
	private T atual;
	private T anterior;
	private double distancia;
	
	public ShoPathEntry(T atual, T anterior, double distancia) {
		this.anterior = anterior;
		this.atual = atual;
		this.distancia = distancia;
	}
	
	public T getAtual() {
		return this.atual;
	}
	
	public T getAnterior() {
		return this.anterior;
	}
	
	public double getDistancia() {
		return this.distancia;
	}
	
	public void setAtual(T atual) {
		this.atual = atual;
	}
	
	public void setAnterior(T anterior) {
		this.anterior = anterior;
	}
	
	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}
	
	public int compareTo(Object obj){
		ShoPathEntry<T> entry = (ShoPathEntry) obj;
		if (this.distancia < entry.distancia)
			return -1;
		else if (this.distancia > entry.distancia)
			return 1;
		else
			return 0;
	} 
}
