package app.mapin.service;

import app.mapin.model.Floor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FloorService {

    private Map<Long, Floor> floors = new HashMap<>();

    public FloorService() {
        floors.put(1L, new Floor(1, "Incubator GIK, Ground Floor", 1));
        floors.put(2L, new Floor(2, "Incubator GIK, First Floor", 2));
    }

    public List<Floor> getAllFloors() {
        return new ArrayList<Floor>(floors.values());
    }

}
