package app.mapin.resource;


import app.mapin.model.WayPoint;
import app.mapin.service.WayPointService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
public class WayPointResource {

    private WayPointService wayPointService = new WayPointService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<WayPoint> getAllWayPoints(@PathParam("floorplanid") String floorPlanId) {
        System.out.println(floorPlanId);
        return wayPointService.getAllWayPoints(floorPlanId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public WayPoint addWayPoint(@PathParam("floorplanid") String floorPlanId, WayPoint wayPoint) {
        return wayPointService.addWaypoint(floorPlanId, wayPoint);
    }

    @PUT
    @Path("/{waypointid}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public WayPoint updateWayPoint(@PathParam("floorplanid") String floorPlanId,
                               @PathParam("waypointid") String waypointId,
                               WayPoint wayPoint) {
        wayPoint.setWayPointId(waypointId);
        return wayPointService.updateWayPoint(floorPlanId, wayPoint);
    }

    @DELETE
    @Path("/{waypointid}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public int removeWayPoint(@PathParam("floorplanid") String floorPlanId,
                              @PathParam("waypointid") String wayPointId) {
        return wayPointService.removeWayPoint(floorPlanId, wayPointId);
    }

}
