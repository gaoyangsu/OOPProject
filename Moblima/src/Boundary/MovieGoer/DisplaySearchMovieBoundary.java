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
        printMenu("Please enter movie title (press enter to return):");
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
            direct(this, new DisplayMovieDetailsBoundary(movie));
        }
    }

}
