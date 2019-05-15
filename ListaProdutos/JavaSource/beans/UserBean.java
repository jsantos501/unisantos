/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import constantes.Constantes;
import dao.ListaDAO;
import dao.UsuarioDAO;
import model.ListaCompras;
import model.User;
import model.Usuario;
import session.SessionContext;

/**
 *
 * @author ciro
 */
@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {

    private static final String LOGIN = "ale";
    private static final String PASSWORD = "123";
    
    private List<ListaCompras> listasCompras;

    @Inject
    private Usuario user;

    /**
     * Creates a new instance of SimpleLogin
     */
    public UserBean() {
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public String validar() throws SQLException {
    	UsuarioDAO userDao = new UsuarioDAO();
    	Usuario usuarioValidado = userDao.consultar(user.getLogin(), user.getSenha());
    	
    	
        if (usuarioValidado != null) {
        	ListaDAO dao = new ListaDAO();
        	listasCompras = dao.listar();
        	SessionContext.getInstance().setAttribute(Constantes.KEY_SESSION_USUARIO_LOGADO, usuarioValidado);
        	SessionContext.getInstance().setAttribute(Constantes.KEY_SESSION_LISTAS_COMPRAS, listasCompras);
            return "/home";
        }
        FacesContext.getCurrentInstance().addMessage("form:submit",
                new FacesMessage("Erro: login ou senha inválidos!", "Digite novamente"));
        return null;
    }

    public String[] getAnosFormacao() {
        String[] anos = new String[50];
        int anoAtual = new GregorianCalendar().
                get(GregorianCalendar.YEAR);
        for (int i = 0; i < 50; i++) {
            anos[i] = String.valueOf(anoAtual - i);
        }
        return anos;
    }

    public List<SelectItem> getListaDeIdiomas() {
        List<SelectItem> idiomas = new ArrayList<>();
        idiomas.add(new SelectItem("Inglês", "English"));
        idiomas.add(new SelectItem("Francês", "Français"));
        idiomas.add(new SelectItem("Italiano", "Italiano"));
        idiomas.add(new SelectItem("Espanhol", "Español"));
        return idiomas;
    }

	public List<ListaCompras> getListasCompras() {
		return listasCompras;
	}

	public void setListasCompras(List<ListaCompras> listasCompras) {
		this.listasCompras = listasCompras;
	}
    
}
