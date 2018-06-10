package assignment8.dataServices;

import assignment8.data.Message;
import assignment8.data.MessageManager;
import assignment8.data.Vote;
import assignment8.linkutils.Hyperlinks;
import com.owlike.genson.Genson;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.*;
import java.util.List;

@Path("messages")
public class MessageService {

    MessageManager manager = MessageManager.getInstance();

    @Context
    protected UriInfo uriInfo;

    @Context
    protected ContainerRequestContext requestContext;

    @Context
    protected Request request;

    @Context
    protected HttpServletRequest httpServletRequest;

    @GET
    @Path("{id : \\d+}")
    public Response getMessageByID(@PathParam("id") final int id) {
        final Response.ResponseBuilder builder = Response.ok(manager.getMessage(id));

        Hyperlinks.addLink(uriInfo, builder, "zickzack/api/messages/" + id, "PUT/modifyText", MediaType.APPLICATION_JSON);
        Hyperlinks.addLink(uriInfo, builder, "zickzack/api/messages/" + id  + "/votes", "POST/vote", MediaType.APPLICATION_JSON);
        Hyperlinks.addLink(uriInfo, builder, "zickzack/api/messages/" + id, "DELETE/deleteMessage", MediaType.APPLICATION_JSON);
        Hyperlinks.addLink(uriInfo, builder, "zickzack/api/messages/" + id + "/comments", "GET/getComments", MediaType.APPLICATION_JSON);

        return builder.build();
    }

    @GET
    public Response getAllMessages() {
        final List<Message> messagesList = manager.getAllMessages();

        final Response.ResponseBuilder builder = Response.ok(messagesList);

        Hyperlinks.addLink(this.uriInfo, builder, "/zickzack/api/messages/", "GET/getMessage", MediaType.APPLICATION_JSON);


        return builder.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createMessage(final Message message){
        final Response.ResponseBuilder builder = Response.ok();

        final int i = manager.addMessage(message);

        Hyperlinks.addLink(this.uriInfo, builder, "/zickzack/api/messages/" + i, "GET/getMessage", MediaType.APPLICATION_JSON);

        return builder.build();
    }

    @PUT
    @Path("{id: \\d+}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modifyText(@PathParam("id") final int id, final Message message){
        manager.getMessage(id).setText(message.getText());
        return Response.ok().build();
    }

    @POST
    @Path("{id: \\d+}/votes/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response vote(@PathParam("id") final int id, final Vote vote){
        System.out.println("reached");
        Genson genson = new Genson();
        System.out.println(genson.serialize(vote));
        Message message = manager.getMessage(id);

        if (vote.getVoteType().isVotePositive())
            message.setVotes(message.getVotes() + 1);
        else
            message.setVotes(message.getVotes() - 1);

        return Response.ok().build();
    }

    @DELETE
    @Path("{id : \\d+}")
    public Response deleteMessage(@PathParam("id") final int id){
        final Response.ResponseBuilder builder = Response.ok();

        manager.deleteMessage(id);

        Hyperlinks.addLink(this.uriInfo, builder, "/zickzack/api/messages/", "GET/getMessages", MediaType.APPLICATION_JSON);
        return Response.ok().build();
    }
}
