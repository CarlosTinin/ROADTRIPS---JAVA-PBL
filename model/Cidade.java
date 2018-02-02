package model;

import java.util.ArrayList;
import java.util.List;

public class Cidade extends Ponto {
	
	private int id;
	private String name;
	private double area;
	private double populacao;
	private String descricao;
	private List<Local> locaisComer;
	private List<Local> locaisDormir;
	
	public Cidade(int id, String name, double area, double populacao, String descricao) {
		this.id = id;
		this.name = name;
		this.area = area;
		this.populacao = populacao;
		this.descricao = descricao;
		this.locaisComer = new ArrayList<>();
		this.locaisDormir = new ArrayList<>();
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the area
	 */
	public double getArea() {
		return area;
	}
	/**
	 * @param area the area to set
	 */
	public void setArea(double area) {
		this.area = area;
	}
	/**
	 * @return the populacao
	 */
	public double getPopulacao() {
		return populacao;
	}
	/**
	 * @param populacao the populacao to set
	 */
	public void setPopulacao(double populacao) {
		this.populacao = populacao;
	}
	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	/**
	 * @return the locaisComer
	 */
	public List<Local> getLocaisComer() {
		return locaisComer;
	}
	/**
	 * @return the locaisDormir
	 */
	public List<Local> getLocaisDormir() {
		return locaisDormir;
	}
	
	public int getId() {
		return this.id;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Cidade) {
			return (((Cidade) obj).getId() == this.id);
		}
		return false;
	}
}
