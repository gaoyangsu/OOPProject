package Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class ShowSchedule implements Serializable {
	
	private static final long serialVersionUID=-2166830098834089557L;

    private Movie movie;
    private Theatre theatre;
    private Date time;
    private Seat[][] seats;
    private static final int numRows= 9;
    private static final int numCols=17;

    public ShowSchedule(){
        seats= new Seat[numRows][numCols];
        createSeats();
    }
    
    public void showSeatLayout() {
    	for (int row=0;row<numRows;row++) {
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
    }

    public Seat getSpecificSeat(int row, int col){
        if (row<1||row > 9 || col < 1 || col > 17)  return null;
        return seats[row - 1][col - 1];
    }

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
    
    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShowSchedule that)) return false;
        return Objects.equals(getMovie(), that.getMovie()) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMovie());
    }
}
