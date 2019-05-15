package beans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import constantes.Constantes;
import model.ListaCompras;
import session.SessionContext;

@Named(value = "produtoBean")
@SessionScoped
public class ProdutoBean implements Serializable {

	
	public String efetivar_cadastro_lista(ListaCompras listaCompras) {
    	SessionContext.getInstance().addMessage(Constantes.MESSAGE_FORM_SUCCESS, "cadastro da lista de compras realizado com sucesso.");
    	
    	return "/home";
    }
	
}
