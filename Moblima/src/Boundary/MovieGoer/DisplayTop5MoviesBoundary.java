package Boundary.MovieGoer;

import Boundary.Boundary;
import Boundary.SupportFunctions;
import Entity.Movie;
import Entity.MovieEnums;

import static Controller.CRUDMovies.*;
import static Controller.MiscMethods.*;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

import static Controller.MiscMethods.*;

public class DisplayTop5MoviesBoundary extends Boundary {
    protected void start() {
        display();
    }

    private void display() {
        SupportFunctions.clearScreen();
        printHeader("TOP 5 MOVIES BY SALES AND RATINGS!");
        FindTop5ByTicketSalesAndRatings();
    }

    private void FindTop5ByTicketSalesAndRatings(){
        ArrayList<Movie> listOfMovies = retrieveMovieList();;
        ArrayList<Movie> SortBySales = new ArrayList<>();
        ArrayList<Movie> SortByRating = new ArrayList<>();
        for(int i=0; i<listOfMovies.size();i++){
            SortBySales.add(listOfMovies.get(i));
            SortByRating.add(listOfMovies.get(i));
        }

        Collections.sort(SortBySales,((o1, o2) -> Integer.compare(o2.getSalesNum(),o1.getSalesNum())));
        while (SortBySales.size()>5) SortBySales.remove(5);


        Collections.sort(SortByRating,((o3, o4) -> Double.compare(getAvgMovieRating(o4),getAvgMovieRating(o3))));
        while (SortByRating.size()>5) SortByRating.remove(5);


        System.out.println("\nTop 5 Movies By Sales!!!: " + generateSpaces(47 - 26) + "Top 5 Movies By Ratings!!!: ");
        int count=0;
        if(listOfMovies.isEmpty()){
            System.out.println("\nSorry, There are no movies screening :(" + generateSpaces(47 - 26) + "Sorry, There are no movies screening :(");
        }
        else{
            for(int i=0; i<SortBySales.size();i++){
                String sales = ++count + ". " + SortBySales.get(i).getMovieName() + " [" + SortBySales.get(i).getSalesNum() + " Tickets Sold]";
                String rating = (count+SortBySales.size()) + ". " + SortByRating.get(i).getMovieName() + 
                " [" + (getAvgMovieRating(SortByRating.get(i)) == 0.0 ? "NA" : getAvgMovieRating(SortByRating.get(i))) + "]" + "\n";
                
                System.out.printf( sales + generateSpaces(47 - sales.length()) + rating);
            }
        }
        printMenu("\n" + (SortBySales.size() + SortByRating.size() + 1) + ". Go back", "");

        int choice = readChoice(1, (SortBySales.size() + SortByRating.size() + 1));

        if (choice == (SortBySales.size() + SortByRating.size() + 1)) end();
        else {
            if(choice <= SortBySales.size()){
                Movie movie = SortBySales.get(choice - 1);
                direct(this, new DisplayMovieDetailsBoundary(movie));
            }
            else{
                Movie movie = SortByRating.get(choice - SortBySales.size() - 1);
                direct(this, new DisplayMovieDetailsBoundary(movie));
            }
        }
    }


}
