package app.config.beans;

import app.entities.Invitation;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.Map;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Invitation.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings((Map) configuration);
                sessionFactory = configuration.buildSessionFactory(builder.build());
            }
        }
    }


}
