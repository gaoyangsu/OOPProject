package Boundary.Staff;
import Boundary.Boundary;
import Boundary.SupportFunctions;
import Boundary.MovieGoer.DisplayMovieListBoundary;
import Entity.*;
import Entity.TheatreEnums.Cineplex;

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
        SupportFunctions.clearScreen();
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
            System.out.println("2. Modify scheduling for "+this.movie.getMovieName());
            System.out.println("3. Remove scheduling for "+this.movie.getMovieName());
            System.out.println("4. Return ");
            int choice = readChoice(1, 4);
            if (choice == 1) addScheduling(movie);
            else if (choice ==2) modifyScheduling(movie);
            else if (choice == 3) removeScheduling(movie);
            else if (choice == 4) end();
        }
    }

    public void addScheduling(Movie movie){
        SupportFunctions.clearScreen();
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
            clearScreen();
            end();
        }

    }

    public void modifyScheduling(Movie movie){

        SupportFunctions.clearScreen();
        printHeader("Modifying ShowTime");
        ShowSchedule showScheduleToModify = new ShowSchedule();

        System.out.println("Which ShowSchedule would you like to modify?");

        System.out.println("Enter the Theatre Code(EG. GJ1): ");
        Scanner sc= new Scanner (System.in);
        String code= sc.nextLine().toUpperCase();
        Theatre theatre = getTheatreThroughCode(code);

        // System.out.println("Enter the movie name: ");
        // String name = sc.nextLine();

        System.out.println("Enter the movie ShowTime: ");
        Date showTime = dateInput();
        
        for (ShowSchedule showSchedule: retrieveMovieShowSchedule(movie)){
            if(showSchedule.getMovie().getMovieName().equals(movie.getMovieName())
                && showSchedule.getTheatre().getCode().equals(code) && showSchedule.getTime().equals(showTime)){
                    showScheduleToModify = showSchedule;
                }
        }

        int moreupdates=1;

        while(moreupdates==1){

        printHeader("Modifying this ShowTime");

        // System.out.println("1. Update Movie");
        System.out.println("1. Update Theatre");
        System.out.println("2. Update Show Time");
        System.out.println("3. Exit");
        System.out.print("Choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice){

        // case 1:
        // name = readString("Enter the new movie name:");
        // for (Movie allMovies:retrieveMovieList()){
        //     if(movie.getMovieName().equals(name)) movie = allMovies;
        // }
        // break;

        case 1:
        System.out.println("Enter the Theatre Code(EG. GJ1): ");
        code= sc.nextLine().toUpperCase();
        theatre = getTheatreThroughCode(code);
        break;

        case 2:
        System.out.println("Enter the new ShowTime: ");
        showTime = dateInput();
        break;

        case 3:
        moreupdates=0;
        break;

        default:
        end();

        }
    }

        ShowSchedule newShowSchedule = new ShowSchedule();
        newShowSchedule.setMovie(movie);
        newShowSchedule.setTheatre(theatre);
        newShowSchedule.setTime(showTime);

        try {
            addMovieShowSchedule(newShowSchedule);
//            updateReviews(movie, newMovie);
//            newMovie.setSalesNum(movie.getSalesNum());
            removeMovieShowSchedule(showScheduleToModify);
            System.out.println("Successfully updated ShowTime.");
        }
        catch (IOException ex) {
            System.out.println("Failed to update ShowTime.");
        }
        finally {
            clearScreen();
            end();
        }
    }

    public void removeScheduling(Movie movie){

        printMenu("Enter the cineplex: ",
        "1.GV",
        "2.Cathay",
        "3.FilmGarde");
        String selection = "";
        int choice = readChoice(1, 3);
        if (choice == 1) selection="GV";
        else if (choice ==2) selection="Cathay";
        else if (choice ==3) selection="FilmGarde";
        TheatreEnums.Cineplex cineplex = readCineplex(selection);

        System.out.println("Enter the Theatre Code(EG. GJ1): ");
        Scanner sc= new Scanner (System.in);
        String code= sc.nextLine().toUpperCase();

        System.out.println("Enter the movie name: ");
        String name = sc.nextLine();

        System.out.println("Enter the movie ShowTime: ");
        Date showTime = dateInput();
        
        for (ShowSchedule showSchedule: retrieveMovieShowSchedule(movie)){
            if(showSchedule.getMovie().getMovieName().equals(name) && showSchedule.getTheatre().getCineplex().equals(cineplex)
                && showSchedule.getTheatre().getCode().equals(code) && showSchedule.getTime().equals(showTime)){
                    try{
                        removeMovieShowSchedule(showSchedule);
                        updateMovieShowSchedule();
                        System.out.println("Removed sucessfully!");
                    } 
                    catch(IOException i){
                        System.out.println("Failed to remove the show schedule.");
                    } 
                    finally{
                        clearScreen();
                        end();
                    }
                }
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
