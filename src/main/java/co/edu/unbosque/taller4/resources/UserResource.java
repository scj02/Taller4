package co.edu.unbosque.taller4.resources;

import co.edu.unbosque.taller4.dtos.User;
import co.edu.unbosque.taller4.services.UserService;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Path("/users")
public class UserResource {
    @Context
    ServletContext context;

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(@FormParam("name") String name,
                               @FormParam("lastname") String lastname,
                               @FormParam("username") String username,
                               @FormParam("password") String password,
                               @FormParam("role") String role) {

        String fcoins = "0";

        String path = context.getRealPath("") + File.separator + "WEB-INF/classes/" + "accounts.csv";
        User signup = null;
        try {
            signup = new UserService().createUser(name, lastname, username, password, role, fcoins, path).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (signup != null) {
            return Response.ok().entity(signup).build();
        }

        return Response.serverError().build();
    }

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("username") String username,
                            @QueryParam("password")String password) {
        try {
            List<User> users = new UserService().getUsers().get();

            User userfound = users.stream().filter(user -> username.equals(user.getUsername())&&password.equals(user.getPassword())).findFirst().orElse(null);

            if (userfound != null) {
                return Response.ok().entity(userfound).build();
            } else {
                return Response.status(404).build();
            }
        } catch (IOException e) {
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("/{username}/fcoins")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserFcoins(@PathParam("username") String username,
                                  @FormParam("fcoins") String fcoins) {

        String path = context.getRealPath("") + File.separator + "WEB-INF/classes/" + "accounts.csv";

        boolean update = new UserService().loadFcoins(username,path,fcoins).get();

        if(update){
            List<User> users = null;
            try {
                users = new UserService().getUsers().get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            User userfound = users.stream().filter(user -> username.equals(user.getUsername())).findFirst().orElse(null);
            if(userfound!=null){
                return Response.ok().entity(userfound).build();
            }
        }

        return Response.serverError().build();
    }
}