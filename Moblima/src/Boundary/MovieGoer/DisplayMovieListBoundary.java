package Boundary.MovieGoer;

import Boundary.Boundary;
import Boundary.SupportFunctions;
import Entity.Movie;
import Entity.MovieEnums;

import static Controller.CRUDMovies.*;
import static Controller.MiscMethods.*;


import java.util.ArrayList;
import java.util.Date;

import static Controller.MiscMethods.*;

public class DisplayMovieListBoundary extends Boundary {

    private boolean isTopFive =false;
    protected void start() {
        display();
    }

    private void display() {
        
        SupportFunctions.clearScreen();
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
                end();
                break;
            case 4:
                end();
                break;
        }

        end();
    }


    private void movieListingView() {

        Date today = new Date();
        SupportFunctions.clearScreen();
        ArrayList<Movie> listOfMovie;

        if (isTopFive) listOfMovie = retrieveTopFiveMovie();
        else listOfMovie = retrieveMovieList();
        printHeader("Movies");
        if (listOfMovie.isEmpty()) {
            printMenu("Movie listing is not available");
            movieListingView();
            return;
        }

        int index = 0;



        //TO FILTER OUT THE END_OF_SHOWING MOVIES, AS WELL AS TO SET TO COMING SOON and NOW SHOWING
        for (Movie movie:listOfMovie){
            if(today.after(movie.getTakeDownDate())) {movie.setMovieStatus(readMovieStatus("END OF SHOWING")); }
            else if (today.before(movie.getReleaseDate())) {
                if (movie.getMovieStatus().toString()=="PREVIEW") {
                    movie.setMovieStatus(readMovieStatus("PREVIEW"));
                }
                else{
                    movie.setMovieStatus(readMovieStatus("COMING SOON"));
                }
            }
            else {
                movie.setMovieStatus(readMovieStatus("NOW SHOWING"));
            }
        }

        //DISPLAY THE ENTIRE LIST OF THE MOVIE
            for (Movie movie : listOfMovie) {
                if (movie.getMovieStatus().equals(MovieEnums.MovieStatus.END_OF_SHOWING)) {
                    ++index;
                    continue;}
                printMenu(++index + ". " + movie.getMovieName() + generateSpaces(47 - movie.getMovieName().length())
                        + "(" + movie.getMovieStatus().toString() + ") " +
                        "[" + (getAvgMovieRating(movie) == 0.0 ? "No rating" : getAvgMovieRating(movie)) + "]");

            }


        printMenu(index + 1 + ". Go back", "");

        int choice = readChoice(1, index + 1);

        if (choice == index + 1) start();
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

