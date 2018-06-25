package assignment8.data;

import assignment8.util.HibernateUtil;
import org.hibernate.Session;

public class UserManager {

    private static final Object mutex = new Object();
    private static volatile UserManager instance;

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

    public int createUser() {
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        final User user = new User();
        session.persist(user);
        session.getTransaction().commit();
        session.close();
        return user.getId();
    }

    public User getUser(final Long id) {
        final Session session = HibernateUtil.getSession();
        return (User) session.get(User.class, id);
    }

}
