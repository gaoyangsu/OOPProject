package Entity;

import Entity.Movie;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
    Entity Class representing a SystemSetting by the Admin user
    @version 1.0
    @since 2022-10-30
 */
public class SystemSettings implements Serializable {
    
    /**
     * @param holidayIncrement the increment if ShowSchedule falls on the holiday
     * @param  childDiscount the decrement for children
     * @param seniorCitizenDiscount the decrement for senior citizen
     * @param premiumPrice the price for premium theatres
     * @param threeDIncrement the increment for 3D pricing
     * @param blockBusterIncrement the increment for blockBuster
     * @param weekendIncrement the increment for weekend
     */
    private double holidaysIncrement,
    childDiscount,
    seniorCitizenDiscount,
    premiumPrice,
    standardPrice,
    threeDIncrement,
    blockBusterIncrement,
    weekendIncrement;

    /**
     * attribute to toggle between 0,1,2 to choose the way of displaying the top five
     */
    private int topFiveChoice;

    // private ArrayList<Movie> top5;
    // private ArrayList<Movie> top5rating;
    /**
     * Constructor of a new SsytemSetting with the given input conditions from the user.
     * @param holidayIncrement the increment if ShowSchedule falls on the holiday
     * @param  childDiscount the decrement for children
     * @param seniorCitizenDiscount the decrement for senior citizen
     * @param premiumPrice the price for premium theatres
     * @param threeDIncrement the increment for 3D pricing
     * @param blockBusterIncrement the increment for blockBuster
     * @param weekendIncrement the increment for weekend
     */
    public SystemSettings(double holidaysIncrement, double childDiscount, double seniorCitizenDiscount, double premiumPrice, double standardPrice, double threeDIncrement, double blockBusterIncrement, double weekendIncrement) {
        this.holidaysIncrement = holidaysIncrement;
        this.childDiscount = childDiscount;
        this.seniorCitizenDiscount = seniorCitizenDiscount;
        this.premiumPrice = premiumPrice;
        this.standardPrice = standardPrice;
        this.threeDIncrement = threeDIncrement;
        this.blockBusterIncrement = blockBusterIncrement;
        this.weekendIncrement = weekendIncrement;
        this.topFiveChoice=0;
    }

    
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SystemSettings)) {
            return false;
        }
        SystemSettings systemSettings = (SystemSettings) o;
        return holidaysIncrement == systemSettings.holidaysIncrement && childDiscount == systemSettings.childDiscount && seniorCitizenDiscount == systemSettings.seniorCitizenDiscount && premiumPrice == systemSettings.premiumPrice && standardPrice == systemSettings.standardPrice && threeDIncrement == systemSettings.threeDIncrement && blockBusterIncrement == systemSettings.blockBusterIncrement && weekendIncrement == systemSettings.weekendIncrement;
    }

    @Override
    public int hashCode() {
        return Objects.hash(holidaysIncrement, childDiscount, seniorCitizenDiscount, premiumPrice, standardPrice, threeDIncrement, blockBusterIncrement, weekendIncrement);
    }


    /**
     * this method gets the price increment of holidays
     * @return double value of price increment
     */
    public double getHolidaysIncrement() {
        return this.holidaysIncrement;
    }

     /**
     * this method sets the price increment of holidays
     * @param holidaysIncrement
     */
    public void setHolidaysIncrement(double holidaysIncrement) {
        this.holidaysIncrement = holidaysIncrement;
    }

     /**
     * this method gets the price decrement for children
     * @return double value of price decrement
     */
    public double getChildDiscount() {
        return this.childDiscount;
    }

    /**
     * this method sets the price decrement of children
     * @param childDiscount
     */
    public void setChildDiscount(double childDiscount) {
        this.childDiscount = childDiscount;
    }

     /**
     * this method gets the price decrement for senior citizens
     * @return double value of price decrement
     */
    public double getSeniorCitizenDiscount() {
        return this.seniorCitizenDiscount;
    }

      /**
     * this method sets the price decrement of children
     * @param seniorCitizenDiscount
     */
    public void setSeniorCitizenDiscount(double seniorCitizenDiscount) {
        this.seniorCitizenDiscount = seniorCitizenDiscount;
    }

     /**
     * this method gets the price increment for premium price
     * @return double value of price increment
     */
    public double getPremiumPrice() {
        return this.premiumPrice;
    }

    /**
     * this method sets the price increment for premium price
     * @param premiumPrice
     */
    public void setPremiumPrice(double premiumPrice) {
        this.premiumPrice = premiumPrice;
    }

     /**
     * this method gets the standard price of a movie ticket
     * @return double value of standard price
     */
    public double getStandardPrice() {
        return this.standardPrice;
    }

    /**
     * this method sets the standard price of a movie ticket
     * @param standardPrice
     */
    public void setStandardPrice(double standardPrice) {
        this.standardPrice = standardPrice;
    }

     /**
     * this method gets the price increment for 3D
     * @return double value of price increment
     */
    public double getThreeDIncrement() {
        return this.threeDIncrement;
    }

    /**
     * this method sets the increment for 3D
     * @param threeDIncrement
     */
    public void setThreeDIncrement(double threeDIncrement) {
        this.threeDIncrement = threeDIncrement;
    }

     /**
     * this method gets the price increment for blockbuster
     * @return double value of price increment
     */
    public double getBlockBusterIncrement() {
        return this.blockBusterIncrement;
    }

    /**
     * this method sets the increment for blockbuster
     * @param blockBusterIncrement
     */
    public void setBlockBusterIncrement(double blockBusterIncrement) {
        this.blockBusterIncrement = blockBusterIncrement;
    }

    /**
     * this method gets the price increment for weekend showtimes
     * @return double value of price increment
     */
    public double getWeekendIncrement() {
        return this.weekendIncrement;
    }

    /**
     * this method sets the increment for weekendPricing
     * @param weekendIncrement
     */
    public void setWeekendIncrement(double weekendIncrement) {
        this.weekendIncrement = weekendIncrement;
    }

    // public ArrayList<Movie> getTop5() {
    //     return this.top5;
    // }

    // public void setTop5(ArrayList<Movie> top5) {
    //     this.top5 = top5;
    // }

    // public ArrayList<Movie> getTop5rating() {
    //     return this.top5rating;
    // }

    // public void setTop5rating(ArrayList<Movie> top5rating) {
    //     this.top5rating = top5rating;
    // }
        
     /**
     * this method gets the topfive choice selection
     * @return selection
     */
    public int getTopFivechoice() {
        return this.topFiveChoice;
    }

    /**
     * this method sets the topfive choice selection 0/1/2
     * @param topFivechoice
     */
    public void setTopFivechoice(int topFivechoice){
        this.topFiveChoice = topFivechoice;
    }
    


}