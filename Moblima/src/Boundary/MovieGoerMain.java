package Boundary;

import Boundary.MovieGoer.DisplayMovieListBoundary;
import Boundary.MovieGoer.MakeBookingBoundary;

import static Controller.MiscMethods.*;

public class MovieGoerMain extends Boundary{
    @Override
    protected void start() {
        
        SupportFunctions.clearScreen();
        printHeader("Moviegoer");
        printMenu("Welcome, please make a selection:",
                "1. Search or list movies",
                "2. Make a booking",
                "3. View booking history",
                "4. Back","");

        int choice = readChoice(1, 4);

        switch (choice) {
            case 1:
                direct(this, new DisplayMovieListBoundary());
                //PrintAllMovieNames();
                //direct(this, new MovieGoerMain());
                break;
            case 2:
            	direct(this, new MakeBookingBoundary());
                //direct(this, new MovieGoerMain());
                end();
                break;
            case 3:
            	//direct(this, new DisplayBookingHistoryBoundary());
                //direct(this, new MovieGoerMain());
            	end();
            	break;
            case 4:
                end();
                break;
        }
    }
}
