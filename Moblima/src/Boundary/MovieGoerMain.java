package Boundary;

import Boundary.MovieGoer.DisplayBookingHistoryBoundary;
import Boundary.MovieGoer.DisplayMovieListBoundary;
import Entity.Customer;
import static Controller.CustomerController.*;
import static Controller.MiscMethods.*;
import static Controller.CustomerController.*;
import Entity.Customer;


import static Controller.MiscMethods.*;
import static Controller.CustomerController.*;
import Entity.Customer;

/**
    Boundary/View Class representing a the Moviegoer main after logging in as member
    or choose to book ticket as a guest
    @version 1.0
    @since 2022-10-23
 */
public class MovieGoerMain extends Boundary{
    
    /**boolean to denote if user signed in as member or logged in as guest */
    private boolean signedIn;

    /**
     * overriden start method from Boundary abstract class
     */
    @Override
    protected void start(){
        displayMovieGoerView();

    }

    /**
     * display menu for moviegoers, to see if they want to search or list movies, or make booking history
     */
    private void displayMovieGoerView() {
        SupportFunctions.clearScreen();
        printHeader("MovieGoer");
        printMenu("Welcome, please make a selection:",
                "1. Search or list movies",
                "2. View booking history",
                "3. Back","");

        int choice = readChoice(1, 4);

        switch (choice) {
            case 1:
                direct(this, new DisplayMovieListBoundary(""));
                end();
                break;
            case 2:
            	direct(this, new DisplayBookingHistoryBoundary(""));
                end();
                break;
            case 3:
            	end();
                break;
        }
    }
}
