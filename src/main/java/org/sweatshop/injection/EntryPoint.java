package org.sweatshop.injection;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.process.internal.RequestScoped;

@Path("/")
@RequestScoped
public class EntryPoint {

    @Inject
    public StringHolder stringHolder;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response homePage() {
        return Response.ok(stringHolder.getString()).build();
    }

}