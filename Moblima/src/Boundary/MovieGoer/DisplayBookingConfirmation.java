package Boundary.MovieGoer;

import Boundary.Boundary;
import Boundary.MovieGoerMain;
import Boundary.SupportFunctions;
import Entity.Booking;
import Entity.Customer;
import Entity.Movie;
import Entity.MovieEnums;
import Entity.Seat;
import Entity.ShowSchedule;
import Entity.SystemSettings;
import Entity.TheatreEnums.TheatreClass;

import static Controller.CRUDMovies.*;
import static Controller.CRUDShowSchedule.*;
import static Controller.AdminController.*;
import static Controller.MiscMethods.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static Controller.MiscMethods.*;

public class DisplayBookingConfirmation extends Boundary{

    private ShowSchedule schedule;
    private ArrayList<Seat> bookedSeats;
    private int numStudent;
    private int numSenior;
    private int numAdult;
    
    public DisplayBookingConfirmation(ShowSchedule schedule,ArrayList<Seat> bookedSeats,int numStudent,int numSenior){
        this.schedule=schedule;
        this.bookedSeats=bookedSeats;
        this.numStudent=numStudent;
        this.numSenior=numSenior;
        this.numAdult=bookedSeats.size()-numStudent-numSenior;
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
    	int studentCount=numStudent;
    	int seniorCount=numSenior;
    	int adultCount=numAdult;
    	double total=0;
    	boolean is3d=schedule.getTheatre().isIs3D();
    	TheatreClass typeOfSeats=schedule.getTheatre().getTheatreClass();
    	double studentPrice=priceList.getStandardPrice()-priceList.getChildDiscount();
    	double seniorPrice=priceList.getStandardPrice()-priceList.getSeniorCitizenDiscount();
    	double price=priceList.getStandardPrice();
    	
    	Calendar cal=Calendar.getInstance();
    	cal.setTime(schedule.getTime());
    	
    	int dayOfWeek=cal.get(Calendar.DAY_OF_WEEK);
    	int hourOfDay=cal.get(Calendar.HOUR_OF_DAY);
    	
    	if (typeOfSeats.toString()=="Gold Class") {
    		price+=priceList.getPremiumPrice();
    	}
    	
    	else if (typeOfSeats.toString()=="Platinum Suites") {
    		price+=2*priceList.getPremiumPrice();
    	}
    	
    	if (is3d) {
    		studentPrice+=priceList.getThreeDIncrement();
    		price+=priceList.getThreeDIncrement();
    	}
    	
    	if (dayOfWeek==1 | dayOfWeek==7 | (dayOfWeek==6 && hourOfDay>=18)) {
    		price+=priceList.getHolidaysIncrement();
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
    	
    	 printMenu("Would you like to confirm your booking?",
         		"\n1.Yes",
         		"\n2.No");
    	 int choice=readChoice(1,2);
         if (choice==1) {
        	 String name=readString("Please enter your name");
        	 System.out.println();
        	 String email=readString("Please enter your email address");
        	 String number=readString("Please enter your mobile number");
        	 int phoneNumber=Integer.parseInt(number);
        	 Customer newCustomer=new Customer(name,email,phoneNumber);
        	 newCustomer.setMovieGoerId(email.substring(0,4)+number.substring(0,4));

        	 Booking newBooking=new Booking(schedule.getTime(),schedule.getTheatre().getCode(),schedule.getMovie().getMovieName(),total,bookedSeats);
        	 
        	 newCustomer.addBookingEntries(newBooking);
        	 
        	 printMenu("\nBooking Confirmed!");
         }

		 else if(choice==2){
			for (Seat seat: bookedSeats){
				seat.unassignSeat();
			}
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
		 
         direct(this,new MovieGoerMain());
    }
}
