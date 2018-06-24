package assignment8.data;

import assignment8.util.HibernateUtil;
import org.hibernate.Session;

public class UserManager {

    private static volatile UserManager instance;
    private static final Object mutex = new Object();

    private UserManager() {
    }

    public static UserManager getInstance() {
        UserManager result = instance;
        if (result == null) {
            synchronized (mutex) {
                result = instance;
                if (result == null)
                    instance = result = new UserManager();
            }
        }
        return result;
    }

    public Long createUser() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        User user = new User();
        session.persist(user);
        session.getTransaction().commit();
        session.close();
        return user.getId();
    }

    public User getUser(final Long id) {
        Session session = HibernateUtil.getSession();
        return session.get(User.class, id);
    }

}
