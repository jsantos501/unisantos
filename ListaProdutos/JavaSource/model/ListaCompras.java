package model;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import constantes.Constantes;

@Entity
@Dependent
@NamedQueries({
@NamedQuery(name=Constantes.LISTA_COMPRAS_LISTA_COMPRAS_POR_USUARIO, 
				query="SELECT lu FROM ListaCompras lu WHERE lu.usuario.login=:pLogin")
			})
public class ListaCompras  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6655653731241151404L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	
	private String idListaCompras;
	
	private String nomeListaCompras;
	
	private String dataListaCompras;
	
	private String qtdProduto;

	@ManyToOne(fetch = FetchType.EAGER)
	private Usuario usuario;

	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name = "compra_id")
	private List<Produto> listaProdutos;
	
	
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

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
