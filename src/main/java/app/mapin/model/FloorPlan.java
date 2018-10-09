package app.mapin.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FloorPlan {

    private String id;
    private String location;
    private long floorNumber;

    public FloorPlan(String id, String location, long floorNumber) {
        this.id = id;
        this.location = location;
        this.floorNumber = floorNumber;
    }

    public FloorPlan() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(long floorNumber) {
        this.floorNumber = floorNumber;
    }
}
