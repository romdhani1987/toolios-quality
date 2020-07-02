package fr.romdhani.aymen.toolios.core.dao;

import fr.romdhani.aymen.toolios.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.function.Consumer;


public class ExecutionContext {
    private Session session = HibernateUtil.getSessionFactory().openSession();

    private static class ExExecutionContextInstance {
        public static final ExecutionContext executionContext = new ExecutionContext();
    }

    private ExecutionContext() {

    }

    public static ExecutionContext getInstance() {
        return ExExecutionContextInstance.executionContext;
    }

    public boolean tryInTransaction(Session session, Consumer<Session> Consumer) {
        boolean isSuccess = false;
        try {
            // create session
            this.session = session;
            Consumer.accept(this.session);
            Transaction tx = this.session.beginTransaction();
            // do something
            if (!tx.wasCommitted()) {
                tx.commit();
            }
            tx.rollback();
            // close session
            try {
                if (this.session  != null && this.session .isOpen()) this.session .close();
            } catch (Exception e) {
                System.err.println(e);
            }
            isSuccess = true;
        } catch (Exception e) {
            System.err.println(e);
        }
        return isSuccess;
    }

    public boolean tryInTransaction(Consumer<Session> Consumer) {
        return tryInTransaction(session, Consumer);
    }


}
