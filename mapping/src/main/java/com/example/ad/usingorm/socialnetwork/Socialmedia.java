package com.example.ad.usingorm.socialnetwork;

import org.hibernate.Session;

/**
 *
 * @author pc-raul
 */

class Socialmedia {
    public static void main (String[] args){
        Session laSesion=HibernateUtil.getSessionFactory().getCurrentSession();
    }
}
