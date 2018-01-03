package model;

import java.util.HashMap;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import exception.DuplicateKeyException;

public class AdjMapGraphTest {

	private AdjMapGraph<Ponto> graphMap;
	
	@Before
	public void setUp() {
		graphMap = new AdjMapGraph<>();
	}
	
	@Test
	public void inserirVerticeTest() throws DuplicateKeyException {
		Ponto ponto = new Ponto("Lul");
		this.graphMap.addVertice(ponto);
		Iterator iterator = graphMap.vertices();
		Assert.assertEquals("Lul", ((Ponto) iterator.next()).getNome());
	}
	
	@Test
	public void inserirArestaTest() throws DuplicateKeyException {
		Ponto origem = new Ponto("origem");
		Ponto destino = new Ponto("destino");
		this.graphMap.addVertice(origem);
		this.graphMap.addVertice(destino);
		//
		this.graphMap.addAresta(origem, destino, 1);
		//
		HashMap<Ponto, Aresta<Ponto>> graph = this.graphMap.getAdjacentes(origem);
		Iterator keyIterator = graph.keySet().iterator();
		Iterator valueIterator = graph.values().iterator();
		
		while (keyIterator.hasNext()) {
			System.out.println(((Ponto) keyIterator.next()).getNome());
		}
		
		while (valueIterator.hasNext()) {
			Aresta<Ponto> a = (Aresta<Ponto>) valueIterator.next();
			System.out.println(a.getOrigem().getNome());
			System.out.println(a.getDestino().getNome());
			System.out.println(a.getPeso());
		}
	}

}
