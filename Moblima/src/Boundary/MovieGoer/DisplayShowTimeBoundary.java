package Boundary.MovieGoer;

import Entity.Movie;
import Boundary.Boundary;
import Boundary.SupportFunctions;
import Entity.Movie;
import Entity.MovieEnums;
import Entity.ShowSchedule;

import javax.imageio.IIOException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

import static Controller.CRUDMovies.*;
import static Controller.MiscMethods.*;
import static Controller.CRUDShowSchedule.*;
import static Controller.CRUDTheatre.*;

/**
    Boundary/View Class to display showScheduling based on a movie
    @version 1.0
    @since 2022-10-23
 */
public class DisplayShowTimeBoundary extends Boundary {
    /** Movie class passed in */
    private Movie movie;
    /** UID class to check if its customer ID or guest */
    private String userId;
    
    /**
     * Constructor to pass in movie and userId
     * @param movie
     * @param userId
     */
    public DisplayShowTimeBoundary(Movie movie,String userId){
        this.movie = movie;
        this.userId=userId;
    }

     /**
     * overriden start method from Boundary abstract class
     */
    @Override
    protected void start() {
        display();
    }

    /** display a list of schedules for a movie
     * can click into each schedule to make a booking
     */
    private void display(){

        Date today= new Date();

        printHeader("View Show Schedule of the Movie");

        ArrayList<ShowSchedule> movieShowTime=  new ArrayList<>();

        if ((retrieveMovieShowSchedule(movie) != null)&&!(retrieveMovieShowSchedule(movie).isEmpty())) {
            for (ShowSchedule showSchedule :retrieveMovieShowSchedule(movie)) {
                if (today.before(showSchedule.getTime())) movieShowTime.add(showSchedule);
            }
        }

        else {
            System.out.println("No show time for "+ movie.getMovieName());
            System.out.println("Press any Key to Return!");
            readCharacter();
            end();
        }

        movieShowTime.sort(Comparator.comparing(o -> o.getTime()));

        int index = 0;
        for (ShowSchedule showSchedule : movieShowTime) {
            System.out.println(++index+"   "+showSchedule.getMovie().getMovieName()+"      "+ showSchedule.getTheatre().getCineplex()
                    +"      "+showSchedule.getTheatre().getCode()+" "+showSchedule.getTheatre().getTheatreClass()+" "+((showSchedule.getTheatre().isIs3D()) ? "3D":"  ")+"   "+"ShowTime: "+showSchedule.getTime());
            System.out.println();
        }
        
        System.out.println("Please choose a showtime(Enter 0 to go back):");

        System.out.println();
        int choice = readChoice(0, movieShowTime.size());
        if (choice == 0) {
            end();
            return;
        }

        ShowSchedule toBookParticularSchedule = movieShowTime.get(choice - 1);
        direct(this,new DisplayShowtimeDetailMenu(toBookParticularSchedule,userId));
    }
}
