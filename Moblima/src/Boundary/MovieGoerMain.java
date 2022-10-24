package Boundary;

import static Controller.MiscMethods.*;

public class MovieGoerMain extends Boundary{
    @Override
    protected void start() {
        printHeader("Moviegoer");
        printMenu("Welcome, please make a selection:",
                "1. Search or list movies",
                "2. View booking history",
                "3. Back","");

        int choice = readChoice(1, 3);

        switch (choice) {
            case 1:
                SupportFunctions.clearScreen();
                direct(this, new DisplayMovieListBoundary());
                //PrintAllMovieNames();
                //direct(this, new MovieGoerMain());
                break;
            case 2:
                SupportFunctions.clearScreen();
                //direct(this, new DisplayBookingHistoryBoundary());
                //direct(this, new MovieGoerMain());
                end();
                break;
            case 3:
                SupportFunctions.clearScreen();
                end();
                break;
        }
    }
}
