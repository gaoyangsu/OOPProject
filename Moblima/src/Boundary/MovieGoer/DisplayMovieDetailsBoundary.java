package Boundary.MovieGoer;

import Boundary.Boundary;
import Boundary.SupportFunctions;
import Entity.Movie;
import Entity.MovieEnums;

import static Controller.CRUDMovies.*;
import static Controller.MiscMethods.*;


import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import static Controller.MiscMethods.*;

public class DisplayMovieDetailsBoundary extends Boundary{

    private Movie movie;
    public DisplayMovieDetailsBoundary(Movie movie ){
        this.movie = movie;
    }

    protected void start() {
        display();
    }

    private void display() {
        movieDetailView(movie);
    }


    private void movieDetailView(Movie movie){
        SupportFunctions.clearScreen();
        printHeader("Movie details");
        printMenu(movie.toString(),
                "1. Display showtime",
                "2. Display/write reviews",
                "3. Go back", "");

        int choice = readChoice(1, 3);
        switch (choice) {
            case 1:
                direct(this, new DisplayShowTimeBoundary(movie));
                break;
            case 2:
                //intent(this, new ReviewView(movie));
                break;
            case 3:
                end();
                break;
        }
       // displayMovieListing();
    }
}
