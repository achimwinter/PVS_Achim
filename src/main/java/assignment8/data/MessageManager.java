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

    public void modifyComment(final Long oldMessageId, final Message newMessage) {
        final Session session = HibernateUtil.getSession();
        final Transaction tx = session.beginTransaction();
        Message oldMessage = (Message) session.get(Message.class, oldMessageId);

        oldMessage.setText(newMessage.getText());
        oldMessage.setVotes(newMessage.getVotes());
        oldMessage.setComments(newMessage.getComments());

        session.save(oldMessage);
        tx.commit();
        session.close();
    }


    public Long addMessage(final Message message) {
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(message);
        session.getTransaction().commit();
        session.close();
        return message.getId();
    }

    public Message getMessage(final Long id) {
        return (Message) HibernateUtil.getSession().get(Message.class, id);
    }

    public List getAllMessages() {
        Session session = HibernateUtil.getSession();
        try {
            return session.createCriteria(Message.class).list();
        } catch (final Exception e) {
            return new ArrayList<Message>();
        } finally {
            session.close();
        }
    }

    public void deleteMessage(final Long id) {
        HibernateUtil.getSession().delete(HibernateUtil.getSession().get(Message.class, id));
    }

}
