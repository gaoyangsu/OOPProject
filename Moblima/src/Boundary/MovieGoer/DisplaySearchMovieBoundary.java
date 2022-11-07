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

/**
    Boundary/View Class to search for a movie instead of listing all the movies
    @version 1.0
    @since 2022-10-27
 */

public class DisplaySearchMovieBoundary extends Boundary{
    /** UID to be passed in to check if member customer log-in or guest */
	private String userId;

    /**
     * Constructor used to passed in the userID
     * @param userId
     */
	
	public DisplaySearchMovieBoundary(String userId) {
		this.userId=userId;
	}

    /**
     * overriden start method from Boundary abstract class
     */
    
    protected void start() {
        display();
    }

    /**
     * display menu for moviegoers to enter their search for a particular movie,
     * calls searchResults() for outcome
     */
    private void display() {
        
        SupportFunctions.clearScreen();
        printHeader("Search Movie");
        printMenu("Please enter movie title (press enter to return):");
        searchResults();

    }
    

    /** this method Finds the movie based on the input movie String
     * if have list out movie
     * if dont have, show message that the movie doesnt exist
     */

    private void searchResults(){
        ArrayList<Movie> listOfMovie;
        ArrayList<Movie> listOfSearchResults = new ArrayList<Movie>();
        listOfMovie = retrieveMovieList();
        Scanner sc = new Scanner(System.in);
        String search=sc.nextLine();


        for(Movie movie: listOfMovie){
            String title = movie.getMovieName();
            search = search.toLowerCase();
            title = title.toLowerCase();
            if(search.isEmpty()){end();}
            if(title.indexOf(search) !=-1? true: false){
                listOfSearchResults.add(movie);
            }
        }

        int count=0;
        if(listOfSearchResults.isEmpty()){
            System.out.println("\nSorry, but this movie does not exist :(");
        }
        else{
            System.out.println("\nSearch Results: ");
            for(Movie result:listOfSearchResults){
                printMenu(++count + ". " + result.getMovieName());
            }
        }
        printMenu("\n" + (count + 1) + ". Go back", "");

        int choice = readChoice(1, count + 1);

        if (choice == count + 1) end();
        else {
            Movie movie = listOfSearchResults.get(choice - 1);
            direct(this, new DisplayMovieDetailsBoundary(movie,userId));
        }
    }

}
