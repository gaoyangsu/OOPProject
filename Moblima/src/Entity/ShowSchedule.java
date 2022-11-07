package Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
/**
    Entity Class representing a ShowSchedule
    A particular ShowSchedule contains all of the Seat Classes, and associates with Movie and Theatre Class to 
    extract the movie and theatre details
    @version 1.0
    @since 2022-10-25
 */
public class ShowSchedule implements Serializable {
	
    /**
     * the {@code long} UID for ShowSchedule Serialiser
     */
	private static final long serialVersionUID=-2166830098834089557L;

    /**
     * the {@code Movie} for this showSchedule
     */
    private Movie movie;

    /**
     * the {@code Theatre} chosen for this showSchedule
     */
    private Theatre theatre;

    /**
     * the {@code Date} chosen for this showSchedule
     */
    private Date time;

    /**
     * 2D array of {@code Seat} classes initialised with this Class
     */
    private Seat[][] seats;

    /**
     * denoting the number of Rows
     */
    private static final int numRows= 9;

    /**
     * denoting the number of Cols
     */
    private static final int numCols=17;

    /**
     * Empty Constructor, only initialsing the Seat classes
     */
    public ShowSchedule(){
        seats= new Seat[numRows][numCols];
        createSeats();
    }
    
    /**
     * method to display the overall SeatLayout,
     * will further call displayOccupancy() and displayCoupleSeatOccupancy(int)
     */
    public void showSeatLayout() {
    	for (int row=0;row<=6;row++) {
    		System.out.print((char)(row+65));
    		System.out.print("  ");
    		for (int column=0;column<numCols;column++) {
    			if (seats[row][column]!=null) {
    				System.out.print(seats[row][column].displayOccupancy());
    			}
    			else {
    				System.out.print("   ");
    			}
    		}
    		System.out.print("  ");
    		System.out.print((char)(row+65));
    		System.out.print("\n");
    	}
    	
    	System.out.print("   ");
    	for (int i=1;i<=9;i++) {
    		System.out.print(" "+i+" ");
    	}
    	for (int i=10;i<=17;i++) {
    		System.out.print(i+" ");
    	}
    	System.out.println();
    	
    	System.out.println("------------------COUPLE SEATS BELOW---------------------");
    	
    	for (int row=7;row<numRows;row++) {
    		System.out.print((char)(row+65));
    		System.out.print("  ");
    		for (int column=0;column<=7;column++) {
    			if (seats[row][column]!=null) {
    				System.out.print(seats[row][column].displayCoupleSeatOccupancy(column%2));
    			}
    			else {
    				System.out.print("   ");
    			}
    		}
    		for (int column=8;column<numCols;column++) {
    			if (seats[row][column]!=null) {
    				System.out.print(seats[row][column].displayCoupleSeatOccupancy((column-1)%2));
    			}
    			else {
    				System.out.print("   ");
    			}
    		}
    		System.out.print("  ");
    		System.out.print((char)(row+65));
    		System.out.print("\n");
    	}
    	System.out.print("     ");
    	for (int i=1;i<=7;i+=2) {
    		if (i==7) {
    			System.out.print(i+""+(i+1));
        		System.out.print("   ");
    		}
    		else {
    			System.out.print(i+""+(i+1));
        		System.out.print("    ");
    		}
    	}
    	System.out.print("9  ");
    	for (int i=10;i<=17;i+=2) {
    		System.out.print(i+""+(i+1));
    		System.out.print("  ");
    	}
    	System.out.println();
    	
    }

    
    /** 
     * method to getSpecific Seat to occupy
     * @param row
     * @param col
     * @return Seat
     */
    public Seat getSpecificSeat(int row, int col){
        if (row<1||row > 9 || col < 1 || col > 17)  return null;
        return seats[row - 1][col - 1];
    }

    /** 
     * method to create the seats within the 2D array of seats
     * @param row
     * @param col
     * @return Seat
     */
    public void createSeats(){
        //some seats are invalid, so we dont create them as Seat classes
        for(int i=0;i<4;i++){
            for(int j=2;j<numCols;j++){
                if(j!=8) seats[i][j]=new Seat(i,j,this);
            }
        }

        for(int i=4;i<8;i++){
            for(int j=0;j<numCols;j++){
                if(j!=8) seats[i][j]=new Seat(i, j, this);
            }
        }

        for(int j=0;j<numCols;j++){
            if((j!=8)&&(j!=9)&&(j!=10)) seats[numRows-1][j]= new Seat(numRows-1,j,this);
        }
    }

    
    /** 
     * A method to check if all seats are empty, 
     * If all empty, then the showSchdule and change time,
     * if not, means someone booked a ticket already, 
     * cannot change time
     * @return int 0 means not empty, 1 means empty
     */
    public int checkAllSeatsEmpty(){
        for(int i=0;i<4;i++){
            for(int j=2;j<numCols;j++){
                if(j!=8) {
                    if(seats[i][j].isAssigned()) return 0;
                }
            }
        }

        for(int i=4;i<8;i++){
            for(int j=0;j<numCols;j++){
                if(j!=8) {
                    if(seats[i][j].isAssigned()) return 0;
                }
            }
        }

        for(int j=0;j<numCols;j++){
            if((j!=8)&&(j!=9)&&(j!=10)){
                if(seats[numRows-1][j].isAssigned()) return 0;
            } 
        }

        return 1;
    }
    
    
    /** 
     * gets the Movie class of this particular showSchedule
     * @return Movie
     */
    public Movie getMovie() {
        return movie;
    }

    
    /** 
     * sets the Movie class of this particular showschedule
     * @param movie
     */
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    
    /** 
     * gets the Theatre class of this particular showSchedule
     * @return Theatre
     */
    public Theatre getTheatre() {
        return theatre;
    }

    
    /** 
     * sets the Theatre class of this particular showschedule
     * @param theatre
     */
    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }

    
    /** 
     * gets the Date and Time of showing
     * @return Date
     */
    public Date getTime() {
        return time;
    }

    
    /** 
     * sets the Date and Time of showing
     * @param time
     */
    public void setTime(Date time) {
        this.time = time;
    }

    
    /** 
     * gets the corresponding ShowSchedule via the equals method
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShowSchedule that)) return false;
        return Objects.equals(getMovie(), that.getMovie()) ;
    }

    
    /** 
     * hashes this ShowSchedule object using the Movie
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(getMovie());
    }
}
