package com.example.ad.usingorm.model;

import java.io.Serializable;

class hibernate implements Serializable {
    // get a Session and start a transaction
Session laSesion=HibernateUtil.getSessionFactory().getCurrentSession();
laSesion.getTransaction().begin();

// Create new object
Peli p=new Peli("Piratas del caribe", 2003, "Gore Verbinsky");
System.out.println("Unsaved: "+ p);

 // save in the database
Long idNueva=(Long)laSesion.save(p);

 // Get the saved object (with another varaible)
 Peli q=laSesion.get(Peli.class, idNueva);

System.out.println("Saved: " + q);

17 //close all
18 laSesion.getTransaction().commit();
19 laSesion.close();
}