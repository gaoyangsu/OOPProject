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

public class DisplayShowTimeBoundary extends Boundary {
    private Movie movie;
    private String userId;
    
    public DisplayShowTimeBoundary(Movie movie,String userId){
        this.movie = movie;
        this.userId=userId;
    }

    @Override
    protected void start() {
        display();
    }

    private void display(){

        Date today= new Date();

        printHeader("View ShowSchedule of the Movie");

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

        movieShowTime.sort(Comparator.comparing(o -> o.getTheatre().getCineplex().toString()));

        int index = 0;
        for (ShowSchedule showSchedule : movieShowTime) {
            System.out.println(++index+"   "+showSchedule.getMovie().getMovieName()+"      "+ showSchedule.getTheatre().getCineplex()
                    +"      "+showSchedule.getTheatre().getCode()+"      "+"ShowTime: "+dateOutput(showSchedule.getTime()));
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
