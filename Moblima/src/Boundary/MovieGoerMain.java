package Boundary;

import Boundary.MovieGoer.DisplayBookingHistoryBoundary;
import Boundary.MovieGoer.DisplayMovieListBoundary;
<<<<<<< HEAD
=======
<<<<<<< HEAD
import Boundary.MovieGoer.MakeBookingBoundary;
import Entity.Customer;
import static Controller.CustomerController.*;
>>>>>>> main
import static Controller.MiscMethods.*;
import static Controller.CustomerController.*;
import Entity.Customer;
public class MovieGoerMain extends Boundary{
    private boolean signedIn;
=======
import static Controller.MiscMethods.*;

public class MovieGoerMain extends Boundary{
	
>>>>>>> 1362c6aa0af163a7eaafc90ca78fd36bb3e4c11d
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
                direct(this, new DisplayMovieListBoundary());
<<<<<<< HEAD
                end();
                break;
            case 2:
            	direct(this, new DisplayBookingHistoryBoundary());
=======
>>>>>>> main
                end();
                break;
<<<<<<< HEAD
            case 3:
<<<<<<< HEAD
=======
            	//direct(this, new DisplayBookingHistoryBoundary());
                //direct(this, new MovieGoerMain());
=======
            case 2:
            	direct(this, new DisplayBookingHistoryBoundary());
>>>>>>> 1362c6aa0af163a7eaafc90ca78fd36bb3e4c11d
                end();
                break;
            case 3:
>>>>>>> main
            	end();
                break;
        }
    }
}
