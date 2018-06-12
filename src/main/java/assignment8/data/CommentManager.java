package assignment8.data;

import java.util.LinkedList;
import java.util.List;


public class CommentManager {


    private static final Object mutex = new Object();
    private static volatile assignment8.data.CommentManager instance;
    private final List<Comment> comments = new LinkedList<>();
    private Integer id = 0;

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
        comment.setId(this.id);
        comments.add(comment);
        this.id++;
        return this.id;
    }

    public void modifyComment(final int oldCommentId, final Comment newComment) {
        newComment.setId(oldCommentId);
        comments.remove(oldCommentId);
        comments.add(newComment);
    }

    public Comment getComment(final int id) {
        return comments.get(id);
    }

    public List<Comment> getAllComments(final int id) {
        final List<Comment> messageComments = new LinkedList<>();

        for (final Comment comment : messageComments){
            if (comment.getMessageId() == id)
                messageComments.add(comment);
        }

        return this.comments;
    }

    public void deleteComment(final int id) {
        comments.remove(id);
    }
}


