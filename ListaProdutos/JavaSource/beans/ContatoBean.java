package beans;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.Contato;
import model.ContatoDAO;

/**
 *
 * @author ciro
 */
@Named(value = "contatoBean")
@SessionScoped
public class ContatoBean implements Serializable {
    @Inject
    private Contato contato;
    
    @Inject
    private List<Contato> contatos;

    /**
     * Creates a new instance of ContatoBean
     */
    public ContatoBean() {
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }
    
    public String pag_novo() {
        contato = new Contato();
        return "/index";
    }

    public String pag_consultar() throws SQLException {
        ContatoDAO cdao = new ContatoDAO();
        contatos = cdao.listar();
        return "/consultar";
    }

    public String novoContato() throws SQLException {
        ContatoDAO cdao = new ContatoDAO();
        cdao.adicionar(contato);
        contato = new Contato();
        return "/index";
    }

}
