/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import constantes.Constantes;
import dao.ListaDAO;
import dao.UsuarioDAO;
import model.ListaCompras;
import model.Usuario;
import session.SessionContext;

@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 6015483335568647664L;

	private List<ListaCompras> listasCompras;

	private List<Usuario> listaUsuarios;
	private Usuario usuario;

	private UsuarioDAO userDAO = new UsuarioDAO();

	@Inject
	private Usuario user;

	private Locale currentLocale = new Locale("pt", "br");

	/**
	 * Creates a new instance of SimpleLogin
	 */
	public UserBean() {
	}

	public void englishLocale() {
		UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
		currentLocale = Locale.US;
		viewRoot.setLocale(currentLocale);
		Locale.setDefault(currentLocale);

	}

	public void portugueseLocale() {
		UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
		currentLocale = new Locale("pt", "br");
		viewRoot.setLocale(currentLocale);
		Locale.setDefault(currentLocale);

	}

	public String validar() throws SQLException {
		UsuarioDAO userDao = new UsuarioDAO();
		Usuario usuarioValidado = userDao.consultar(user.getLogin(), user.getSenha());

		if (usuarioValidado != null) {
			ListaDAO dao = new ListaDAO();
			listasCompras = dao.listar(usuarioValidado);
			SessionContext.getInstance().setAttribute(Constantes.KEY_SESSION_USUARIO_LOGADO, usuarioValidado);
			SessionContext.getInstance().setAttribute(Constantes.KEY_SESSION_LISTAS_COMPRAS, listasCompras);
			return "/home";
		}
		FacesContext.getCurrentInstance().addMessage("form:submit",
				new FacesMessage("Erro: login ou senha inválidos!", "Digite novamente"));
		return null;
	}

    public String pag_home() throws SQLException {
    	Usuario user = (Usuario) SessionContext.getInstance().getAttribute(Constantes.KEY_SESSION_USUARIO_LOGADO);
		ListaDAO dao = new ListaDAO();

    	listasCompras = dao.listar(user);
		SessionContext.getInstance().setAttribute(Constantes.KEY_SESSION_LISTAS_COMPRAS, listasCompras);

    	return "/home";
    }
	
	public String[] getAnosFormacao() {
		String[] anos = new String[50];
		int anoAtual = new GregorianCalendar().get(GregorianCalendar.YEAR);
		for (int i = 0; i < 50; i++) {
			anos[i] = String.valueOf(anoAtual - i);
		}
		return anos;
	}

	public String alterarUsuarioSel(Usuario userSel) throws SQLException {
		usuario = userSel;
		return "/alterar_usuario";
	}

	public String excluirUsuarioSel(Usuario userSel) throws SQLException {
		userDAO.excluirUsuario(userSel);
		listaUsuarios = userDAO.listar();
		SessionContext.getInstance().setAttribute(Constantes.KEY_SESSION_LISTA_USUARIOS, listaUsuarios);
		return "/lista_usuarios";
	}

	public String addUsuario() throws SQLException {
		userDAO.addUsuario(usuario);
		listaUsuarios = userDAO.listar();
		usuario = new Usuario();
		SessionContext.getInstance().setAttribute(Constantes.KEY_SESSION_LISTA_USUARIOS, listaUsuarios);
		return "/lista_usuarios";
	}

	public String attUsuario() throws SQLException {
		userDAO.alterarUsuario(usuario);
		listaUsuarios = userDAO.listar();
		usuario = new Usuario();
		SessionContext.getInstance().setAttribute(Constantes.KEY_SESSION_LISTA_USUARIOS, listaUsuarios);
		return "/lista_usuarios";
	}

	public String listarUsuario() throws SQLException {
		listaUsuarios = userDAO.listar();
		usuario = new Usuario();
		SessionContext.getInstance().setAttribute(Constantes.KEY_SESSION_LISTA_USUARIOS, listaUsuarios);
		return "/lista_usuarios";
	}

	public List<ListaCompras> getListasCompras() {
		return listasCompras;
	}

	public void setListasCompras(List<ListaCompras> listasCompras) {
		this.listasCompras = listasCompras;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public String sairSistema() {
		SessionContext.getInstance().encerrarSessao();
		return "/login";
	}

	public Locale getCurrentLocale() {
		return currentLocale;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	protected String getMessage(String key) {
		UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
		Locale locale = viewRoot.getLocale();
		ResourceBundle text = ResourceBundle.getBundle(Constantes.MESSAGE_MESSAGES, locale);
		return text.getString(key);
	}

	public void setCurrentLocale(Locale currentLocale) {
		this.currentLocale = currentLocale;
	}

}
