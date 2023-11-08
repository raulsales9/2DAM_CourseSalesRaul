package com.example.ad.usingorm.socialnetwork;

import org.hibernate.Session;
import com.example.ad.usingorm.entities.HibernateUtil;

/**
 *
 * @author pc-raul
 */

class Socialmedia {
    public static void main(String[] args) {
        Session laSesion = HibernateUtil.getSessionFactory().getCurrentSession();
        //logica de insercion
    }

}
