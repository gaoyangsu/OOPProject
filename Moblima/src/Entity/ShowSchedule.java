package Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class ShowSchedule implements Serializable {

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
        return Objects.equals(getMovie(), that.getMovie()) && getTheatre().equals(that.getTheatre()) && getTime().equals(that.getTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMovie(), getTheatre(), getTime());
    }
}
