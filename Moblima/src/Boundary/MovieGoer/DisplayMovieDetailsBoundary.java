package Boundary.MovieGoer;

import Boundary.Boundary;
import Boundary.SupportFunctions;
import Entity.Movie;
import Entity.MovieEnums;
import Entity.Review;

import static Controller.CRUDMovies.*;
import static Controller.MiscMethods.*;


import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import static Controller.MiscMethods.*;

public class DisplayMovieDetailsBoundary extends Boundary{
	private String userId;
    private Movie movie;
    
    public DisplayMovieDetailsBoundary(Movie movie,String userId){
        this.movie = movie;
        this.userId=userId;
    }

    protected void start() {
        display();
    }

    private void display() {
        movieDetailView(movie);
    }


    private void movieDetailView(Movie movie){

        Scanner sc= new Scanner(System.in);

        SupportFunctions.clearScreen();
        printHeader("Movie Details");

        if(movie.getMovieStatus().toString()=="COMING SOON"){
        	printMenu(movie.toString());
        	System.out.println();
            System.out.println(movie.getMovieName()+ " is Coming soon");
            System.out.println("Stay tuned for updates about this film!");
            System.out.println("Press any key to return");
            sc.nextLine();
            end();
        }
        
        printMenu(
                movie.toString(),
                "1. Make a booking",
                "2. Display/write movie reviews/rating",
                "3. Go back", "");

        int choice = readChoice(1, 3);
        switch (choice) {
            case 1:
                direct(this, new DisplayShowTimeBoundary(movie,userId));
                break;
            case 2:
                direct(this, new DisplayReviewBoundary(movie,""));
                break;
            case 3:
                end();
                break;
        }
    }
}
