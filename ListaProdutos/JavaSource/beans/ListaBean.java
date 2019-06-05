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
import model.Usuario;
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
    	return "/alterar_lista_1";
	}    
    
    public String alterarCompra() throws SQLException{
    	
     	ListaDAO listaDAO = new ListaDAO();
    	Usuario user = (Usuario) SessionContext.getInstance().getAttribute(Constantes.KEY_SESSION_USUARIO_LOGADO);
    	compras.setIdUser(user.getId());
     	listaDAO.alterarLista(compras);
     	
     	
       List<ListaCompras> listasCompras = listaDAO.listar(user);
    	SessionContext.getInstance().setAttribute(Constantes.KEY_SESSION_LISTAS_COMPRAS, listasCompras);
        return "/home";

    }
    
    
    public String pag_excluir_1(ListaCompras listaCompras) {
    	setCompras(listaCompras);
    	SessionContext.getInstance().setAttribute(Constantes.KEY_SESSION_COMPRAS_SELECIONADA, listaCompras);
    	return "/excluir_lista_1";
    }
    
    public String excluirProduto(Produto produtoSel) throws SQLException {
    	ListaDAO listaDAO = new ListaDAO();
    	ProdutoDAO produtoDAO = new  ProdutoDAO();
    	
    	listaDAO.excluirProduto(compras, produtoSel);
    	compras.setListaProdutos(produtoDAO.listar(compras));
    	return "/excluir_lista_1";
	}    

    public String excluirProdutoEmDetalhe(Produto produtoSel) throws SQLException {
    	ListaDAO listaDAO = new ListaDAO();
    	ProdutoDAO produtoDAO = new  ProdutoDAO();
    	listaDAO.excluirProduto(compras, produtoSel);
    	compras.setListaProdutos(produtoDAO.listar(compras));

    	return "/lista_compras";
	}    

    
    public String excluirCompra() throws SQLException{
     	ListaDAO listaDAO = new ListaDAO();
     	listaDAO.excluirLista(compras);
     	Usuario user = (Usuario) SessionContext.getInstance().getAttribute(Constantes.KEY_SESSION_USUARIO_LOGADO);

        List<ListaCompras> listasCompras = listaDAO.listar(user);
     	SessionContext.getInstance().setAttribute(Constantes.KEY_SESSION_LISTAS_COMPRAS, listasCompras);
         return "/home";
    }
    
    
    
    public String addLista() throws SQLException{
    	ListaDAO listaDAO = new ListaDAO();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		compras.setDataListaCompras(sdf.format(Calendar.getInstance().getTime()));
    	compras.setListaProdutos(new ArrayList<Produto>());
    	compras.setQtdProduto("0");
    	Usuario user = (Usuario) SessionContext.getInstance().getAttribute(Constantes.KEY_SESSION_USUARIO_LOGADO);

    	compras.setIdUser(user.getId());
    	listaDAO.cadastrar(compras);
    	compras = listaDAO.consultar(compras);
    	SessionContext.getInstance().setAttribute(Constantes.KEY_SESSION_COMPRAS_SELECIONADA, compras);

    	return "/cadastrar_lista_2";
    }
    
    public String addProduto() throws SQLException{
    	ListaDAO listaDAO = new ListaDAO();
    	ProdutoDAO produtoDAO = new ProdutoDAO();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    	produto.setDataProduto(sdf.format(Calendar.getInstance().getTime()));
    	produto.setPego("0");
    	
    	produtoDAO.adicionar(produto);
    	produto = produtoDAO.buscarProduto(produto);
    	listaDAO.addProdutoNaLista(compras,produto);
    	compras = listaDAO.listaProdutos(compras);
    	produto = new Produto();
    	
    	return "/cadastrar_lista_2";
    }

    
    public String addProdutoEmDetalhe() throws SQLException{
    	ListaDAO listaDAO = new ListaDAO();
    	
    	ProdutoDAO produtoDAO = new ProdutoDAO();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    	produto.setDataProduto(sdf.format(Calendar.getInstance().getTime()));
    	produto.setPego("0");
    	
    //	compras = listaDAO.addProduto(compras,produto);
    	produtoDAO.adicionar(produto);
    	produto = produtoDAO.buscarProduto(produto);
    	listaDAO.addProdutoNaLista(compras,produto);
    	compras = listaDAO.listaProdutos(compras);
    	
    	
    	
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

