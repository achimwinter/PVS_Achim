package assignment8.dataServices;


import assignment8.data.User;
import assignment8.data.UserManager;
import assignment8.linkutils.Hyperlinks;
import com.owlike.genson.Genson;


import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.*;
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
        final Response.ResponseBuilder builder = Response.ok();

        final int id = UserManager.getInstance().createUser();

        Hyperlinks.addLink(this.uriInfo, builder, uriInfo.getAbsolutePath().toString() + "/" + id, "GET/getUser", MediaType.APPLICATION_JSON);

        return builder.build();
    }

}
