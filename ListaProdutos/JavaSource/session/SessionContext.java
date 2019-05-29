package session;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import constantes.Constantes;

public class SessionContext {
	private static SessionContext instance = null;

	private SessionContext() {
	}

	public static SessionContext getInstance() {
		if (instance == null)
			instance = new SessionContext();
		return instance;
	}

	private ExternalContext currentExternalContext() throws RuntimeException {
		FacesContext context = FacesContext.getCurrentInstance();
		if (context == null) throw new RuntimeException("O FacesContext não pode ser chamado fora de uma requisição HTTP");
		else return context.getExternalContext();
	}

	public Object getAttribute(String nome) throws RuntimeException {
		HttpSession session = (HttpSession) currentExternalContext().getSession(false);
		if (session == null) throw new RuntimeException("Não foi possível recuperar a sessão!");
		return session.getAttribute(nome);
	}

	public void setAttribute(String nome, Object valor) throws RuntimeException {
		HttpSession session = (HttpSession) currentExternalContext().getSession(false);
		if (session == null) throw new RuntimeException("Não foi possível recuperar a sessão!");
		session.setAttribute(nome, valor);
	}

	public void encerrarSessao() {
		currentExternalContext().invalidateSession();
	}
	
    public void addMessage(String key, String value) {
    	FacesContext.getCurrentInstance().addMessage(key,
                new FacesMessage(value));
	
    }
    
    public static String getMessage(String key) {
		UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();  
	   	Locale locale = viewRoot.getLocale();
	   	ResourceBundle text = ResourceBundle.getBundle(Constantes.MESSAGE_MESSAGES, locale);
	    return text.getString(key);
	}

	
}

