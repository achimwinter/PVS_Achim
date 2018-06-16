package assignment8.util;

import assignment8.data.Message;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class HibernateUtil {

    //Annotation based configuration
    private static SessionFactory sessionAnnotationFactory;


    private static SessionFactory buildSessionAnnotationFactory() {
        try {
            Configuration configuration = new Configuration();
            System.out.println("trying to load hibernate config");
            configuration.configure("hibernate.cfg.xml");
            configuration.addAnnotatedClass(Message.class);
            System.out.println("Hibernate Annotation Configuration loaded");

            final ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate Annotation serviceRegistry created");

            final SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            return sessionFactory;
        } catch (final Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }


    public static SessionFactory getSessionAnnotationFactory() {
        if (sessionAnnotationFactory == null)
            sessionAnnotationFactory = buildSessionAnnotationFactory();
        return sessionAnnotationFactory;
    }


}
