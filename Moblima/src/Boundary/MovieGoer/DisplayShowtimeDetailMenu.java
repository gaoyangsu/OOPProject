package Boundary.MovieGoer;

import Boundary.Boundary;
import Entity.Seat;
import Entity.ShowSchedule;
import static Controller.MiscMethods.*;

import java.util.ArrayList;

public class DisplayShowtimeDetailMenu extends Boundary {
	private ShowSchedule showtime;
	private ArrayList<Seat> chosenSeats;
	
	public DisplayShowtimeDetailMenu(ShowSchedule showtime) {
		this.showtime=showtime;
		this.chosenSeats=new ArrayList<Seat>();
	}
	
	protected void start() {
        display();
    }
	
	private void display() {
		int loop=1;
		
		showPricing();
		
		while (loop==1) {
			printHeader("Screen");
			showtime.showSeatLayout();
			printHeader("Entrace");
			printMenu("\nHow would you like to proceed?",
					"\n1.Choose a seat",
					"2.Carry on to payment");
			
			int choice=readChoice(1,2);
			if (choice==2) {
				break;
			}
			
			printMenu("\nSelect your row(choose a number between 1-9)");
			int row=readChoice(1,9);
			
			printMenu("\nSelect your Column(choose a number between 1-17, excluding 8)");
			int column=readChoice(1,17);
			
			if (showtime.getSpecificSeat(row, column).isAssigned() || showtime.getSpecificSeat(row, column)==null) {
				printMenu("Seat is already occupied. Please pick another seat");
				continue;
			}
			else {
				showtime.getSpecificSeat(row,column).assignSeat();
				chosenSeats.add(showtime.getSpecificSeat(row, column));
				printMenu("Seat succesfully selected.");
			}
		}
		if (showtime.getMovie().getAgeAdvisory().toString()=="M18" ||showtime.getMovie().getAgeAdvisory().toString()=="R21") {
			printMenu("Please note that this movie is rated "+showtime.getMovie().getAgeAdvisory().toString());
			printMenu("MOBLIMA welcomes all guests aged 18 and above to the Platinum Movie Suites.");
		}
		
	}
	
	public static void showPricing() {
		System.out.println("Ticket Type\t\t Regular&Digital Movies\t 3D Movies");
		System.out.println("\nSenior Citizens\t\t $4.00\t\t\t N.A");
		System.out.println("Mon-Fri, before 6pm");
		System.out.println();
		System.out.println("Students\t\t $7.00\t\t\t $9.00");
		System.out.println("Mon-Fri, before 6pm");
		System.out.println();
		System.out.println("Mon-Wed\t\t\t $8.50\t\t\t $11.00");
		System.out.println("All Sessions");
		System.out.println();
		System.out.println("Thu\t\t\t $9.50\t\t\t $11.00");
		System.out.println("All Sessions");
		System.out.println();
		System.out.println("Fri\t\t\t $9.50\t\t\t $15.00");
		System.out.println("Sessions before 6pm");
		System.out.println();
		System.out.println("Fri\t\t\t $11.00\t\t\t $15.00");
		System.out.println("Sessions after 6pm");
		System.out.println();
		System.out.println("Sat & Sun\t\t $11.00\t\t\t $15.00");
		System.out.println("All Sessions");
		System.out.println();
		System.out.println("PH & PH Eve\t\t $11.00\t\t\t $15.00");
		System.out.println("All Sessions");
		System.out.println();
	}
}
