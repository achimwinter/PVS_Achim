package assignment8.main;

import assignment8.linkutils.Hyperlinks;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;


@Path("")
public class StartService {

    @Context
    private UriInfo uriInfo;

    @GET
    public Response getDispatcher() {
        final Response.ResponseBuilder builder = Response.ok();

        Hyperlinks.addLink(this.uriInfo, builder, "/zickzack/api/users", "GET/getAllPersons", MediaType.APPLICATION_JSON);
        Hyperlinks.addLink(this.uriInfo, builder, "/zickzack/api/messages", "GET/getAllMessages", MediaType.APPLICATION_JSON);

        return builder.build();
    }

}


