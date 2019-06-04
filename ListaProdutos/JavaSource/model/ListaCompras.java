package model;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;

@Dependent
public class ListaCompras  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6655653731241151404L;
	private String idListaCompras;
	private String nomeListaCompras;
	private String dataListaCompras;
	private String idUser;
	private List<Produto> listaProdutos;
	private String qtdProduto;
	
	
	public String getIdListaCompras() {
		return idListaCompras;
	}
	public void setIdListaCompras(String idListaCompras) {
		this.idListaCompras = idListaCompras;
	}
	public String getNomeListaCompras() {
		return nomeListaCompras;
	}
	public void setNomeListaCompras(String nomeListaCompras) {
		this.nomeListaCompras = nomeListaCompras;
	}
	public String getDataListaCompras() {
		return dataListaCompras;
	}
	public void setDataListaCompras(String dataListaCompras) {
		this.dataListaCompras = dataListaCompras;
	}
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}
	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}
	public String getQtdProduto() {
		return qtdProduto;
	}
	public void setQtdProduto(String qtdProduto) {
		this.qtdProduto = qtdProduto;
	}
	
	
}
