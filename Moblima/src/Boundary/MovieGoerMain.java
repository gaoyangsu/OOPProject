package Boundary;

import Boundary.MovieGoer.DisplayBookingHistoryBoundary;
import Boundary.MovieGoer.DisplayMovieListBoundary;
import Entity.Customer;
import static Controller.CustomerController.*;
<<<<<<< Updated upstream
import static Controller.MiscMethods.*;
import static Controller.CustomerController.*;
import Entity.Customer;

public class MovieGoerMain extends Boundary{
    private boolean signedIn;
=======

import static Controller.MiscMethods.*;
import static Controller.CustomerController.*;
import Entity.Customer;

public class MovieGoerMain extends Boundary{
    private boolean signedIn;

>>>>>>> Stashed changes
    @Override
    protected void start(){
        displayMovieGoerView();

    }
    private void displayMovieGoerView() {
        SupportFunctions.clearScreen();
        printHeader("Moviegoer");
        printMenu("Welcome, please make a selection:",
                "1. Search or list movies",
                "2. View booking history",
                "3. Back","");

        int choice = readChoice(1, 4);

        switch (choice) {
            case 1:
<<<<<<< Updated upstream
                direct(this, new DisplayMovieListBoundary());
                end();
                break;
            case 2:
            	direct(this, new DisplayBookingHistoryBoundary());
=======
                direct(this, new DisplayMovieListBoundary(""));
                end();
                break;
            case 2:
            	direct(this, new DisplayBookingHistoryBoundary(""));
>>>>>>> Stashed changes
                end();
                break;
            case 3:
            	end();
                break;
        }
    }
}
