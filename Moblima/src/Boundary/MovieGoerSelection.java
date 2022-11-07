package Boundary;

import static Controller.MiscMethods.printHeader;
import static Controller.MiscMethods.printMenu;
import static Controller.MiscMethods.readChoice;

/**
    Boundary/View Class representing a Moviegoer page to either book tickets as guest,
	 or log in as a member
   
    @version 1.0
    @since 2022-11-01
 */
public class MovieGoerSelection extends Boundary{
	
	/**
     * overriden start method from Boundary abstract class
     */
	 @Override
	    protected void start() {
	        
	        SupportFunctions.clearScreen();
	        printHeader("MovieGoer");
	        printMenu("Welcome, please make a selection:",
	                "1. Book as a guest",
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
