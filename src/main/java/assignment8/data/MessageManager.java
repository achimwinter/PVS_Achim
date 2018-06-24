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
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        Message oldMessage = session.get(Message.class, oldMessageId);

        oldMessage.setText(newMessage.getText());
        oldMessage.setVotes(newMessage.getVotes());
        oldMessage.setComments(newMessage.getComments());

        session.save(oldMessage);
        tx.commit();
        session.flush();
        session.close();
    }


    public Long addMessage(Message message) {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(message);
        tx.commit();
        session.flush();
        session.close();
        return message.getId();
    }

    public Message getMessage(final Long id) {
        return HibernateUtil.getSession().get(Message.class, id);
    }

    public List getAllMessages() {
        final Session session = HibernateUtil.getSession();
        try {
            List test = session.createCriteria(Message.class).list();
            return test;
        } catch (Exception e) {
            return new ArrayList<Message>();
        } finally {
            session.close();
        }
    }

    public void deleteMessage(final Long id) {
        HibernateUtil.getSession().delete(HibernateUtil.getSession().get(Message.class, id));
    }

}
