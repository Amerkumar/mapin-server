package app.mapin.resource;

import app.mapin.model.FloorPlan;
import app.mapin.service.FloorPlanService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("floors")
public class FloorPlanResource {

    FloorPlanService floorPlanService = new FloorPlanService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<FloorPlan> getFloors() {
        return floorPlanService.getAllFloors();
    }

    @GET
    @Path("{floorplanid}")
    @Produces(MediaType.APPLICATION_JSON)
    public FloorPlan getFloor(@PathParam("floorplanid") String floorPlanId) {
        System.out.println(floorPlanId);
        return floorPlanService.getFloor(floorPlanId);
    }

    @Path("{floorplanid}/waypoints")
    public WayPointResource getWayPointResource() {
        return new WayPointResource();
    }

    @Path("{floorplanid}/floorpaths")
    public FloorPathResource getFloorPathResource() {
        return new FloorPathResource();
    }
}
