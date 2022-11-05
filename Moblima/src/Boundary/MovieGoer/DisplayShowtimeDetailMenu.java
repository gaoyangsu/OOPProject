package Boundary.MovieGoer;

import Boundary.Boundary;
import Entity.Seat;
import Entity.ShowSchedule;
import static Controller.MiscMethods.*;

import java.util.ArrayList;
import java.util.Calendar;

public class DisplayShowtimeDetailMenu extends Boundary {
	private ShowSchedule showtime;
	private ArrayList<Seat> chosenSeats;
	private String userId;
	
	public DisplayShowtimeDetailMenu(ShowSchedule showtime,String userId) {
		this.showtime=showtime;
		this.userId=userId;
		this.chosenSeats=new ArrayList<Seat>();
	}
	
	protected void start() {
        display();
    }
	
	private void display() {
		int numStudent=0;
		int numSeniors=0;
		int count=0;
		int coupleSeatYesOrNo=0;
		int numCoupleSeats=0;
		
		printHeader("Pricing Information");
		showPricing();
		printHeader("Booking menu");
		printMenu("What would you like to do?"+
				"\n1. Proceed to booking."+
				"\n2. Go back");
		
		int choice=readChoice(1,2);
		
		if (choice==2) {
			end();
		}
		
		printMenu("\nPlease enter number of seats(Press 0 to go back)");
		int numSeats=readChoice(0,134);
		
		if (numSeats==0) {
			end();
		}
		
		else if (numSeats%2==0) {
			printMenu("Would you like a couple seat?"
					+ "\n1.Yes"
					+ "\n2.No");
			coupleSeatYesOrNo=readChoice(1,2);
			if(coupleSeatYesOrNo==1) {
				printMenu("How many pairs of couple seats would you like to book?");
				numCoupleSeats=readChoice(1,numSeats/2);
			}
		}
		
		Calendar cal=Calendar.getInstance();
    	cal.setTime(showtime.getTime());
    	
    	int dayOfWeek=cal.get(Calendar.DAY_OF_WEEK);
    	int hourOfDay=cal.get(Calendar.HOUR_OF_DAY);
    	
    	if (coupleSeatYesOrNo!=1) {
    		if (dayOfWeek>=2 && dayOfWeek<=6 && hourOfDay<18) {
        		printMenu("Choose number of student tickets");
        		numStudent=readChoice(0,numSeats);
        		
        		printMenu("Choose number of Senior Citizen tickets");
        		numSeniors=readChoice(0,numSeats-numStudent);
        	}
    	}
    	
    	printHeader("Screen");
		showtime.showSeatLayout();
		printHeader("Entrace");
		System.out.println();
    	
		while (count<=numSeats) {
			if(count==numSeats) break;
			
			if (coupleSeatYesOrNo==0|coupleSeatYesOrNo==2) {
				printMenu("Please select your seats: "+"("+(numSeats-count)+" left)");
				printMenu("Select your Row(choose an alphabet from A to G)");
				char result=readCharacter();
				int row=(int)(result-64);
				
				printMenu("Select your Column(choose a number between 1-17, excluding 9)");
				int column=readChoice(1,17);
				
				if (showtime.getSpecificSeat(row, column)==null) {
					printMenu("No such seat exists. Please pick another seat.");
					continue;
				}
				
				if (showtime.getSpecificSeat(row, column).isAssigned()) {
					printMenu("Seat is already occupied. Please pick another seat.");
					continue;
				}
				showtime.getSpecificSeat(row,column).assignSeat();
				chosenSeats.add(showtime.getSpecificSeat(row, column));
				printMenu("Seat succesfully selected.");
				printHeader("Screen");
				showtime.showSeatLayout();
				printHeader("Entrace");
				System.out.println();
				count++;
			}
			else {
				printMenu("Please select your couple seat: "+"("+(numCoupleSeats-count)+" left)");
				printMenu("Select your Row(choose either H or I");
				char result=readCharacter();
				int row=(int)(result-64);
				
				printMenu("Select your Column(choose one of 1,3,5,7,10,12,14,16)");
				int column=readChoice(1,17);
				if (column==2|column==4|column==6|column==8|column==11|column==13|column==15|column==17) {
					printMenu("Please select a valid couple seat.\n\n");
					continue;
				}
				
				if (showtime.getSpecificSeat(row, column)==null) {
					printMenu("No such seat exists. Please pick another seat.");
					continue;
				}
				
				if (showtime.getSpecificSeat(row, column).isAssigned()) {
					printMenu("Seat is already occupied. Please pick another seat.");
					continue;
				}
				
				showtime.getSpecificSeat(row,column).assignSeat();
				showtime.getSpecificSeat(row, column+1).assignSeat();
				chosenSeats.add(showtime.getSpecificSeat(row, column));
				chosenSeats.add(showtime.getSpecificSeat(row, column+1));
				printMenu("Seat succesfully selected.");
				printHeader("Screen");
				showtime.showSeatLayout();
				printHeader("Entrace");
				System.out.println();
				count+=2;
			}
		}


		if (showtime.getMovie().getAgeAdvisory().toString()=="M18" ||showtime.getMovie().getAgeAdvisory().toString()=="R21") {
			printMenu("Please note that this movie is rated "+showtime.getMovie().getAgeAdvisory().toString());
			printMenu("MOBLIMA welcomes all guests aged 18 and above to the Platinum Movie Suites.");
		}
		direct(this, new DisplayBookingConfirmation(showtime,chosenSeats,numStudent,numSeniors,userId));
		


		
	}
	
	public static void showPricing() {
		System.out.println("Ticket Type\t\t Regular&Digital Movies\t 3D Movies");
		System.out.println("\nSenior Citizens*\t $4.00\t\t\t N.A");
		System.out.println("Mon-Fri, before 6pm");
		System.out.println();
		System.out.println("Students**\t\t $7.00\t\t\t $10.00");
		System.out.println("Mon-Fri, before 6pm");
		System.out.println();
		System.out.println("Mon-Wed#\t\t $8.50\t\t\t $11.50");
		System.out.println("All Sessions");
		System.out.println();
		System.out.println("Thu\t\t\t $9.50\t\t\t $12.50");
		System.out.println("All Sessions");
		System.out.println();
		System.out.println("Fri\t\t\t $9.50\t\t\t $12.50");
		System.out.println("Sessions before 6pm");
		System.out.println();
		System.out.println("Fri\t\t\t $12.00\t\t\t $15.00");
		System.out.println("Sessions after 6pm");
		System.out.println();
		System.out.println("Sat & Sun^\t\t $12.00\t\t\t $15.00");
		System.out.println("All Sessions");
		System.out.println();
		System.out.println("Gold Class^^\t\t $19.00\t\t\t $22.00");
		System.out.println("All Sessions,Daily");
		System.out.println();
		System.out.println("Platinum Suites^^\t $28.50\t\t\t $31.50");
		System.out.println("All Sessions,Daily");
		System.out.println();
		System.out.println("\n**Not valid on PH/eve of PH | ^Include PH?eve of PH and weekend sneaks | *For patrons 55 years & older. Not valid on PH/eve of PH | ^^Applies for Monday-Sunday,including PH/eve of PH");
	}
}
