package assignment8.data;

import java.util.LinkedList;
import java.util.List;


public class CommentManager {


    private List<Comment> comments = new LinkedList<>();
    private Integer id = 0;

    private static volatile assignment8.data.CommentManager instance;
    private static Object mutex = new Object();

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


    public int addComment(Comment comment) {
        if (comment.getMessage() == null)
            throw new RuntimeException("posted Comment without Message");
        comment.setId(this.id);
        comments.add(comment);
        this.id += 2;
        return this.id--;
    }

    public Comment getComment(final int id) {
        return comments.get(id);
    }

    public List<Comment> getAllComments(final int id) {
        List<Comment> messageComments = new LinkedList<>();

        for (final Comment comment : messageComments){
            if (comment.getMessage().getId() == id)
                messageComments.add(comment);
        }

        return this.comments;
    }

    public void deleteComment(final int id) {
        comments.remove(id);
    }
}


