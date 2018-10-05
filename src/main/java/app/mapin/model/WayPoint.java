package app.mapin.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class WayPoint {

    private String wayPointId;
    private Double wayPointLat;
    private Double wayPointLng;


    public WayPoint(String wayPointId, Double wayPointLat, Double wayPointLng) {
        this.wayPointId = wayPointId;
        this.wayPointLat = wayPointLat;
        this.wayPointLng = wayPointLng;
    }

    public WayPoint() {
    }

    public String getWayPointId() {
        return wayPointId;
    }

    public void setWayPointId(String wayPointId) {
        this.wayPointId = wayPointId;
    }

    public Double getWayPointLat() {
        return wayPointLat;
    }

    public void setWayPointLat(Double wayPointLat) {
        this.wayPointLat = wayPointLat;
    }

    public Double getWayPointLng() {
        return wayPointLng;
    }

    public void setWayPointLng(Double wayPointLng) {
        this.wayPointLng = wayPointLng;
    }
}
