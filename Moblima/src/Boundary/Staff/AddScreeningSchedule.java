package Boundary.Staff;
import Boundary.Boundary;
import Boundary.MovieGoer.DisplayMovieListBoundary;
import Entity.*;
import static Controller.CRUDMovies.*;
import static Controller.CRUDTheatre.*;
import static Controller.CRUDShowSchedule.*;
import static Controller.MiscMethods.*;
import static Boundary.SupportFunctions.*;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class AddScreeningSchedule extends Boundary {

    Movie movie;
    public static final SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    public AddScreeningSchedule(Movie movie){
        this.movie= movie;
    }
    @Override
    protected void start() {
        display();
    }

    private void display(){
        ArrayList<ShowSchedule> scheduleForMovie =retrieveMovieShowSchedule(movie);
        printHeader("Show Scheduling for "+movie.getMovieName());

        if (scheduleForMovie == null) {
            System.out.println("No schedule for "+this.movie.getMovieName()+" Found");
            System.out.println("1. Add scheduling for "+this.movie.getMovieName());
            System.out.println("2. Return ");
            int choice = readChoice(1, 2);
            if (choice ==1) addScheduling(movie);
            else if (choice == 2) end();
        }

        else {
            for (ShowSchedule showSchedule: scheduleForMovie){
                System.out.println(showSchedule.getMovie().getMovieName()+"      "+ showSchedule.getTheatre().getCineplex()
                        +"      "+showSchedule.getTheatre().getCode()+"      "+"ShowTime: "+dateOutput(showSchedule.getTime()));
                System.out.println();

            }
            System.out.println("1. Add scheduling for "+this.movie.getMovieName());
            System.out.println("2. Return ");
            int choice = readChoice(1, 2);
            if (choice ==1) addScheduling(movie);
            else if (choice == 2) end();
        }
    }

    public void addScheduling(Movie movie){

        Date toAppendDate=null;
        Scanner sc= new Scanner (System.in);


        Theatre theatreToUse;

        printHeader("Show Scheduler");

        System.out.println("Enter your Theatre Code(EG. GJ1): ");
        String code= sc.nextLine();
        theatreToUse= getTheatreThroughCode(code);

        System.out.println("Input show time and theatre location for :" + movie.getMovieName());

        toAppendDate=dateInput();

        System.out.println(dateOutput(toAppendDate));
        System.out.println();

        ShowSchedule toAppend= new ShowSchedule();
        toAppend.setTime(toAppendDate);
        toAppend.setMovie(movie);
        toAppend.setTheatre(theatreToUse);

        try {
            addMovieShowSchedule(toAppend);
            System.out.println("Successfully added showtime and location for " + movie.getMovieName());
        }
        catch (IOException ex) {
            System.out.println("Failed to add Showtime and location for "+ movie.getMovieName());
        }
        finally {
            display();
        }


    }

    public Theatre getTheatreThroughCode(String code){
        for (TheatreEnums.Cineplex cineplex :TheatreEnums.Cineplex.values()){
            if(retrieveTheatreList(cineplex)==null) continue;
            for (Theatre theatre : retrieveTheatreList(cineplex)) {
                if (theatre.getCode().equals(code)) return theatre;
            }
        }
        return null;  // not found
    }




}

