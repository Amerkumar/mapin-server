package app.mapin.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@XmlRootElement
public class FloorPath {

    private String id;
    private List<LatLng> latLngList;
    private List<MagData> magDataList;


    public FloorPath(String id, List<LatLng> latLngList, List<MagData> magDataList) {
        this.id = id;
        this.latLngList = latLngList;
        this.magDataList = magDataList;
    }

    public FloorPath() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<LatLng> getLatLngList() {
        return latLngList;
    }

    public void setLatLngList(List<LatLng> latLngList) {
        this.latLngList = latLngList;
    }

    public List<MagData> getMagDataList() {
        return magDataList;
    }

    public void setMagDataList(List<MagData> magDataList) {
        this.magDataList = magDataList;
    }
}
