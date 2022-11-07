package Entity;

import java.io.Serializable;

/**
    Entity Class representing a Particular Seat in a ShowSchedule
    includes methods to print the layout of EACH seat, be it normal seats or couple seats
    @version 1.0
    @since 2022-10-25
 */
public class Seat implements Serializable {

    
    /**
     * row number of seat
     */
    private int row;
    /**
     * col number of seat
     */
    private int col;
    /**
     * {@code boolean} denoting if the seat is assigned
     */
    private boolean isAssigned;
    /**
     * {@code ShowSchedule} class that this seat belongs to
     */
    private ShowSchedule showSchedule;


    /**
     * Constructor of Seat Classes
     * Creates a new Seat with the given input conditions from the user.
     * @param row row number of seat
     * @param col col number of seat
     * @param showSchdule {@code ShowSchedule} class that this seat belongs to
     */
    public Seat(int row, int col, ShowSchedule showSchedule) {
        this.row = row;
        this.col = col;
        this.isAssigned = false;
        this.showSchedule = showSchedule;
    }

    
    /** 
     * getter of row of the particular Seat
     * @return int
     */
    public int getRow() {
        return row;
    }

    
    /** 
     * getter of Col of the particular Seat
     * @return int
     */
    public int getCol() {
        return col;
    }

    
    /** 
     * getter of whether the Seat is assigned
     * @return boolean true if assigned, false if not
     */
    public boolean isAssigned() {
        return isAssigned;
    }

    
    /** 
     * getter of showSchedule belonging to the Seat
     * @return ShowSchedule
     */
    public ShowSchedule getShowSchedule() {
        return showSchedule;
    }

    /** 
     * method to assign a Seat by changing isAssigned variable
     */
    public void assignSeat(){
        isAssigned=true;
    }

     /** 
     * method to unassign a Seat by changing isAssigned variable
     */
    public void unassignSeat(){
        isAssigned=false;
    }

    
    /** 
     * display occupancy of a single Seat (Normal)
     * @return String
     */
    public String displayOccupancy(){
        if (isAssigned==true) return "{X}";
        else return "{ }";
    }

    
    /** 
     * display occupancy of a couple Seat (2 people)
     * @param parity
     * @return String
     */
    public String displayCoupleSeatOccupancy(int parity) {
    	if (parity==0) {
    		if (isAssigned==true) return "{{X";
        	else return "{{ ";
    	}
    	else if(parity==1) {
    		if (isAssigned==true) return "X}}";
        	else return " }}";
    	}
    	return "";
    }
}
