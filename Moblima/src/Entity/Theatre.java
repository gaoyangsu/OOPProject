package Entity;
import Entity.TheatreEnums.*;

import java.io.Serializable;
import java.util.Objects;

public class Theatre implements Serializable {

    private boolean is3D;
    private TheatreClass theatreClass;
    private Cineplex cineplex;
    private CinemaLocation cinemaLocation;
    private String code;
    private double basePrice;

    public Theatre(boolean is3D, TheatreClass theatreClass, Cineplex cineplex, CinemaLocation cinemaLocation, String code) {
        this.is3D = is3D;
        this.theatreClass = theatreClass;
        this.cineplex = cineplex;
        this.cinemaLocation = cinemaLocation;
        this.code = code;
        this.basePrice = 0.0;
    }

    public boolean isIs3D() {
        return is3D;
    }

    public void setIs3D(boolean is3D) {
        this.is3D = is3D;
    }

    public TheatreClass getTheatreClass() {
        return theatreClass;
    }

    public void setTheatreClass(TheatreClass theatreClass) {
        this.theatreClass = theatreClass;
    }

    public Cineplex getCineplex() {
        return cineplex;
    }

    public void setCineplex(Cineplex cineplex) {
        this.cineplex = cineplex;
    }

    public CinemaLocation getCinemaLocation() {
        return cinemaLocation;
    }

    public void setCinemaLocation(CinemaLocation cinemaLocation) {
        this.cinemaLocation = cinemaLocation;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Theatre theatre)) return false;
        return getCineplex() == theatre.getCineplex() && Objects.equals(getCode(), theatre.getCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCineplex(), getCode());
    }

    public String toString() {
        return this.code;
    }

}
