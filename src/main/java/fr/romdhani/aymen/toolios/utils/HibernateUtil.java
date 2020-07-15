package fr.romdhani.aymen.toolios.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("deprecation")
public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    private Session session;

    static {
        try {
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Session Factory could not be created." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static boolean execQuery(Object object) {
        try {
            Transaction localTransaction = getSession().getTransaction();
            localTransaction.begin();
            getSession().save(object);
            localTransaction.commit();
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return true;
        }
    }
}
