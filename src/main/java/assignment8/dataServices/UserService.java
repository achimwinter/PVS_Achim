package assignment8.dataServices;


import assignment8.data.UserManager;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
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
    public Response createUser() {
        final int id = UserManager.getInstance().createUser();

        final URI locationURI = uriInfo.getAbsolutePathBuilder().path(Integer.toString(id)).build(new Object[0]);

        final Response.ResponseBuilder builder = Response.created(locationURI);

        return builder.build();
    }

}
