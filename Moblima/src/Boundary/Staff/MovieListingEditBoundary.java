package Boundary.Staff;
import Boundary.Boundary;
import Boundary.MovieGoer.DisplayMovieListBoundary;
import Entity.*;
import static Controller.CRUDMovies.*;
import static Controller.MiscMethods.*;
import static Boundary.SupportFunctions.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieListingEditBoundary extends Boundary {
    @Override
    protected void start() {
        display();
    }
    int index=0;
    void display(){
        ArrayList<Movie> listOfMovie;
        listOfMovie=retrieveMovieList();

        if(listOfMovie.isEmpty()){
            printMenu("No movie found in Database",
                    "1.List New Movie",
                    "2. Return");
            int choice = readChoice(1, 2);
            if (choice == 1) addNewMovie();
            else end();

        }
        for (Movie movie :listOfMovie) {
            System.out.println(++index + ". " + movie.getMovieName() + generateSpaces(70 - movie.getMovieName().length())+ "(" + movie.getMovieStatus().toString() + ")");
        }
        System.out.println(index + 1 + ". Go back");

        printMenu("Please choose a movie to modify/ add a screening Schedule based on the Movie.",
                "To list a new movie, enter 0:");

        int choice = readChoice(0, index + 1);

        if (choice == index + 1) end();
        if (choice == 0) {
            addNewMovie();
        }
        modifyIndividualMovie(listOfMovie.get(choice - 1));

    }

    private void addNewMovie() {
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
        String[] casts = readString("Enter casts, separate with semicolon(;)").split(";");
        cast = new ArrayList<>();
        for (int i = 0; i < casts.length; i++) cast.add(casts[i]);

        // set movie movieStatus
        while(movieStatus == null) {
            String input = readString("Enter movie movieStatus, please enter one of the following:",
                    "Coming soon, Now showing, Preview").toUpperCase();
            movieStatus = readMovieStatus(input);
        }

        // create movie object
        Movie movie = new Movie(ID,name,synopsis,director,cast,movieStatus,ageAdvisory);


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

    private void modifyIndividualMovie(Movie movie){
        printHeader("Modify Movie" + movie.getMovieName());
        printMenu("1.Modify the details of movie",
                "2.Add ScreenTime for the movie",
                "3.Remove ScreenTime for the movie",
                "4.Return/Back");
        int choice =readChoice(1,4);
        switch (choice) {
            case 1:
                clearScreen();
                //direct(this, new DisplayMovieListBoundary());
                //PrintAllMovieNames();
                //direct(this, new MovieGoerMain());
                break;
            case 2:
                clearScreen();
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
