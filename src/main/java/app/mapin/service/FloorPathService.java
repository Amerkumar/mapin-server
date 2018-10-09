package app.mapin.service;


import app.mapin.model.FloorPath;
import app.mapin.model.LatLng;
import app.mapin.model.MagData;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FloorPathService {

    public List<FloorPath> floorPaths = new ArrayList<>();

    public FloorPathService() {
        List<LatLng> latLngs = new ArrayList<>();
        latLngs.add(new LatLng(63.3434,3434.3434));
        latLngs.add(new LatLng(34.4343,43412.23));
        List<MagData> magDataList = new ArrayList<>();
        magDataList.add(new MagData(23.323,323.232,32312.343));
        magDataList.add(new MagData(213.232,3232.323,323.232545));
        magDataList.add(new MagData(6.56,545.545,67.675));
        floorPaths.add(new FloorPath(UUID.randomUUID().toString(), latLngs, magDataList));
        floorPaths.add(new FloorPath(UUID.randomUUID().toString(), latLngs, magDataList));
    }


    public List<FloorPath> getAllFloorPaths(String floorPlanId) {
        return floorPaths;
    }



}
