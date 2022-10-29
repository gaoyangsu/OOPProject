package Boundary.MovieGoer;

import Boundary.Boundary;
import Boundary.SupportFunctions;
import Entity.Movie;
import Entity.MovieEnums;
import Entity.Review;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static Controller.MiscMethods.*;

import static Controller.MiscMethods.printHeader;
import static Controller.CRUDMovies.*;

public class DisplayReviewBoundary extends Boundary {

    private Movie movie;

    public DisplayReviewBoundary(Movie movie){
        this.movie=movie;
    };

    @Override
    protected void start() {
        display();
    }

    private void display(){
        SupportFunctions.clearScreen();
        printHeader("Review" + " (" + movie.getMovieName() + ")");
        if (movie.getMovieStatus()== MovieEnums.MovieStatus.COMING_SOON || movie.getMovieStatus()== MovieEnums.MovieStatus.PREVIEW){
            readString("Not allowed to comment on movies yet to come." + "\n\nPress any key to return");
            end();
        }
        
        printMenu("1. Give a Review/Rating",
                "2. View all Reviews/Ratings",
                "3. Go back", "");
        int choice = readChoice(1, 3);
        switch (choice) {
            case 1:
                giveAReview();
                break;
            case 2:
                showReviews();
                break;
            case 3:
                end();
                break;
        }
        end();
    }

    private void giveAReview(){
        
        SupportFunctions.clearScreen();
        Scanner sc= new Scanner(System.in);
        printHeader("Please Give us a review");
        System.out.println("Please enter your name (press enter to return)");
        String name= sc.nextLine();
        if(name == ""){display();}
        System.out.println("Give your ratings for this movie (1-5)");
        int rating= readChoice(1,5);
        System.out.println("Please give us your written review");
        String review= sc.nextLine();

        Review review1 = new Review(rating, review, this.movie, name);
        //For serialising the data
        try {
            addReviewIntoList(movie, review1);
            System.out.println("Review Given!");
            System.out.println("Press any key to return");
            String r= sc.nextLine();
        }
        catch (IOException ex) {
            System.out.println("Review failed to upload!");
            System.out.println("Press any key to return");
            String r= sc.nextLine();
        }
        finally {
            display();
        }
    }

    private void showReviews(){
        
        SupportFunctions.clearScreen();
        Scanner sc= new Scanner(System.in);
        ArrayList<Review> listOfReview = retrieveReviewList(movie);
        if(!(listOfReview==null)){

            for (Review ratings : listOfReview) {
                System.out.println( " Name:     " + ratings.getName());
                System.out.println( " Rating:   " + ratings.getRating());
                System.out.println( " Comments: " + addLinebreaks(ratings.getReview(), 45, 12));
                System.out.println();

            }
        }
        else System.out.println("No review!");
        System.out.println("Press any key to return");
        String r= sc.nextLine();
        display();

    }
}



