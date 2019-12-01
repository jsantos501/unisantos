package dao;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import connection.JPAEntityManager;
import constantes.Constantes;
import model.ListaCompras;
import model.Usuario;


public class UsuarioDAO {

    public List<Usuario> listar() throws SQLException {
 
		List<Usuario> lu = null;

		EntityManager manager = JPAEntityManager.getEntityManager();
		Query query = manager.createNamedQuery(Constantes.USUARIO_LISTAR_TODOS_USUARIOS, Usuario.class);
		
		try {
			lu = query.getResultList();
		}catch (NoResultException e) {
			lu = null;
		}finally {
			manager.close();
		}
	
		return lu;
		
    }
    
    public Usuario consultar(String usuario, String senha) throws SQLException {
    	Usuario u = null;

		EntityManager manager = JPAEntityManager.getEntityManager();
		Query query = manager.createNamedQuery(Constantes.USUARIO_USUARIO_POR_LOGIN_E_SENHA, Usuario.class);
		query.setParameter("pLogin", usuario);	
		query.setParameter("pSenha", senha);	
		try {
			u = (Usuario) query.getSingleResult();
		}catch (NoResultException e) {
			u = null;
		}finally {
			manager.close();
		}
	
		return u;
    	
    }
    

   
    public void addUsuario(Usuario usuario) throws SQLException {
		EntityManager manager = JPAEntityManager.getEntityManager();
		manager.getTransaction().begin();
		manager.persist(usuario);
		manager.flush();
		manager.getTransaction().commit();
		manager.close();
    }

    public void excluirUsuario(Usuario usuario) throws SQLException {
		EntityManager manager = JPAEntityManager.getEntityManager();
		List<ListaCompras> lu = null;
		manager.getTransaction().begin();
		try {
			usuario = manager.find(Usuario.class, usuario.getId());
			Query query = manager.createNamedQuery(Constantes.LISTA_COMPRAS_LISTA_COMPRAS_POR_USUARIO, ListaCompras.class);
			query.setParameter("pLogin", usuario.getLogin());	
		
			lu = query.getResultList();
			if(lu != null && lu.size() > 0)
				lu.stream().forEach(registro -> manager.remove(registro));
			
			manager.remove(usuario);
			
		}catch (NoResultException e) {
			lu = null;
		}finally {
			manager.getTransaction().commit();
			manager.close();
		}
    }
    
    
    public void alterarUsuario(Usuario usuario) throws SQLException {
		EntityManager manager = JPAEntityManager.getEntityManager();
		manager.getTransaction().begin();
		manager.merge(usuario);

		manager.flush();
		manager.getTransaction().commit();
		manager.close();
    }
    
}
