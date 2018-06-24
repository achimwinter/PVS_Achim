package assignment8.dataServices;


import assignment8.data.User;
import assignment8.data.UserManager;
import assignment8.util.HibernateUtil;
import org.hibernate.Session;

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
    public Response getUserByID(@PathParam("id") final Long id) {
        return Response.ok(UserManager.getInstance().getUser(id)).build();
    }


    @POST
    public Response createUser() {
        Session session = HibernateUtil.getSession();
        User user = new User();

        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
        Long id = user.getId();

        final URI locationURI = uriInfo.getAbsolutePathBuilder().path(Long.toString(id)).build();

        final Response.ResponseBuilder builder = Response.created(locationURI);

        return builder.build();
    }

}
