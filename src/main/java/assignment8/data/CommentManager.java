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


    public Long addComment(final Comment comment) {
        final Session session = HibernateUtil.getSession();
        final Transaction tx = session.beginTransaction();
        session.saveOrUpdate(comment);
        tx.commit();
        session.flush();
        session.close();
        return comment.getId();
    }

    public void modifyComment(final Long oldCommentId, final Comment newComment) {
        final Session session = HibernateUtil.getSession();
        final Message oldComment = (Message) session.get(Message.class, oldCommentId);
        session.delete(oldComment);
        newComment.setId(oldCommentId);
        session.persist(newComment);
        session.close();
    }

    public Comment getComment(final Long id) {

        return (Comment) HibernateUtil.getSession().get(Comment.class, id);
    }

    public List getAllComments(final Long id) {
        final Session session = HibernateUtil.getSession();
        try {

            session.createCriteria(Comment.class)
                    .add(Restrictions.eq("message.id", MessageManager.getInstance().getMessage(id)));
            return session.createCriteria(Comment.class).list();
        } catch (final Exception e) {
            return new ArrayList<Comment>();
        } finally {
            session.close();
        }
    }

    public void deleteComment(final Long id) {
        HibernateUtil.getSession().delete(HibernateUtil.getSession().get(Comment.class, id));
    }
}


