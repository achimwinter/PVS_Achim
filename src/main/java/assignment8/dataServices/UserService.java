package assignment8.dataServices;


import assignment8.data.UserManager;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.*;
import java.net.URI;

@Path("users")
public class UserService {

    @Context
    protected UriInfo uriInfo;

    @Context
    protected ContainerRequestContext requestContext;

    @Context
    protected Request request;

    @Context
    protected HttpServletRequest httpServletRequest;


    @GET
    @Path("{id}")
    public Response getUserByID(@PathParam("id") final int id) {
        return Response.ok(UserManager.getInstance().getUser(id)).build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser() {
        final Long id = UserManager.getInstance().createUser();

        final URI locationURI = uriInfo.getAbsolutePathBuilder().path(Long.toString(id)).build();

        final Response.ResponseBuilder builder = Response.created(locationURI);

        return builder.build();
    }

}
