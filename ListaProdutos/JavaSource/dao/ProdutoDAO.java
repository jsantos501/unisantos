package dao;


import java.sql.SQLException;

import javax.persistence.EntityManager;
import connection.JPAEntityManager;
import model.ListaCompras;

/**
*
* @author ALEXANDRE JOSE DOS SANTOS
*/

public class ProdutoDAO {

    public void listar(ListaCompras compras) throws SQLException {

		EntityManager manager = JPAEntityManager.getEntityManager();
		compras = manager.find(ListaCompras.class, compras);
		manager.close();
	}
	
}
