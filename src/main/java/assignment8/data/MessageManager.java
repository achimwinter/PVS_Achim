package assignment8.data;

import assignment8.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class MessageManager {

    private static final Object mutex = new Object();
    private static volatile MessageManager instance;
    private final Session session = HibernateUtil.getSession();

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
        Message oldMessage = (Message) session.get(Message.class, oldMessageId);
        session.delete(oldMessage);
        newMessage.setId(oldMessageId);
        session.persist(newMessage);
    }


    public int addMessage(Message message) {
        Transaction tx = session.beginTransaction();
        session.persist(message);
        tx.commit();
        return message.getId();
    }

    public Message getMessage(final int id) {
        return (Message) session.get(Message.class, id);
    }

    public List getAllMessages() {
        try {
            return session
                    .createCriteria(Message.class).list();
        } catch (Exception e) {
            return new ArrayList<Message>();
        }
    }

    public void deleteMessage(final int id) {
        session.delete(session.get(Message.class, id));
    }

}
