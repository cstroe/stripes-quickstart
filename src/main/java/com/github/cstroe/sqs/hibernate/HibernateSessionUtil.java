package com.github.cstroe.sqs.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.resource.transaction.spi.TransactionStatus;

public class HibernateSessionUtil {
    private static final ThreadLocal<Session> registry;
    private static final ThreadLocal<Boolean> rollbackReg;
    private static SessionFactory sessionFactory;

    static {
        registry    = new ThreadLocal<>();
        rollbackReg = new ThreadLocal<>();
    }

    private HibernateSessionUtil() {}

    private static void buildSessionFactory() {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            buildSessionFactory();
        }
        return sessionFactory;
    }

    static void beginTransaction() {
        Session ses = getCurrentSession();

        if (ses == null) {
            registry.set(HibernateSessionUtil.getSessionFactory().openSession());
            rollbackReg.set(false);

            ses = getCurrentSession();
        }

        ses.beginTransaction();
    }

    static void rollbackOnly() {
        rollbackReg.set(true);
        getCurrentSession().clear();
    }


    public static Session getCurrentSession() {
        return registry.get();
    }

    public static void commitTransaction()
    {
        Session ses = getCurrentSession();
        Transaction trn = ses.getTransaction();

        if(trn.getStatus() == TransactionStatus.ACTIVE) {
            trn.commit();
        }
    }


    private static void rollbackTransaction()
    {
        System.out.println( "Rolling back transaction!" );

        getCurrentSession().getTransaction().rollback();
    }


    static void resolveTransaction()
    {
        if (rollbackReg.get())
            rollbackTransaction();
        else
            commitTransaction();
    }


    static void closeSession()
    {
        getCurrentSession().close();

        registry.set( null );
        rollbackReg.set( null );
    }
}