package assignment8.data;

import assignment8.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

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

    public int createUser() {
        Session session = HibernateUtil.getSession();
        User user = new User();
        Transaction tx = session.beginTransaction();
        session.persist(user);
        tx.commit();
        return user.getId();
    }

    public User getUser(final Integer id) {
        Session session = HibernateUtil.getSession();
        return (User) session.get(User.class, id);
    }

}
