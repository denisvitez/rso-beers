package si.fri.rso.sn.beers.api.v1.resources;

import com.kumuluz.ee.logs.cdi.Log;
import si.fri.rso.sn.beers.models.entities.Beer;
import si.fri.rso.sn.beers.services.beans.BeersBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Log
@ApplicationScoped
@Path("/scores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BeersResource {

    @Inject
    private BeersBean beersBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    public Response getBeers() {

        List<Beer> scores = beersBean.getBeers();

        return Response.ok(scores).build();
    }
}
