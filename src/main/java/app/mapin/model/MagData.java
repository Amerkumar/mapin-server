package app.mapin.model;

public class MagData {

    private Double magX;
    private Double magY;
    private Double magZ;


    public MagData(Double magX, Double magY, Double magZ) {
        this.magX = magX;
        this.magY = magY;
        this.magZ = magZ;
    }

    public MagData() {
    }

    public Double getMagX() {
        return magX;
    }

    public void setMagX(Double magX) {
        this.magX = magX;
    }

    public Double getMagY() {
        return magY;
    }

    public void setMagY(Double magY) {
        this.magY = magY;
    }

    public Double getMagZ() {
        return magZ;
    }

    public void setMagZ(Double magZ) {
        this.magZ = magZ;
    }
}
