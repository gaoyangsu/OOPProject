package Boundary.MovieGoer;

import Boundary.Boundary;
import Entity.Movie;
import Entity.MovieEnums;

import static Controller.CRUDMovies.*;
import static Controller.MiscMethods.*;


import java.util.ArrayList;

import static Controller.MiscMethods.*;

public class DisplayMovieListBoundary extends Boundary {

    private boolean isTopFive =false;
    protected void start() {
        display();
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
                isTopFive=false;
                movieListingView();
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


    private void movieListingView() {
        ArrayList<Movie> listOfMovie;

        if (isTopFive) listOfMovie = retrieveTopFiveMovie();
        else listOfMovie = retrieveMovieList();

        printHeader("Movies");
        if (listOfMovie.isEmpty()) {
            printMenu("Movie listing is not available");
            movieListingView();
        }

        int index = 0;


            for (Movie movie : listOfMovie) {  // show ticket sales

                printMenu(++index + ". " + movie.getMovieName() + generateSpaces(47 - movie.getMovieName().length())
                        + "(" + movie.getMovieStatus().toString() + ") " +
                        "[" + (getAvgMovieRating(movie) == 0.0 ? "No rating" : getAvgMovieRating(movie)) + "]");

            }


        printMenu(index + 1 + ". Go back", "");

        int choice = readChoice(1, index + 1);

        if (choice == index + 1) start();
        else {
            Movie movie = listOfMovie.get(choice - 1);
            if (movie.getMovieStatus().equals(MovieEnums.MovieStatus.END_OF_SHOWING)) {
                movie = listOfMovie.get(choice);
            }
            movieDetailView(movie);
        }

    }

    private void movieDetailView(Movie movie){
        printHeader("Movie details");
        printMenu(movie.toString(),
                "1. Display showtime",
                "2. Display/write reviews",
                "3. Go back", "");

        int choice = readChoice(1, 3);
        switch (choice) {
            case 1:
                //direct(this, new ShowtimeView(movie));
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

