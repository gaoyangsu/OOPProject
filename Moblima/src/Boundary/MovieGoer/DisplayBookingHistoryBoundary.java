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

public class DisplayBookingHistoryBoundary extends Boundary {
	private String userId;
	
	public DisplayBookingHistoryBoundary(String userId) {
		this.userId=userId;
	}
	
    @Override
    protected void start() {
        //SupportFunctions.clearScreen();
        display();
    }
    
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
    			printMenu(++index + ". " + b.getTransactionId() + generateSpaces(20 - b.getTransactionId().length())
                + "(" + b.getShowTime() + ")"
                + "  S$" + b.getPrice()+"\t"
                + seatInfo
                + "  PH/PH eve");
    		}
    		else {
    			printMenu(++index + ". " + b.getTransactionId() + generateSpaces(20 - b.getTransactionId().length())
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
