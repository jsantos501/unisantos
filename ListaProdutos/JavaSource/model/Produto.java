package model;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
*
* @author ALEXANDRE JOSE DOS SANTOS
*/
@Entity
@Dependent
public class Produto  implements Serializable {
	private static final long serialVersionUID = -7810451511090909294L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	private String pego;
	private String nome;
	private String marca;
	private String quantidade;
	private String descricao;
	private String valorProduto;
	private String dataProduto;
	private String mercado;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
