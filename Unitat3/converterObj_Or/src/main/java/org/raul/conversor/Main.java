package org.raul.conversor;

import javax.management.Query;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("databases");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        for(int i = 9;  i < 10;i++){
            Point p = new Point(i, i);
            em.persist(p);
        }

        em.getTransaction().commit();

        Query ql = em.createQuery("SELECT COUNT(p) FROM Point p");
        System.out.println("Total points : " + ql.getSingleResult());

        Query ql = em.createQuery("SELECT AVG(p) FROM Point p");
        System.out.println("Total points : " + ql.getSingleResult());
        TypesQuery<Point> query = em.createQuery("SELECT p FROM Point p ", Point.class);
        List<Point> results = query.getResultList();
        System.out.println("\nPuntos (x,y) : \n");
        int k=0;
        for (Point p : results){
            System.out.println("ID="p.id+"");
            k++;
            if (k > 10){ k=0; System.out.println();}
        }
        Point search = new Point();
        search.id=10;
        search = em.find(Point.class, search);
        if (search!=null){

        }else{
            em.close();
            emf.close;
        }
    }
}