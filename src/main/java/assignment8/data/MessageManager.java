package assignment8.data;

import assignment8.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.LinkedList;
import java.util.List;

public class MessageManager {

    private static final Object mutex = new Object();
    private static volatile MessageManager instance;
    final SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
    final Session session = sessionFactory.getCurrentSession();
    private final List<Message> messages = new LinkedList<>();

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
        messages.remove(oldMessageId);
        newMessage.setId(oldMessageId);
        messages.add(newMessage);
    }


    public int addMessage(Message message) {
        session.beginTransaction();
        session.save(message);
        session.getTransaction().commit();
        return message.getId();
    }

    public Message getMessage(final int id){
        return messages.get(id);
    }

    public List<Message> getAllMessages(){
        return this.messages;
    }

    public void deleteMessage(final int id){
        messages.remove(id);
    }

}
