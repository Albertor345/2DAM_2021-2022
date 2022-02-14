package configuration;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.inject.Singleton;

@Singleton
public class HibernateConfig {
    private static SessionFactory ourSessionFactory;

    public HibernateConfig() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
            System.out.println("hibernate config done");
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public  Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }
}
