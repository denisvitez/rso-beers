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
@Path("/beers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BeersResource {

    @Inject
    private BeersBean bean;

    @Context
    protected UriInfo uriInfo;

    @GET
    public Response getBeers() {
        List<Beer> beers = bean.getBeers();
        return Response.ok(beers).build();
    }

    @GET
    @Path("/{beerId}")
    public Response getBeer(@PathParam("beerId") Integer beerId) {
        Beer b = bean.getBeer(beerId);
        if (b == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(b).build();
    }

    @GET
    @Path("/filtered")
    public Response getBeersFiltered() {
        List<Beer> beers;
        beers = bean.getBeersFilter(uriInfo);
        return Response.status(Response.Status.OK).entity(beers).build();
    }

    @POST
    public Response createBeer(Beer beer) {
        if (beer.getName() == null || beer.getStyle() == null || beer.getAlcohol() <= 0 || beer.getBreweryId() <= 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            beer = bean.createBeer(beer);
        }
        //Handle success/failure
        if (beer.getId() != null) {
            return Response.status(Response.Status.CREATED).entity(beer).build();
        } else {
            return Response.status(Response.Status.CONFLICT).entity(beer).build();
        }
    }

    @PUT
    @Path("{beerId}")
    public Response putBrewer(@PathParam("beerId") Integer beerId, Beer b) {
        b = bean.putBeer(beerId, b);
        if (b == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            if (b.getId() != null)
                return Response.status(Response.Status.OK).entity(b).build();
            else
                return Response.status(Response.Status.NOT_MODIFIED).build();
        }
    }

    @DELETE
    @Path("{beerId}")
    public Response deleteBrewer(@PathParam("beerId") int beerId) {
        boolean deleted = bean.deleteBeer(beerId);
        if (deleted) {
            return Response.status(Response.Status.GONE).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
