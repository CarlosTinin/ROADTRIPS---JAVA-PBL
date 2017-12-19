/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author tinin
 */
public class Aresta<T> implements Serializable {
    private T origem;
    private T destino;
    private double peso;

    public Aresta(T origem, T destino, double peso) {
        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
    }

    public T getOrigem() {
        return origem;
    }

    public void setOrigem(T origem) {
        this.origem = origem;
    }

    public T getDestino() {
        return destino;
    }

    public void setDestino(T destino) {
        this.destino = destino;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
    
    public boolean equals(Object obj) {
    	if (obj instanceof Aresta) {
    		Aresta aresta = (Aresta) obj;
    		if (this.origem.equals(aresta.getOrigem()) && this.destino.equals(aresta.getDestino())) {
    			return true;
    		}
    	}
		return false;
    }
}
