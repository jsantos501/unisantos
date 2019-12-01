package model;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import constantes.Constantes;

/**
*
* @author ALEXANDRE JOSE DOS SANTOS
*/
@Entity
@Dependent
@NamedQueries({
	 @NamedQuery(name=Constantes.USUARIO_USUARIO_POR_LOGIN_E_SENHA, 
			 query="SELECT u FROM Usuario u WHERE u.login=:pLogin AND u.senha=:pSenha"),
	 @NamedQuery(name=Constantes.USUARIO_LISTAR_TODOS_USUARIOS, 
	 		query="SELECT u FROM Usuario u")

	})
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1086125588626003704L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String login;
	private String senha;
	private String perfil;
	private String nome;
	private String cpf;
	private String email;
	private String cep;
	private String celular;
	


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}

}
