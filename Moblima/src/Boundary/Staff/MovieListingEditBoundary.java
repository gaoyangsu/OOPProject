package Boundary.Staff;
import Boundary.Boundary;
import Boundary.MovieGoer.DisplayMovieListBoundary;
import Entity.*;
import static Controller.CRUDMovies.*;
import static Controller.CRUDShowSchedule.addMovieShowSchedule;
import static Controller.MiscMethods.*;
import static Boundary.SupportFunctions.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class MovieListingEditBoundary extends Boundary {
    @Override
    protected void start() {
        display();
    }

    void display(){
        Date today= new Date();
        int index=0;
        ArrayList<Movie> listOfMovie;

        listOfMovie=retrieveMovieList();

        if(listOfMovie.isEmpty()){
            printMenu("No movie found in Database",
                    "1. List New Movie",
                    "2. Return");
            int choice = readChoice(1, 2);
            if (choice == 1) addNewMovie();
            else end();

        }

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


        for (Movie movie :listOfMovie) {
            System.out.println(++index + ". " + movie.getMovieName() + generateSpaces(70 - movie.getMovieName().length())+ "(" + movie.getMovieStatus().toString() + ")"
                    +" "+"Release Date: "+movie.releaseDateToString()+" "+"Take Down Date: "+movie.takeDownDateToString());
        }
        System.out.println(index + 1 + ". Go back");

        printMenu("Please choose a movie to modify/ add a screening Schedule based on the Movie.",
                "To list a new movie, enter 0:");

        int choice = readChoice(0, index + 1);

        if (choice == index + 1) end();
        else if (choice == 0) {
            addNewMovie();
        }
        else modifyIndividualMovie(listOfMovie.get(choice - 1));

    }

    public void addNewMovie() {
        int ID;


        Scanner sc= new Scanner(System.in);


        String name, director, synopsis;
        MovieEnums.AgeAdvisory ageAdvisory = null;
        ArrayList<String> cast;
        MovieEnums.MovieStatus movieStatus = null;

        printHeader("Add movie listing");
        System.out.println("Enter the movie ID");
        ID= sc.nextInt();
        name = readString("Enter the movie name:");



        // set age restriction
        while (ageAdvisory == null) {
            String input = readString("Choose the movie restriction, please enter one of the following:",
                    "G, PG, PG13, NC16, M18, R21").toUpperCase();
            ageAdvisory = readAgeAdvisory(input);
        }

        director = readString("Enter director:");
        synopsis = addLinebreaks(readString("Enter synopsis:"), 50, 14);

        // set casts
        String[] casts = readString("Enter casts, separate with comma(,), Last CAST NO NEED!").split(",");
        cast = new ArrayList<>();
        for (int i = 0; i < casts.length; i++) cast.add(casts[i]);


        // set movie movieStatus
        while(movieStatus == null) {
            movieStatus = readMovieStatus("NOW SHOWING");
            System.out.println("if the movie has not been launched, is there a Preview for the Movie? ");
            System.out.println("ENTER 1--- YES, 2--- NO");
            int statusChoice= readChoice(1,2);
            if(statusChoice==1) movieStatus = readMovieStatus("PREVIEW");
        }

        // create movie object
        Movie movie = new Movie(ID,name,synopsis,director,cast,movieStatus,ageAdvisory);
        System.out.println("Enter Release Date for "+ movie.getMovieName());
        movie.setReleaseDate();
        System.out.println("Enter Take Down Date for "+ movie.getMovieName());
        movie.setTakeDownDate();


        // write to file
        try {
            addMovieIntoList(movie);
            System.out.println("Successfully listed movie " + name);
        }
        catch (IOException ex) {
            System.out.println("Failed to list the movie.");
        }
        finally {
            display();
        }
    }

    public void modifyIndividualMovie(Movie movie){
        printHeader("Modify Movie" + movie.getMovieName());
        printMenu("1.Modify movie details",
                "2.Add ScreenTime for the movie",
                "3.Remove ScreenTime for the movie",
                "4.Return/Back");
        int choice =readChoice(1,4);
        switch (choice) {
            case 1:
                //clearScreen();
                direct(this, new ModifyMovieDetails(movie));
                //direct(this, new DisplayMovieListBoundary());
                //PrintAllMovieNames();
                //direct(this, new MovieGoerMain());
                break;
            case 2:
                //clearScreen();
                direct(this, new AddScreeningSchedule(movie));
                //direct(this, new MovieGoerMain());
                end();
                break;
            case 3:
                clearScreen();
                end();
            case 4:
                clearScreen();
                end();
                break;


        }
    }

}
