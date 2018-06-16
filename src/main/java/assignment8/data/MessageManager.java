package assignment8.data;

import assignment8.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class MessageManager {

    private static final Object mutex = new Object();
    private static volatile MessageManager instance;

    private MessageManager() {
    }

    public static MessageManager getInstance() {
        MessageManager result = instance;
        if (result == null) {
            synchronized (mutex) {
                result = instance;
                if (result == null) {
                    instance = result = new MessageManager();
                }
            }
        }
        return result;
    }

    public void modifyComment(final int oldMessageId, final Message newMessage) {
        Session session = HibernateUtil.getSession();
        Message oldMessage = (Message) session.get(Message.class, oldMessageId);
        session.delete(oldMessage);
        newMessage.setId(oldMessageId);
        session.persist(newMessage);
        session.close();
    }


    public int addMessage(Message message) {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        session.persist(message);
        tx.commit();
        session.close();
        return message.getId();
    }

    public Message getMessage(final int id) {
        return (Message) HibernateUtil.getSession().get(Message.class, id);
    }

    public List getAllMessages() {
        Session session = HibernateUtil.getSession();
        try {
            return session.createCriteria(Message.class).list();
        } catch (Exception e) {
            return new ArrayList<Message>();
        } finally {
            session.close();
        }
    }

    public void deleteMessage(final int id) {
        HibernateUtil.getSession().delete(HibernateUtil.getSession().get(Message.class, id));
    }

}
