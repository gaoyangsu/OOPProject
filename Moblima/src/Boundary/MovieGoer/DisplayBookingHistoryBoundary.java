package Boundary.MovieGoer;

import static Controller.MiscMethods.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import Boundary.Boundary;
import Boundary.SupportFunctions;
import Entity.Booking;
import Entity.Customer;
import Entity.Holiday;
import Entity.Seat;

import static Controller.CRUDCustomerBooking.*;
import static Controller.CustomerController.*;
import static Controller.AdminController.*;


/**
    Boundary/View Class to display the past booking history of the moviegoer
	if logged in as member, automatically provide details of past bookings
	else as a guest, enter email used to booked the ticket to check past bookings
    @version 1.0
    @since 2022-11-01
 */
public class DisplayBookingHistoryBoundary extends Boundary {
	/** UID of customer, to check if logged in ornot */
	private String userId;
	
	/**
	 * Constructor taking in
	 * @param userId
	 * to check if logged in as member or guest only
	 */
	public DisplayBookingHistoryBoundary(String userId) {
		this.userId=userId;
	}
	
	 /**
     * overriden start method from Boundary abstract class
     */
    @Override
    protected void start() {
        //SupportFunctions.clearScreen();
        display();
    }
     /**
     * display menu Find past bookinglisting
	 * based on the email of member/guest
	 * will call showBookings if credentials found
     */
    private void display() {
    	ArrayList<Booking> bookings=retrieveBookingList();
        int indicator=-1;
        ArrayList<Booking> foundBookings=new ArrayList<Booking>();
        
        printHeader("Booking History");
        
        if (this.userId.equals("")) {
        	String email=readString("For Guests...Please enter your email to view your booking history: ");
            
            for (Booking b:bookings) {
            	if (b.getCustomerEmail().equals(email)) {
            		foundBookings.add(b);
            		indicator=1;
            	}
            }
        }
        
        else {
        	ArrayList<Customer> customers=retrieveCustomerList();
        	String targetEmail="";
        	for (Customer c:customers) {
        		if (c.getMovieGoerId().equals(userId)) {
        			targetEmail=c.getEmail();
        			break;
        		}
        	}
        	
        	for (Booking b:bookings) {
            	if (b.getCustomerEmail().equals(targetEmail)) {
            		foundBookings.add(b);
            		indicator=1;
            	}
            }
        }
        
        if (indicator==-1) {
        	printMenu("Record(s) not found.");
			System.out.println("Input a character to return");
			readCharacter();
        	end();
        }
        
        if (indicator==1) {
        	showBookings(foundBookings);
        	//end();
        }
    }
    
    
	/** 
	 * this method is to show the list the bookings if past bookings found for the specified email
	 * @param foundBookings
	 */
	private void showBookings(ArrayList<Booking> foundBookings) {
    	int index=0;
    	int indicator=-1;
    	Date target=foundBookings.get(0).getShowTime();
    	Calendar cal=Calendar.getInstance();
    	cal.setTime(target);
    	
    	ArrayList<Holiday> holidayList=retrieveHolidays();
    	Calendar cal2=Calendar.getInstance();
    	
    	for (Holiday h:holidayList) {
    		cal2.setTime(h.getDate());
    		if (cal2.get(Calendar.DAY_OF_YEAR)==cal.get(Calendar.DAY_OF_YEAR)) {
    			indicator=1;
    			break;
    		}
    	}
    	
    	for (Booking b:foundBookings) {
    		ArrayList<Seat> seat=b.getSeat();
    		String movieName=seat.get(0).getShowSchedule().getMovie().getMovieName();
    		String seatInfo="{";
    		
    		for (Seat s:seat) {
    			int row=s.getRow();
    			int col=s.getCol()+1;
    			if (seat.indexOf(s)==seat.size()-1) {
    				seatInfo+=getCharForNumber(row)+col;
    			}
    			else {
    				seatInfo+=getCharForNumber(row)+col+",";
    			}
    		}
    		
    		seatInfo+="}";
    		
    		if (indicator==1) {
    			printMenu(++index + ". " +movieName+ "\t" + b.getTransactionId() + generateSpaces(20 - b.getTransactionId().length())
                + "(" + b.getShowTime() + ")"
                + "  S$" + b.getPrice()+"\t"
                + seatInfo
                + "  PH/PH eve");
    		}
    		else {
    			printMenu(++index + ". " +movieName+ "\t"  + b.getTransactionId() + generateSpaces(20 - b.getTransactionId().length())
                + "(" + b.getShowTime() + ")"
                + "  S$" + b.getPrice()+"\t"
                + seatInfo);
    		}
    	}

		System.out.println("Press 0 to return");
		int choice = readChoice(0,0);
        if (choice == 0) {
            end();
            return;
        }
    }
    
    
	/** 
	 * A converter from char, the row alphabets of the seating layout of the theatre
	 * to a number, readable by the 2-D array
	 * @param i
	 * @return String
	 */
	public static String getCharForNumber(int i){

        // return null for bad input
        if(i < 0){
            return null;
        }

        // convert to base 26
        String s = Integer.toString(i, 26);

        char[] characters = s.toCharArray();

        String result = "";
        for(char c : characters){
            // convert the base 26 character back to a base 10 integer
            int x = Integer.parseInt(Character.valueOf(c).toString(), 26);
            // append the ASCII value to the result
            result += String.valueOf((char)(x + 'A'));          
        }

        return result;
    }

	
}
