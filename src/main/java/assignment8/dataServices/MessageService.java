package assignment8.dataServices;

import assignment8.data.Message;
import assignment8.data.MessageManager;
import assignment8.linkutils.Hyperlinks;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.*;
import java.util.List;

@Path("messages")
public class MessageService {

    @Context
    protected UriInfo uriInfo;

    @Context
    protected ContainerRequestContext requestContext;

    @Context
    protected Request request;

    @Context
    protected HttpServletRequest httpServletRequest;



    @GET
    public Response getAllMessages() {
        final List<Message> messagesList = MessageManager.getInstance().getAllMessages();

        final Response.ResponseBuilder builder = Response.ok();

        for (Message message : messagesList){
            Hyperlinks.addLink(this.uriInfo, builder, "/zickzack/api/messages/" + message.getId(), "GET/getMessage", MediaType.APPLICATION_JSON);
        }



        Hyperlinks.addLink(this.uriInfo, builder, "/zickzack/api/messages/", "GET/getMessage", MediaType.APPLICATION_JSON);


        return builder.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createMessage(final Message message){
        final Response.ResponseBuilder builder = Response.ok();

        final int i = MessageManager.getInstance().addMessage(message);

        Hyperlinks.addLink(this.uriInfo, builder, "/zickzack/api/messages/" + i, "GET/getMessage", MediaType.APPLICATION_JSON);

        return builder.build();
    }
}
