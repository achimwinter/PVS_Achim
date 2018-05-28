package assignment8.dataServices;


import assignment8.data.UserManager;


import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;

@Path("users")
public class UserService {

    private final UserManager userManager = UserManager.getInstance();

    @Context
    protected UriInfo uriInfo;

    @Context
    protected ContainerRequestContext requestContext;

    @Context
    protected Request request;

    @Context
    protected HttpServletRequest httpServletRequest;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        userManager.createUser();
        return Response.ok(userManager.getUser(0)).build();
    }

    @GET
    @Path("{id}")
    public Response getUserByID(@PathParam("id") final int id) {
        return Response.ok(userManager.getUser(id)).build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser() {
        Integer id = userManager.createUser();
        final URI locationURI = uriInfo.getAbsolutePathBuilder().path(id.toString()).build(new Object[0]);

        return Response.created(locationURI).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteUser(@PathParam("id") final int id) {
        userManager.deleteUser(id);

        return Response.noContent().build();
    }

}
