
package com.raul.postgresql1a.objectdbraul_2a;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

/**
 *
 * @author rauls
 */

public class Objectdbraul_2a {

    public static void main(String[] args) {
        EntityManagerFactory emf;
        EntityManager em;
        emf  = Persistence.createEntityManagerFactory("testBD.odb");
        try {
            em = emf.createEntityManager();
            System.out.println("DB was created");
            em.close();
        }
        catch (PersistenceException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
