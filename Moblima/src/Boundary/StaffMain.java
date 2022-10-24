package Boundary;

import static Controller.MiscMethods.*;
import static Controller.CRUDMovies.*;
import Controller.Movie;

public class StaffMain extends Boundary {
    @Override
    protected void start() {
        printHeader("Staff");
        printMenu("Welcome, please make a selection:",
                "1. Add new movie",
                "2. Remove movie",
                "3. Back","");

        int choice = readChoice(1, 3);

        switch (choice) {
            case 1:
                addMovie(new Movie(123, "shrek", "HI"));
                end();
                break;
            case 2:
                removeMovie(new Movie(123, "shrek", "HI"));
                end();
                break;
            case 3:
                end();
                break;
        }
    }
}
