package Boundary;

import Boundary.MovieGoer.DisplayMovieListBoundary;

import static Controller.MiscMethods.*;

public class MovieGoerMain extends Boundary{
    @Override
    protected void start() {
        
        SupportFunctions.clearScreen();
        printHeader("Moviegoer");
        printMenu("Welcome, please make a selection:",
                "1. Search or list movies",
                "2. View booking history",
                "3. Back","");

        int choice = readChoice(1, 3);

        switch (choice) {
            case 1:
                direct(this, new DisplayMovieListBoundary());
                //PrintAllMovieNames();
                //direct(this, new MovieGoerMain());
                break;
            case 2:
                //direct(this, new DisplayBookingHistoryBoundary());
                //direct(this, new MovieGoerMain());
                end();
                break;
            case 3:
                end();
                break;
        }
    }
}
