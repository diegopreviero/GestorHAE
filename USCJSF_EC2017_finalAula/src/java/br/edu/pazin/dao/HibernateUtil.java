/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.pazin.dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author apazi
 */
public class HibernateUtil {
     private static final SessionFactory sessionFactory;
    
    static {
        try {
            Configuration cfg = new Configuration().configure();
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                    .applySettings(cfg.getProperties());
            sessionFactory =  cfg.buildSessionFactory(builder.build());
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("HibernteUtil: Falha ao criar a sessão inicial." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
