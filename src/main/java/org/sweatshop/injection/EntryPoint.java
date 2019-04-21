package org.sweatshop.injection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class EntryPoint {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response homePage() {
        return Response.ok("foo").build();
    }

}
