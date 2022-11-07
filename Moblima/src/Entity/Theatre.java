package Entity;
import Entity.TheatreEnums.*;

import java.io.Serializable;
import java.util.Objects;

/**
    Entity Class representing a Theatre
    A Theatre can be configured according to 3D, theatreclass, cineplex, location and code.
    @version 1.0
    @since 2022-10-25
 */
public class Theatre implements Serializable {
    /**
     * whether the movie is 3D ornot
     */
    private boolean is3D;
    /**
     * the enum Theatreclass of the theatre
     */
    private TheatreClass theatreClass;
    /**
     * the enum Cineplex of the theatre
     */
    private Cineplex cineplex;
     /**
     * the enum Location of the theatre
     */
    private CinemaLocation cinemaLocation;
    /**
     * the {@code String} code of the theatre
     */
    private String code;
    /**
     * the {@code double} baseprice  of the theatre
     */
    private double basePrice;


    /**
     * Creates a new theatre with the given input conditions from the user.
     * @param is3D if the theatre is 3D
     * @param theatreClass the enum Theatreclass of the theatre
     * @param cineplex the enum Cineplex of the theatre
     * @param cineaLocation the enum Location of the theatre
     * @param code the String code of the theatre
     */
    public Theatre(boolean is3D, TheatreClass theatreClass, Cineplex cineplex, CinemaLocation cinemaLocation, String code) {
        this.is3D = is3D;
        this.theatreClass = theatreClass;
        this.cineplex = cineplex;
        this.cinemaLocation = cinemaLocation;
        this.code = code;
        this.basePrice = 0.0;
    }

    
    /** 
     * getter to check if the {@code theatre} is 3D
     * @return boolean
     */
    public boolean isIs3D() {
        return is3D;
    }

    
    /** 
     * setter to set if the {@code theatre} is 3D or not
     * @param is3D
     */
    public void setIs3D(boolean is3D) {
        this.is3D = is3D;
    }

    
    /** 
     * getter to get the Class of the theatre
     * @return TheatreClass
     */
    public TheatreClass getTheatreClass() {
        return theatreClass;
    }

    
    /** 
     * setter of the enum TheatreClass
     * @param theatreClass
     */
    public void setTheatreClass(TheatreClass theatreClass) {
        this.theatreClass = theatreClass;
    }

    
    /** 
     * getter of enum of Cineplex
     * @return Cineplex
     */
    public Cineplex getCineplex() {
        return cineplex;
    }

    
    /** 
     * setter of the enum of Cineplex
     * @param cineplex
     */
    public void setCineplex(Cineplex cineplex) {
        this.cineplex = cineplex;
    }

    
    /** 
     * getter of enum of CinemaLocation
     * @return CinemaLocation
     */
    public CinemaLocation getCinemaLocation() {
        return cinemaLocation;
    }

    
    /** 
     * setter of enum of CinemaLocation
     * @param cinemaLocation
     */
    public void setCinemaLocation(CinemaLocation cinemaLocation) {
        this.cinemaLocation = cinemaLocation;
    }

    
    /** 
     * getter of Cinema code in String, for generating TID purposes 
     * and checking of the theatre for a specified ShowSchedule
     * @return String
     */
    public String getCode() {
        return code;
    }

    
    /** 
     * setter of the Cinema code in String
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    
    /** 
     * getter of BasePrice
     * @return double
     */
    public double getBasePrice() {
        return basePrice;
    }

    
    /** 
     * sets the basePrice of the particular theatre
     * @param basePrice
     */
    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    
    /** 
     * gets the theatre object based on equals to
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Theatre theatre)) return false;
        return getCineplex() == theatre.getCineplex() && Objects.equals(getCode(), theatre.getCode());
    }

    
    /** 
     * hashes the theatre objected based on Cineplex enum and code of the theatre
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(getCineplex(), getCode());
    }

    
    /** 
     * returns the theatre code toString
     * @return String
     */
    public String toString() {
        return this.code;
    }

}
