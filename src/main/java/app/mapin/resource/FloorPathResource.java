package app.mapin.resource;

import app.mapin.model.FloorPath;
import app.mapin.model.WayPoint;
import app.mapin.service.FloorPathService;

import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class FloorPathResource {

    private FloorPathService floorPathService = new FloorPathService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<FloorPath> getAllFloorPaths(@PathParam("floorplanid") String floorPlanId) {
        System.out.println(floorPlanId);
        return floorPathService.getAllFloorPaths(floorPlanId);
    }
}
