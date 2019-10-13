package connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author ALEXANDRE JOSE DOS SANTOS
 */
public class JPAEntityManager {

    private static final String PU_NAME = "listaProdutoPU";
    private static EntityManagerFactory emf = null;

    public static EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory(PU_NAME);
        }
        return emf.createEntityManager();
    }
}
