package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

import exception.ArestaNotFoundException;
import exception.DuplicateKeyException;

/**
 *
 * @author tinin
 * @param <T> representa os vertices.
 */
public class AdjMapGraph<T> implements IGraph, Serializable {
    
    /**
     * HashMap de adjacencias. <br>
     * 
     * Sendo que as chaves do primeiro <b>HashMap</b> representam os vertices do grafo. NÃ£o obstante, as chaves do segundo HashMap representam os vertices adjacentes ao vertice do primeiro HashMap, e o valor as arestas que ligam os vertices.
     */
    private HashMap<T, HashMap<T, Aresta<T>>> adjacencias;
    
    private List<Aresta<T>> arestas;
    
    public AdjMapGraph() {
        adjacencias = new HashMap<>();
        arestas = new ArrayList<>();
    }
    
    // Vertices
    
    public void addVertice(T object) throws DuplicateKeyException {
        if(adjacencias.containsKey(object)) {
            throw new DuplicateKeyException();
        }
        adjacencias.put(object, new HashMap<>());
    }
    
    public Iterator vertices() {
        if (!adjacencias.isEmpty()) {
            return adjacencias.keySet().iterator();
        }
        return null;
    }
    
    public int numberVertices() {
        return adjacencias.keySet().size();
    }
    
    public void removeVertice(T object) {
    	if(!adjacencias.get(object).isEmpty()) {
    		for(Aresta<T> aresta : adjacencias.get(object).values()) {
    			if (aresta != null) {
    				arestas.remove(aresta);
    			}
        	}
    		adjacencias.remove(object);
    	}
    }    
    
    // Arestas.
    
    public void addAresta(T origem, T destino, double peso) {
    	if (this.adjacencias.containsKey(origem) && this.adjacencias.containsKey(destino)) {	// se existem os limites da aresta
        	Aresta<T> aresta = new Aresta<>(origem, destino, peso);	
    		if (!this.arestas.contains(aresta)) { // se a aresta ja nao existe
	        	this.arestas.add(aresta);  // adiciona no array de arestas
	        	this.adjacencias.get(origem).put(destino, aresta);  // adiciona no map de adjacencia (Ida)
	        	this.adjacencias.get(destino).put(origem, aresta);	//adiciona no map de adjacencia (Volta).
    		}
    	}
    }
    
    public Aresta<T> getAresta(T origem, T destino) throws ArestaNotFoundException {
    	for (Aresta<T> aresta : arestas) {
    		if (aresta.getOrigem().equals(origem) && aresta.getDestino().equals(destino)) {
    			return aresta;
    		}
    	}
    	throw new ArestaNotFoundException();
    }
    
    public Iterator arestas() {
        if (!arestas.isEmpty()) {
            return arestas.iterator();
        }
        return null;
    }
    
    public int numberArestas() {
    	return this.arestas.size();
    }
    
    public void removeAresta(Aresta<T> aresta) throws ArestaNotFoundException {
    	if (this.arestas.contains(aresta)) {
    		arestas.remove(aresta);
    		adjacencias.get(aresta.getOrigem()).remove(aresta.getDestino(), aresta);
    		adjacencias.get(aresta.getDestino()).remove(aresta.getOrigem(), aresta);
    	}
    	throw new ArestaNotFoundException();
    }
    
    /**
     * Função que retorna os vértices adjacentes ao vertice passado como parâmetro, se não existir nenhum vértice adjacente ele retorna <b>null</b>.
     * 
     * @param T vertice
     * @return HashMap<T, Aresta<T>> 
     * */
	public HashMap<T, Aresta<T>> getAdjacentes(T vertice) {
		return (this.adjacencias.get(vertice).isEmpty() ? null : this.adjacencias.get(vertice));
    }
	
	
	/**
	 * Função que retorna o menor caminho entre um vertice e os demais do grafo, baseada no algoritmo de menor caminho de Dijkstra.
	 * 
	 * @param T vertice
	 * @return HashMap<T, ShoPathEntry<T>> shortestPath
	 * */
	public HashMap<T, ShoPathEntry<T>> shortestPath(T vertice) throws ArestaNotFoundException {
		HashMap<T, ShoPathEntry<T>> shortest = new HashMap<>();
		PriorityQueue<ShoPathEntry<T>> queue = new PriorityQueue<>();
		
		queue.offer(new ShoPathEntry<T>(vertice, null, 0));
		
		while (!queue.isEmpty()) {
			ShoPathEntry<T> entry = queue.poll();
			vertice = entry.getAtual();
			
			if (!shortest.containsKey(vertice)) {
				shortest.put(vertice, entry);
				for (T temp : this.getAdjacentes(vertice).keySet()) {
					double distancia = this.getAresta(vertice, temp).getPeso();
					distancia = distancia + entry.getDistancia();
					
					ShoPathEntry<T> tempEntry = shortest.get(temp);
					if (tempEntry == null || distancia < tempEntry.getDistancia()) {
						queue.offer(new ShoPathEntry<T>(temp, vertice, distancia));
					}
				}
			}
		}
		
		return shortest;
	}
	
	/**
	 * Função que retorna o menor caminho entre dois vertices
	 * 
	 * @param T inicio, T fim, HashMap<T, ShoPathEntry<T>> paths
	 * @return Stack<ShoPathEntry<T>> stack
	 * */
	public Stack<ShoPathEntry<T>> path(T inicio, T fim, HashMap<T, ShoPathEntry<T>> paths) {
		
		Stack<ShoPathEntry<T>> stack = new Stack<>();
			while(!inicio.equals(fim)){
				stack.push(paths.get(fim));
				fim = stack.peek().getAnterior();
			}
			
		stack.push(paths.get(fim));
		return stack;
	}
}