package Entity;

import java.io.Serializable;

public class Seat implements Serializable {

    private int row;
    private int col;
    private boolean isAssigned;
    private ShowSchedule showSchedule;

    public Seat(int row, int col, ShowSchedule showSchedule) {
        this.row = row;
        this.col = col;
        this.isAssigned = false;
        this.showSchedule = showSchedule;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isAssigned() {
        return isAssigned;
    }

    public ShowSchedule getShowSchedule() {
        return showSchedule;
    }

    public void assignSeat(){
        isAssigned=true;
    }

    public String displayOccupancy(){
        if (isAssigned==true) return "{X}";
        else return "{ }";


    }

}
