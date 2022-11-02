package Entity;

import Entity.Movie;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class SystemSettings implements Serializable {

    private double holidaysIncrement,
    childDiscount,
    seniorCitizenDiscount,
    premiumPrice,
    standardPrice,
    threeDIncrement,
    blockBusterIncrement,
    weekendIncrement;

    private ArrayList<Movie> top5;
    private ArrayList<Movie> top5rating;
    public SystemSettings(double holidaysIncrement, double childDiscount, double seniorCitizenDiscount, double premiumPrice, double standardPrice, double threeDIncrement, double blockBusterIncrement, double weekendIncrement) {
        this.holidaysIncrement = holidaysIncrement;
        this.childDiscount = childDiscount;
        this.seniorCitizenDiscount = seniorCitizenDiscount;
        this.premiumPrice = premiumPrice;
        this.standardPrice = standardPrice;
        this.threeDIncrement = threeDIncrement;
        this.blockBusterIncrement = blockBusterIncrement;
        this.weekendIncrement = weekendIncrement;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SystemSettings)) {
            return false;
        }
        SystemSettings systemSettings = (SystemSettings) o;
        return holidaysIncrement == systemSettings.holidaysIncrement && childDiscount == systemSettings.childDiscount && seniorCitizenDiscount == systemSettings.seniorCitizenDiscount && premiumPrice == systemSettings.premiumPrice && standardPrice == systemSettings.standardPrice && threeDIncrement == systemSettings.threeDIncrement && blockBusterIncrement == systemSettings.blockBusterIncrement && weekendIncrement == systemSettings.weekendIncrement && Objects.equals(top5, systemSettings.top5) && Objects.equals(top5rating, systemSettings.top5rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(holidaysIncrement, childDiscount, seniorCitizenDiscount, premiumPrice, standardPrice, threeDIncrement, blockBusterIncrement, weekendIncrement, top5, top5rating);
    }



    public double getHolidaysIncrement() {
        return this.holidaysIncrement;
    }

    public void setHolidaysIncrement(double holidaysIncrement) {
        this.holidaysIncrement = holidaysIncrement;
    }

    public double getChildDiscount() {
        return this.childDiscount;
    }

    public void setChildDiscount(double childDiscount) {
        this.childDiscount = childDiscount;
    }

    public double getSeniorCitizenDiscount() {
        return this.seniorCitizenDiscount;
    }

    public void setSeniorCitizenDiscount(double seniorCitizenDiscount) {
        this.seniorCitizenDiscount = seniorCitizenDiscount;
    }

    public double getPremiumPrice() {
        return this.premiumPrice;
    }

    public void setPremiumPrice(double premiumPrice) {
        this.premiumPrice = premiumPrice;
    }

    public double getStandardPrice() {
        return this.standardPrice;
    }

    public void setStandardPrice(double standardPrice) {
        this.standardPrice = standardPrice;
    }

    public double getThreeDIncrement() {
        return this.threeDIncrement;
    }

    public void setThreeDIncrement(double threeDIncrement) {
        this.threeDIncrement = threeDIncrement;
    }

    public double getBlockBusterIncrement() {
        return this.blockBusterIncrement;
    }

    public void setBlockBusterIncrement(double blockBusterIncrement) {
        this.blockBusterIncrement = blockBusterIncrement;
    }

    public double getWeekendIncrement() {
        return this.weekendIncrement;
    }

    public void setWeekendIncrement(double weekendIncrement) {
        this.weekendIncrement = weekendIncrement;
    }

    public ArrayList<Movie> getTop5() {
        return this.top5;
    }

    public void setTop5(ArrayList<Movie> top5) {
        this.top5 = top5;
    }

    public ArrayList<Movie> getTop5rating() {
        return this.top5rating;
    }

    public void setTop5rating(ArrayList<Movie> top5rating) {
        this.top5rating = top5rating;
    }


}