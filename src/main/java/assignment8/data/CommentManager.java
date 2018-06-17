package assignment8.data;

import assignment8.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;


public class CommentManager {


    private static final Object mutex = new Object();
    private static volatile assignment8.data.CommentManager instance;

    private CommentManager() {
    }

    public static assignment8.data.CommentManager getInstance() {
        assignment8.data.CommentManager result = instance;
        if (result == null) {
            synchronized (mutex) {
                result = instance;
                if (result == null)
                    instance = result = new assignment8.data.CommentManager();
            }
        }
        return result;
    }


    public int addComment(final Comment comment) {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        session.persist(comment);
        tx.commit();
        session.flush();
        session.close();
        return comment.getId();
    }

    public void modifyComment(final int oldCommentId, final Comment newComment) {
        Session session = HibernateUtil.getSession();
        Message oldComment = (Message) session.get(Message.class, oldCommentId);
        session.delete(oldComment);
        newComment.setId(oldCommentId);
        session.persist(newComment);
        session.close();
    }

    public Comment getComment(final int id) {
        return (Comment) HibernateUtil.getSession().get(Comment.class, id);
    }

    public List getAllComments(final int id) {
        Session session = HibernateUtil.getSession();
        session.createCriteria(Comment.class)
                .add(Restrictions.eq("message.id", MessageManager.getInstance().getMessage(id)));

        try {
            return session.createCriteria(Comment.class).list();
        } catch (Exception e) {
            return new ArrayList<Comment>();
        } finally {
            session.close();
        }
    }

    public void deleteComment(final int id) {
        HibernateUtil.getSession().delete(HibernateUtil.getSession().get(Comment.class, id));
    }
}


