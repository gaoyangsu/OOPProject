package Boundary.MovieGoer;

import Boundary.Boundary;
import Boundary.SupportFunctions;
import Entity.Booking;
import Entity.Customer;
import Entity.Holiday;
import Entity.Movie;
import Entity.Seat;
import Entity.ShowSchedule;
import Entity.SystemSettings;
import Entity.TheatreEnums.TheatreClass;

import static Controller.CRUDMovies.*;
import static Controller.CRUDShowSchedule.*;
import static Controller.AdminController.*;
import static Controller.MiscMethods.*;
import static Controller.CRUDCustomerBooking.*;
import static Controller.CustomerController.*;

import java.io.IOException;
import java.util.*;

public class DisplayBookingConfirmation extends Boundary{
	private String userId;
    private ShowSchedule schedule;
    private ArrayList<Seat> bookedSeats;
    
    private int numStudent;
    private int numSenior;
    private int numAdult;
    
    public DisplayBookingConfirmation(ShowSchedule schedule,ArrayList<Seat> bookedSeats,int numStudent,int numSenior,String userId){
        this.schedule=schedule;
        this.bookedSeats=bookedSeats;
        this.numStudent=numStudent;
        this.numSenior=numSenior;
        this.numAdult=bookedSeats.size()-numStudent-numSenior;
        this.userId=userId;
    };

    protected void start() {
        display();
    }

    private void display() {
        SupportFunctions.clearScreen();
        printHeader("Booking Confirmation");
        BookingConfirmation();
    }

    private void BookingConfirmation(){
    	SystemSettings priceList=retrieveSystemSettings();
    	ArrayList<Holiday> holidayList=retrieveHolidays();
    	int studentCount=numStudent;
    	int seniorCount=numSenior;
    	int adultCount=numAdult;
    	double total=0;
    	boolean is3d=schedule.getTheatre().isIs3D();
    	TheatreClass typeOfSeats=schedule.getTheatre().getTheatreClass();
    	double studentPrice=priceList.getStandardPrice()-priceList.getChildDiscount();
    	double seniorPrice=priceList.getStandardPrice()-priceList.getSeniorCitizenDiscount();
    	double price=0;
    	int holidayIndicator=-1;
    	
    	Calendar cal=Calendar.getInstance();
    	cal.setTime(schedule.getTime());
    	
    	Calendar cal2=Calendar.getInstance();
    	for (Holiday h:holidayList) {
    		cal2.setTime(h.getDate());
    		if (cal2.get(Calendar.DAY_OF_YEAR)==cal.get(Calendar.DAY_OF_YEAR)) {
    			holidayIndicator=1;
    			break;
    		}
    	}
    	
    	int dayOfWeek=cal.get(Calendar.DAY_OF_WEEK);
    	int hourOfDay=cal.get(Calendar.HOUR_OF_DAY);
    	
    	if (dayOfWeek==2 | dayOfWeek==3 | dayOfWeek==4) {
    		price=priceList.getStandardPrice();
    	}
    	
    	if (dayOfWeek==5 | dayOfWeek==6 | dayOfWeek==7 | dayOfWeek==1 | holidayIndicator==1) {
    		price=priceList.getPremiumPrice();
    	}
    	
    	if (dayOfWeek==1 | dayOfWeek==7 | (dayOfWeek==6 && hourOfDay>=18) | holidayIndicator==1) {
    		price+=priceList.getHolidaysIncrement();
    	}
    		
		if (typeOfSeats.toString()=="Gold Class") {
    		price=2*priceList.getPremiumPrice();
    	}
    	
    	else if (typeOfSeats.toString()=="Platinum Suites") {
    		price=3*priceList.getPremiumPrice();
    	}
		
		if (is3d) {
    		studentPrice+=priceList.getThreeDIncrement();
    		price+=priceList.getThreeDIncrement();
    	}
    		
    	while (studentCount!=0) {
    		total+=studentPrice;
    		studentCount--;
    	}
    	
    	while (seniorCount!=0) {
    		total+=seniorPrice;
    		seniorCount--;
    	}
    	
    	while (adultCount!=0) {
    		total+=price;
    		adultCount--;
    	}  	
    	
    	System.out.println("Tickets Purchased for: "+schedule.getMovie().getMovieName()+"      "+schedule.getTheatre().getCode()+"      "+"ShowTime: "+dateOutput(schedule.getTime()));
    	if (numStudent!=0) {
    		System.out.println("\nChildren Tickets(x"+numStudent+"): "+studentPrice+" ea");
    	}
    	if (numSenior!=0) {
    		System.out.println("\nSenior Citizen Tickets(x"+numSenior+"): "+seniorPrice+" ea");
    	}
    	System.out.println("\nAdult Tickets(x"+numAdult+"): "+price+" ea");
    	System.out.println("\nTotal Amount: "+total);
    	System.out.println();
    	
    	if (schedule.getMovie().getAgeAdvisory().toString().equals("M18")|schedule.getMovie().getAgeAdvisory().toString().equals("R21")) {
			printMenu("Please note that this movie is rated "+schedule.getMovie().getAgeAdvisory().toString());
			printMenu("MOBLIMA welcomes all guests aged 18 and above to the Platinum Movie Suites.");
			System.out.println("Press Y to proceed.");
            if (readCharacter()!='Y') {
            	for (Seat seat: bookedSeats){
    				seat.unassignSeat();
    			}
    			total=0;
    			studentCount=0;
    			seniorCount=0;
    			adultCount=0;
    			bookedSeats=new ArrayList<Seat>();
    			end();
            }
		}
    	
    	 printMenu("Would you like to confirm your booking?",
         		"\n1.Yes",
         		"\n2.No");
    	 int choice=readChoice(1,2);
         if (choice==1) {
        	 String name="";
        	 String email="";
        	 int number=0;
        	 if (userId=="") {
        		 name=readString("Please enter your name");
            	 email=readString("Please enter your email address");
        		 number=Integer.parseInt(readString("Please enter your mobile number"));
        	 }
        	 
        	 else {
        		 ArrayList<Customer> customers=retrieveCustomerList();
        		 for (Customer c:customers) {
        			 if (c.getMovieGoerId().equals(userId)) {
        				 name=c.getName();
        				 email=c.getEmail();
        				 number=c.getContact();
        			 }
        		 }
        	 }
        	 
        	 Booking newBooking=new Booking(name,email,number,schedule,new Date(),total,bookedSeats);
        	 try {
        		 retrieveBookingList().add(newBooking);
        		 updateBookingList();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Booking unsuccessfully saved.");
			}
        	 printMenu("\nBooking Confirmed!");
         }

		 else if(choice==2){
			for (Seat seat: bookedSeats){
				seat.unassignSeat();
			}
			total=0;
			studentCount=0;
			seniorCount=0;
			adultCount=0;
			bookedSeats=new ArrayList<Seat>();
			end();
		 }

		try {
			Movie movieToAppend= schedule.getMovie();
			retrieveMovieList().get(retrieveMovieList().indexOf(movieToAppend)).increaseCount(bookedSeats.size());
		
            updateMovieList();
            
			updateMovieShowSchedule();
            System.out.println("Payment has been made. We wish you a great day!");
        }
        catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Payment failed.");
        }
		 
         end();
    }
}
