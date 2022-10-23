package Boundary.MovieGoer;

import Boundary.Boundary;

import static Controller.MiscMethods.*;

public class DisplayMovieListBoundary extends Boundary {
    @Override
    protected void start() {

    }

    private void display() {
        printHeader("Search or list movies");
        printMenu("1. Search movies",
                "2. List all movies",
                "3. List the top 5 movies",
                "4. Go back","");
        int choice = readChoice(1, 4);
        switch (choice) {
            case 1:
                //searchMovie();
                break;
            case 2:
                //topFive = false;
                //displayMovieListing();
                break;
            case 3:
                //topFive = true;
                //displayMovieListing();
                break;
            case 4:
                break;
        }

        end();
    }
}
