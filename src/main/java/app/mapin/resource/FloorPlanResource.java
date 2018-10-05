package app.mapin.resource;

import app.mapin.model.Floor;
import app.mapin.service.FloorService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("floors")
public class FloorPlanResource {

    FloorService floorService = new FloorService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Floor> getFloors() {
        return floorService.getAllFloors();
    }

    @GET
    @Path("{floorplanid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Floor getFloor(@PathParam("floorplanid") String floorPlanId) {
        System.out.println(floorPlanId);
        return floorService.getFloor(floorPlanId);
    }

    @Path("{floorplanid}/waypoints")
    public WayPointResource getWayPointResource() {
        return new WayPointResource();
    }
}
