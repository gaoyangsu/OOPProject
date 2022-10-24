package Boundary;

import static Controller.MiscMethods.*;
import static Controller.CRUDMovies.*;

public class DisplayMovieListBoundary extends Boundary {
    @Override
    protected void start() {
        printHeader("Movie List");
        PrintAllMovieNames();
        System.out.println("");
        printMenu("Welcome, please make a selection:",
                "1. Buy Ticket for a Movie",
                "2. Back","");

        int choice = readChoice(1, 2);

        switch (choice) {
            case 1:
                SupportFunctions.clearScreen();
                end();
                break;
            case 2:
                SupportFunctions.clearScreen();
                end();
                break;
        }
    }
}
