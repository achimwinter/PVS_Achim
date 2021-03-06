package assignment8.dataServices;

import assignment8.data.*;
import assignment8.util.Hyperlinks;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("messages")
public class MessageService {

    private final MessageManager manager = MessageManager.getInstance();

    @Context
    protected UriInfo uriInfo;


    @GET
    @Path("{id : \\d+}")
    public Response getMessageByID(@PathParam("id") final Long id) {
        final Response.ResponseBuilder builder = Response.ok(manager.getMessage(id));

        Hyperlinks.addLink(uriInfo, builder, "zickzack/api/messages/" + id, "PUT/modifyText", MediaType.APPLICATION_JSON);
        Hyperlinks.addLink(uriInfo, builder, "zickzack/api/messages/" + id + "/votes", "POST/vote", MediaType.APPLICATION_JSON);
        Hyperlinks.addLink(uriInfo, builder, "zickzack/api/messages/" + id, "DELETE/deleteMessage", MediaType.APPLICATION_JSON);
        Hyperlinks.addLink(uriInfo, builder, "zickzack/api/messages/" + id + "/comments", "GET/getComments", MediaType.APPLICATION_JSON);

        return builder.build();
    }

    @GET
    public Response getAllMessages() {
        System.out.println("rest reached");
        final List messagesList = manager.getAllMessages();

        final Response.ResponseBuilder builder = Response.ok(messagesList);

        Hyperlinks.addLink(this.uriInfo, builder, "/zickzack/api/messages/", "GET/getMessage", MediaType.APPLICATION_JSON);

        return builder.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createMessage(final Message message) {
        final Response.ResponseBuilder builder = Response.ok();

        final Long i = manager.addMessage(message);

        Hyperlinks.addLink(this.uriInfo, builder, "/zickzack/api/messages/" + i, "GET/getMessage", MediaType.APPLICATION_JSON);

        return builder.build();
    }

    @PUT
    @Path("{id: \\d+}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putMessage(@PathParam("id") final Long id, final Message newMessage) {
        manager.modifyComment(id, newMessage);

        return Response.ok().build();
    }


    @POST
    @Path("{id: \\d+}/votes/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response voteMessage(@PathParam("id") final Long id, final Vote vote) {
        final Response.ResponseBuilder builder = Response.ok();

        final Message message = manager.getMessage(id);

        if (vote.isPositive())
            message.incrementVotes();
        else
            message.decrementVotes();

        Hyperlinks.addLink(this.uriInfo, builder, "/zickzack/api/messages/" + id, "GET/getMessage", MediaType.APPLICATION_JSON);

        return Response.ok().build();
    }

    @DELETE
    @Path("{id : \\d+}")
    public Response deleteMessage(@PathParam("id") final Long id) {
        final Response.ResponseBuilder builder = Response.ok();

        manager.deleteMessage(id);

        Hyperlinks.addLink(this.uriInfo, builder, "/zickzack/api/messages/", "GET/getMessages", MediaType.APPLICATION_JSON);
        return Response.ok().build();
    }

    @GET
    @Path("{id : \\d+}/comments")
    public Response getComments(@PathParam("id") final Long id) {
        final Response.ResponseBuilder builder = Response.ok(CommentManager.getInstance().getAllComments(id));

        Hyperlinks.addLink(this.uriInfo, builder, "/zickzack/api/messages/" + id + "/comments", "POST/postComment", MediaType.APPLICATION_JSON);
        Hyperlinks.addLink(this.uriInfo, builder, "/zickzack/api/messages/" + id + "/comments/", "GET/getComment", MediaType.APPLICATION_JSON);

        return builder.build();
    }

    @POST
    @Path("{id : \\d+}/comments")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createComment(final Comment comment) {
        final Response.ResponseBuilder builder = Response.ok();

        final Long id = CommentManager.getInstance().addComment(comment);

        Hyperlinks.addLink(this.uriInfo, builder, "/zickzack/api/messages/" + comment.getComment_message() + "/comments/" + id, "GET/getComment", MediaType.APPLICATION_JSON);

        return builder.build();
    }

    @DELETE
    @Path("{messageId: \\d}/comments/{commentId: \\d}")
    public Response deleteComment(@PathParam("messageId") final Long messageId, @PathParam("commentId") final Long commentId) {
        final Response.ResponseBuilder builder = Response.ok();

        Hyperlinks.addLink(this.uriInfo, builder, "/zickzack/api/messages/" + messageId + "/comments/" + commentId, "GET/getComment", MediaType.APPLICATION_JSON);

        CommentManager.getInstance().deleteComment(commentId);
        return builder.build();
    }

    @PUT
    @Path("{messageId: \\d}/comments/{commentId: \\d}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putComment(@PathParam("messageId") final Long messageId, @PathParam("commentId") final Long commentId, final Comment newComment) {
        CommentManager.getInstance().modifyComment(commentId, newComment);
        final Response.ResponseBuilder builder = Response.ok();

        Hyperlinks.addLink(this.uriInfo, builder, "/zickzack/api/messages/" + messageId + "/comments/" + commentId, "GET/getComment", MediaType.APPLICATION_JSON);

        return Response.ok().build();
    }

    @GET
    @Path("{messageId: \\d}/comments/{commentId: \\d}")
    public Response getComment(@PathParam("messageId") final Long messageId, @PathParam("commentId") final Long commentId) {
        final Response.ResponseBuilder builder = Response.ok();

        Hyperlinks.addLink(this.uriInfo, builder, "/zickzack/api/messages/" + messageId + "/comments/" + commentId, "DELETE/deleteComment", MediaType.APPLICATION_JSON);
        Hyperlinks.addLink(this.uriInfo, builder, "/zickzack/api/messages/" + messageId + "/comments/" + commentId, "PUT/modifyComment", MediaType.APPLICATION_JSON);
        Hyperlinks.addLink(this.uriInfo, builder, "/zickzack/api/messages/" + messageId + "/comments/" + commentId, "POST/voteComment", MediaType.APPLICATION_JSON);


        return Response.ok(CommentManager.getInstance().getComment(commentId)).build();
    }

    @POST
    @Path("{messageId: \\d}/comments/{commentId: \\d}/votes")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response voteComment(@PathParam("commentId") final Long commentId, final Vote vote) {
        final Response.ResponseBuilder builder = Response.ok();

        final Comment comment = CommentManager.getInstance().getComment(commentId);

        if (vote.isPositive())
            comment.incrementVotes();
        else
            comment.decrementVotes();

        Hyperlinks.addLink(this.uriInfo, builder, "/zickzack/api/messages/" + comment.getComment_message() + "/comments/" + commentId, "GET/getComment", MediaType.APPLICATION_JSON);

        return Response.ok().build();
    }

}
