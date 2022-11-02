package Boundary;

import static Controller.MiscMethods.printHeader;
import static Controller.MiscMethods.printMenu;
import static Controller.MiscMethods.readChoice;

import Boundary.MovieGoer.DisplayBookingHistoryBoundary;
import Boundary.MovieGoer.DisplayMovieListBoundary;

public class MovieGoerSelection extends Boundary{
	
	 @Override
	    protected void start() {
	        
	        SupportFunctions.clearScreen();
	        printHeader("Moviegoer");
	        printMenu("Welcome, please make a selection:",
	                "1. Log in as guest",
	                "2. Log in as member",
	                "3. Back","");

	        int choice = readChoice(1, 3);

	        switch (choice) {
	            case 1:
	                direct(this, new MovieGoerMain());
	                end();
	                break;
	            case 2:
	            	direct(this, new MovieGoerMainLogin());
	                end();
	                break;
	            case 3:
	            	end();
	                break;
	        }
	    }
}
