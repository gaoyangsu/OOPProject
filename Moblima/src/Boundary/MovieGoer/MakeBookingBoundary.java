package Boundary.MovieGoer;

import Boundary.Boundary;
import Boundary.SupportFunctions;
import Entity.Movie;
import Entity.MovieEnums;
import Entity.ShowSchedule;

import static Controller.CRUDMovies.*;
import static Controller.CRUDShowSchedule.retrieveMovieShowSchedule;
import static Controller.MiscMethods.*;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;

import static Controller.MiscMethods.*;

public class MakeBookingBoundary extends Boundary{
	protected void start() {
        display();
    }
	
	private void display() {
        SupportFunctions.clearScreen();
        printHeader("Make a booking");
        
        ArrayList<Movie> listOfMovie=getListOfCurrentlyShowingMovies();
        
        int index=0;
        
        for (Movie movie : listOfMovie) {
          printMenu(++index + ". " + movie.getMovieName() + generateSpaces(47 - movie.getMovieName().length())
                  + "(" + movie.getMovieStatus().toString() + ") "
                  + "[" + movie.getAgeAdvisory().toString()+ "]");
        }
        
        printMenu("Please select your movie:");
      
        int choice = readChoice(1, index + 1);
        
    	Movie movie = listOfMovie.get(choice - 1);
    	
    	direct(this,new DisplayShowTimeBoundary(movie));
    	
        end();
    }
	
	private ArrayList<Movie> getListOfCurrentlyShowingMovies() {
		ArrayList<Movie> listOfMovies=retrieveMovieList();
		ArrayList<Movie> result=new ArrayList<Movie>();
		
		for (Movie m:listOfMovies) {
			if (m.getMovieStatus().toString()=="PREVIEW" || m.getMovieStatus().toString()=="NOW SHOWING") {
				result.add(m);
			}
		}
		
		return result;
	}
}
