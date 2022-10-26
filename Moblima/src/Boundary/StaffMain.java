package Boundary;

import static Controller.MiscMethods.*;
import static Controller.CRUDMovies.*;

import Boundary.Staff.ModifyTheatreBoundary;
import Boundary.Staff.MovieListingEditBoundary;
import Entity.Movie;

public class StaffMain extends Boundary {
    @Override
    protected void start() {
        
        //SupportFunctions.clearScreen();
        //NEED TO DO LOG IN PAGE.. this one is just for testing
        printHeader("Staff");
        printMenu("Welcome, please make a selection:",
                "1. Modify Movie Listing",
                "2. Modify theatres",
                "3. Back","");

        int choice = readChoice(1, 3);

        switch (choice) {
            case 1:
                direct(this, new MovieListingEditBoundary());
                //SupportFunctions.clearScreen();
                end();
                break;
            case 2:
                direct(this, new ModifyTheatreBoundary());
                //SupportFunctions.clearScreen();
                end();
                break;
            case 3:
                end();
                break;
        }
    }
}
