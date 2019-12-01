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

public class ListaDAO {

	public void cadastrar(ListaCompras compras) throws SQLException {
		EntityManager manager = JPAEntityManager.getEntityManager();
		manager.getTransaction().begin();
		manager.persist(compras);
		manager.flush();
		manager.getTransaction().commit();
		manager.close();
		
	}

	public void addProdutoNaLista(ListaCompras compras) throws SQLException {
 
		EntityManager manager = JPAEntityManager.getEntityManager();
		manager.getTransaction().begin();
		manager.merge(compras);

		manager.flush();
		manager.getTransaction().commit();
		manager.close();
	}


	public void alterarLista(ListaCompras compras) throws SQLException {
		
		EntityManager manager = JPAEntityManager.getEntityManager();
		manager.getTransaction().begin();
		manager.merge(compras);
		manager.getTransaction().commit();
		manager.close();
	}

	public void excluirProduto(ListaCompras compras) throws SQLException {
		EntityManager manager = JPAEntityManager.getEntityManager();
		manager.getTransaction().begin();
		manager.merge(compras);
		manager.getTransaction().commit();
		manager.close();
	}

	public void excluirLista(ListaCompras compras) throws SQLException {
		EntityManager manager = JPAEntityManager.getEntityManager();
		manager.getTransaction().begin();
		compras = manager.find(ListaCompras.class, compras.getId());
		manager.remove(compras);
		manager.getTransaction().commit();
		manager.close();
	}

	public List<ListaCompras> listar(Usuario usuario) throws SQLException {

		List<ListaCompras> lu = null;

		EntityManager manager = JPAEntityManager.getEntityManager();
		Query query = manager.createNamedQuery(Constantes.LISTA_COMPRAS_LISTA_COMPRAS_POR_USUARIO, ListaCompras.class);
		
		query.setParameter("pLogin", usuario.getLogin());	
		
		try {
			lu = query.getResultList();
		}catch (NoResultException e) {
			lu = null;
		}finally {
			manager.close();
		}
		return lu;
	}

	public ListaCompras consultar(Long idCompras) {
		
		EntityManager manager = JPAEntityManager.getEntityManager();
		ListaCompras registro = manager.find(ListaCompras.class, idCompras);
		manager.close();
		
		return registro;
	}

}
