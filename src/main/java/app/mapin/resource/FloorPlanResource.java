package app.mapin.resource;

import app.mapin.model.FloorPlan;
import app.mapin.service.FloorPlanService;
import de.siegmar.fastcsv.reader.CsvParser;
import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.CsvRow;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;


@Path("floors")
public class FloorPlanResource {


    private static final String UPLOAD_FOLDER = "d:/uploadedFiles/";
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

    @POST
    @Path("{floorplanid}/floordata")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(
            @PathParam("floorplanid") String floorPlanId,
            @FormDataParam("data.csv") InputStream uploadedInputStream,
            @FormDataParam("data.csv") FormDataContentDisposition fileDetail) {

        // check if all form parameters are provided
        if (uploadedInputStream == null || fileDetail == null)
            return Response.status(400).entity("Invalid form data").build();
        // create our destination folder, if it not exists
        try {
            createFolderIfNotExists(UPLOAD_FOLDER);
        } catch (SecurityException se) {
            return Response.status(500)
                    .entity("Can not create destination folder on server")
                    .build();
        }

        String uploadedFileLocation = UPLOAD_FOLDER + fileDetail.getFileName();

        try {
            saveToFile(uploadedInputStream, uploadedFileLocation);
        } catch (IOException e) {
            return Response.status(500).entity("Can not save file").build();
        }
        return Response.status(200)
                .entity("File saved to " + uploadedFileLocation).build();
    }




    /**
     * Utility method to save InputStream data to target location/file
     *
     * @param inStream
     *            - InputStream to be saved
     * @param target
     *            - full path to destination file
     */
    private void saveToFile(InputStream inStream, String target)
            throws IOException {
//        OutputStream out = null;

        CsvReader csvReader = new CsvReader();
        try (CsvParser csvParser = csvReader.parse(new InputStreamReader(inStream))) {
            CsvRow row;
            while ((row = csvParser.nextRow()) != null) {
                System.out.println("First column of line: " + row.getField(0));
            }
        }

//        int read = 0;
//        byte[] bytes = new byte[1024];
//        out = new FileOutputStream(new File(target));
//        while ((read = inStream.read(bytes)) != -1) {
//            System.out.println();
//            out.write(bytes, 0, read);
////        }
//        out.flush();
//        out.close();
    }
    /**
     * Creates a folder to desired location if it not already exists
     *
     * @param dirName
     *            - full path to the folder
     * @throws SecurityException
     *             - in case you don't have permission to create the folder
     */
    private void createFolderIfNotExists(String dirName)
            throws SecurityException {
        File theDir = new File(dirName);
        if (!theDir.exists()) {
            theDir.mkdir();
        }
    }
}
