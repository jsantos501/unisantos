package model;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

@Dependent
public class Produto  implements Serializable {
	private String id;
	private String pego;
	private String nome;
	private String marca;
	private String quantidade;
	private String unidadeMedida;
	private String descricao;
	private String valorProduto;
	private String dataProduto;
	private String mercado;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPego() {
		return pego;
	}
	public void setPego(String pego) {
		this.pego = pego;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	public String getUnidadeMedida() {
		return unidadeMedida;
	}
	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getValorProduto() {
		return valorProduto;
	}
	public void setValorProduto(String valorProduto) {
		this.valorProduto = valorProduto;
	}
	public String getDataProduto() {
		return dataProduto;
	}
	public void setDataProduto(String dataProduto) {
		this.dataProduto = dataProduto;
	}
	public String getMercado() {
		return mercado;
	}
	public void setMercado(String mercado) {
		this.mercado = mercado;
	}
	
	
}
