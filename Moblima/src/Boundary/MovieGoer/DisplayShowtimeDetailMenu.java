package Boundary.MovieGoer;

import Boundary.Boundary;
import Entity.ShowSchedule;

public class DisplayShowtimeDetailMenu extends Boundary {
	private ShowSchedule showtime;
	
	public DisplayShowtimeDetailMenu(ShowSchedule showtime) {
		this.showtime=showtime;
	}
	
	protected void start() {
        display();
    }
	
	private void display() {
		
	}
}
