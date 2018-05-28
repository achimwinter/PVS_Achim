package assignment8.dataServices;

import assignment8.data.Message;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.*;
import java.net.URI;

@Path("messages")
public class MessageService {

    @GET
    public Response getAllMessages() {
        return Response.ok(new Message("hallo")).build();
    }


}
