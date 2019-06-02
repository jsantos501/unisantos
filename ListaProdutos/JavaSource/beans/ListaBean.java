package beans;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import constantes.Constantes;
import dao.ListaDAO;
import dao.ProdutoDAO;
import model.ListaCompras;
import model.Produto;
import session.SessionContext;

@Named(value = "listaBean")
@SessionScoped
public class ListaBean  implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	private ListaCompras compras;
	private Produto produto;
	
    public String pag_detalhe_lista(ListaCompras listaCompras) throws SQLException {
    	setCompras(listaCompras);
    	setProduto(new Produto());
    	ProdutoDAO produtoDAO = new ProdutoDAO();
    	listaCompras.setListaProdutos(produtoDAO.listar(listaCompras));
    	
    	SessionContext.getInstance().setAttribute(Constantes.KEY_SESSION_COMPRAS_SELECIONADA, listaCompras);
    	return "/lista_compras";
    }

    public String pag_alterar_1(ListaCompras listaCompras) {
    	setCompras(listaCompras);
    	SessionContext.getInstance().setAttribute(Constantes.KEY_SESSION_COMPRAS_SELECIONADA, listaCompras);
    	return "/alterar_lista_1";
    }
    
    public String alterarProduto(Produto produtoSel) throws SQLException {
    	ListaDAO listaDAO = new ListaDAO();
    	compras = listaDAO.alterarProduto(compras, produtoSel);
    	return "/alterar_lista_1";
	}    
    
    public String alterarCompra() throws SQLException{
     	ListaDAO listaDAO = new ListaDAO();
     	compras = listaDAO.alterarLista(compras);
    	return "/alterar_lista_1";
    }
    
    
    public String pag_excluir_1(ListaCompras listaCompras) {
    	setCompras(listaCompras);
    	SessionContext.getInstance().setAttribute(Constantes.KEY_SESSION_COMPRAS_SELECIONADA, listaCompras);
    	return "/excluir_lista_1";
    }
    
    public String excluirProduto(Produto produtoSel) throws SQLException {
    	ListaDAO listaDAO = new ListaDAO();
    	compras = listaDAO.excluirProduto(compras, produtoSel);
    	return "/excluir_lista_1";
	}    

    public String excluirProdutoEmDetalhe(Produto produtoSel) throws SQLException {
    	ListaDAO listaDAO = new ListaDAO();
    	compras = listaDAO.excluirProduto(compras, produtoSel);
    	return "/lista_compras";
	}    

    
    public String excluirCompra() throws SQLException{
     	ListaDAO listaDAO = new ListaDAO();
     	compras = listaDAO.excluirLista(compras);
    	SessionContext.getInstance().addMessage(Constantes.MESSAGE_FORM_SUCCESS, "exclusão da lista de compras realizado com sucesso.");
    	return "/home";
    }
    
    
    
    public String addLista() throws SQLException{
    	ListaDAO listaDAO = new ListaDAO();
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		compras.setDataListaCompras(sdf.format(Calendar.getInstance().getTime()));
    	compras.setListaProdutos(new ArrayList<Produto>());
    	compras.setQtdProduto("0");
    	
    	compras = listaDAO.cadastrar(compras);
    	return "/cadastrar_lista_2";
    }
    
    public String addProduto() throws SQLException{
    	ListaDAO listaDAO = new ListaDAO();
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    	produto.setDataProduto(sdf.format(Calendar.getInstance().getTime()));
    	produto.setPego("false");
    	
    	compras = listaDAO.addProduto(compras,produto);
    	produto = new Produto();
    	
    	return "/cadastrar_lista_2";
    }

    
    public String addProdutoEmDetalhe() throws SQLException{
    	ListaDAO listaDAO = new ListaDAO();
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    	produto.setDataProduto(sdf.format(Calendar.getInstance().getTime()));
    	produto.setPego("false");
    	
    	compras = listaDAO.addProduto(compras,produto);
    	produto = new Produto();
    	
    	return "/lista_compras";
    }
    
    public String pag_cadastro_lista() {
    	compras = new ListaCompras();
    	produto = new Produto();
        	return "/cadastrar_lista_1";
    }

    public String efetivar_cadastro_lista(ListaCompras listaCompras) {
    	SessionContext.getInstance().addMessage(Constantes.MESSAGE_FORM_SUCCESS, "cadastro da lista de compras realizado com sucesso.");
    	
    	return "/home";
    }


    public String pag_home() {
    	
    	return "/home";
    }


    public String efetivar_alteracao_lista(ListaCompras listaCompras) {
    	SessionContext.getInstance().addMessage(Constantes.MESSAGE_FORM_SUCCESS, "alteração da lista de compras realizada com sucesso.");
    	
    	return "/home";
    }

    public String pag_excluir_lista(ListaCompras listaCompras) {
    	setCompras(listaCompras);
    	SessionContext.getInstance().addMessage(Constantes.MESSAGE_FORM_SUCCESS, "exclusão da lista de compras realizado com sucesso.");

    	SessionContext.getInstance().setAttribute(Constantes.KEY_SESSION_COMPRAS_SELECIONADA, listaCompras);
    	return "/home";
    }

	public ListaCompras getCompras() {
		return compras;
	}

	public void setCompras(ListaCompras compras) {
		this.compras = compras;
	}


	public Produto getProduto() {
		return produto;
	}


	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
    
}

