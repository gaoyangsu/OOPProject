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

public class DisplaySearchMovieBoundary extends Boundary{
    
    protected void start() {
        display();
    }

    private void display() {
        
        SupportFunctions.clearScreen();
        printHeader("Search Movie");
        printMenu("Please enter movie title:");
        searchResults();

    }

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
            Movie movie = listOfMovie.get(choice - 1);
            movieDetailView(movie);
        }
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
                break;
        }
       // displayMovieListing();
    }

}
