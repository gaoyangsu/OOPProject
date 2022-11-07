package Boundary.MovieGoer;

import Boundary.Boundary;
import Entity.Holiday;
import Entity.Seat;
import Entity.ShowSchedule;
import Entity.SystemSettings;
import Entity.TheatreEnums.TheatreClass;

import static Controller.MiscMethods.*;
import static Controller.AdminController.*;

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
		int coupleSeatCount=0;
		int regularSeat=104;
		int coupleSeat=15;
		chosenSeats=new ArrayList<Seat>();
		
		for (int i=1;i<=9;i++) {
			for (int j=1;j<=17;j++) {
				if (showtime.getSpecificSeat(i, j)!=null) {
					if (showtime.getSpecificSeat(i, j).isAssigned() && i<=7) {
						regularSeat--;
					}
					else if (showtime.getSpecificSeat(i, j).isAssigned() && i>=8) {
						coupleSeat--;
						j+=1;
					}
				}
			}
		}
		
		printHeader("Pricing Information");
		showPricing();
		printHeader("Booking Menu");
		printMenu("What would you like to do?"+
				"\n1. Proceed to booking."+
				"\n2. Go back");
		
		int choice=readChoice(1,2);
		
		if (choice==2) {
			end();
		}
		
		printMenu("\nPlease enter number of seats (Press 0 to go back)");
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
				if (numCoupleSeats>=coupleSeat) {
					printMenu("Insufficient couple seats remaining in this theatre. Please select another showtime.");
					end();
				}
			}
		}
		
		Calendar cal=Calendar.getInstance();
    	cal.setTime(showtime.getTime());
    	
    	int dayOfWeek=cal.get(Calendar.DAY_OF_WEEK);
    	int hourOfDay=cal.get(Calendar.HOUR_OF_DAY);
    	
    	if (coupleSeatYesOrNo==2 | coupleSeatYesOrNo==0) {
    		if (numSeats>=regularSeat) {
    			printMenu("Insufficient seats remaining in this theatre. Please select another showtime.");
    			end();
    		}
    		if ((dayOfWeek>=2 & dayOfWeek<=6) & hourOfDay<18) {
        		printMenu("Choose number of student tickets");
        		numStudent=readChoice(0,numSeats);
        		
        		printMenu("Choose number of senior citizen tickets");
        		numSeniors=readChoice(0,numSeats-numStudent);
        	}
    	}
    	
    	printHeader("Screen");
		showtime.showSeatLayout();
		printHeader("Entrance");
		System.out.println();

		int prevRow=0;
		int prevCol=0;
		
		while (count<=numSeats) {
			if(count==numSeats) break;
			
			if (coupleSeatYesOrNo==0|coupleSeatYesOrNo==2) {
				printMenu("Please select your seats: "+"("+(numSeats-count)+" left)\n");
				printMenu("Seats {A1,A2,A9,B1,B2,B9,C1,C2,C9,D1,D2,D9,E9,F9,G9} are not available. Please refrain from picking those seats.\n");
				printMenu("Select your Row (choose an alphabet from A to G)");
				char result=readCharacter();
				int row=(int)(result-64);
				if (row<1 | row>7) {
					printMenu("Invalid seat. Please pick another seat.\n");
					continue;
				}
				
				if (prevRow==0) {
					prevRow=row;
				}
				
				else if (row!=prevRow) {
					printMenu("Please select seats within the same row.\n");
					continue;
				}
				
				printMenu("Select your Column (choose a number between 1-17, excluding 9)");
				int column=readChoice(1,17);
				
				if (prevCol==0) {
					prevCol=column;
				}
				
				else{
					if (column>prevCol+1 | column<prevCol-1) {
						printMenu("Please select an adjacent seat within the same row.\n");
						continue;
					}
				}
				
				if (showtime.getSpecificSeat(row, column)==null) {
					printMenu("No such seat exists. Please pick another seat.\n");
					continue;
				}
				
				if (showtime.getSpecificSeat(row, column).isAssigned()) {
					printMenu("Seat is already occupied. Please pick another seat.\n");
					continue;
				}
				
				prevCol=column;
				
				showtime.getSpecificSeat(row,column).assignSeat();
				chosenSeats.add(showtime.getSpecificSeat(row, column));
				printMenu("Seat successfully selected.");
				printHeader("Screen");
				showtime.showSeatLayout();
				printHeader("Entrance");
				System.out.println();
				count++;
			}
			else {
				printMenu("Please select your couple seat: "+"("+(numCoupleSeats-coupleSeatCount)+" left)");
				printMenu("Select your Row (choose either H or I)");
				char result=readCharacter();
				int row=(int)(result-64);
				if (row<8 | row>9) {
					printMenu("Invalid seat. Please pick another seat.");
					continue;
				}
				
				printMenu("Select your Column (choose one of 1,3,5,7,10,12,14,16)");
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
				printMenu("Seat successfully selected.");
				printHeader("Screen");
				showtime.showSeatLayout();
				printHeader("Entrance");
				System.out.println();
				count+=2;
				coupleSeatCount++;
			}
		}

		direct(this, new DisplayBookingConfirmation(showtime,chosenSeats,numStudent,numSeniors,userId));
		


		
	}
	
	public static void showPricing() {
		SystemSettings priceList=retrieveSystemSettings();
		double goldClassPrice=2*priceList.getPremiumPrice();
		double goldClassPrice3D=goldClassPrice+priceList.getThreeDIncrement();
		double platSuitesPrice=3*priceList.getPremiumPrice();
		double platSuitesPrice3D=platSuitesPrice+priceList.getThreeDIncrement();
    	double studentPrice=priceList.getStandardPrice()-priceList.getChildDiscount();
    	double studentPrice3D=studentPrice+priceList.getThreeDIncrement();
    	double seniorPrice=priceList.getStandardPrice()-priceList.getSeniorCitizenDiscount();
    	double standardPrice=priceList.getStandardPrice();
    	double standardPrice3D=standardPrice+priceList.getThreeDIncrement();
    	double premiumPrice=priceList.getPremiumPrice();
    	double premiumPrice3D=premiumPrice+priceList.getThreeDIncrement();
    	double weekendPrice=premiumPrice+priceList.getWeekendIncrement();
    	double weekendPrice3D=premiumPrice3D+priceList.getWeekendIncrement();

		
		System.out.println("Ticket Type\t\t Regular&Digital Movies\t 3D Movies");
		System.out.println("\nSenior Citizens*\t $"+seniorPrice+"\t\t\t N.A");
		System.out.println("Mon-Fri, before 6pm");
		System.out.println();
		System.out.println("Students**\t\t $"+studentPrice+"\t\t\t $"+studentPrice3D);
		System.out.println("Mon-Fri, before 6pm");
		System.out.println();
		System.out.println("Mon-Wed#\t\t $"+standardPrice+"\t\t\t $"+standardPrice3D);
		System.out.println("All Sessions");
		System.out.println();
		System.out.println("Thu\t\t\t $"+premiumPrice+"\t\t\t $"+premiumPrice3D);
		System.out.println("All Sessions");
		System.out.println();
		System.out.println("Fri\t\t\t $"+premiumPrice+"\t\t\t $"+premiumPrice3D);
		System.out.println("Sessions before 6pm");
		System.out.println();
		System.out.println("Fri\t\t\t $"+weekendPrice+"\t\t\t $"+weekendPrice3D);
		System.out.println("Sessions after 6pm");
		System.out.println();
		System.out.println("Sat & Sun^\t\t $"+weekendPrice+"\t\t\t $"+weekendPrice3D);
		System.out.println("All Sessions");
		System.out.println();
		System.out.println("Gold Class^^\t\t $"+goldClassPrice+"\t\t\t $"+goldClassPrice3D);
		System.out.println("All Sessions,Daily");
		System.out.println();
		System.out.println("Platinum Suites^^\t $"+platSuitesPrice+"\t\t\t $"+platSuitesPrice3D);
		System.out.println("All Sessions,Daily");
		System.out.println();
		System.out.println("\n**Not valid on PH/eve of PH | ^Include PH/eve of PH and weekend sneaks | *For patrons 55 years & older. Not valid on PH/eve of PH | ^^Applies for Monday-Sunday,including PH/eve of PH");
	}
}
