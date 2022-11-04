package Boundary.MovieGoer;

import Boundary.Boundary;
import Boundary.SupportFunctions;
import Entity.Movie;
import Entity.MovieEnums;

import static Controller.CRUDMovies.*;
import static Controller.MiscMethods.*;
import static Controller.AdminController.*;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

import static Controller.MiscMethods.*;

public class DisplayTop5MoviesBoundary extends Boundary {

    private Date today= new Date();
	private String userId;
	
	public DisplayTop5MoviesBoundary(String userId) {
		this.userId=userId;
	}
	
	
    protected void start() {
        display();
    }

    private void display() {
        SupportFunctions.clearScreen();
        printHeader("TOP 5 MOVIES!");
        FindTop5ByTicketSalesAndRatings();
    }

    private void FindTop5ByTicketSalesAndRatings(){
        ArrayList<Movie> listOfMovies = retrieveMovieList();;
        
        //for checking with Today's Date, by altering status of the movie
        for (Movie movie:listOfMovies){
            if(movie.getMovieStatus().toString()=="END OF SHOWING") continue;
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


        ArrayList<Movie> SortBySales = new ArrayList<>();
        ArrayList<Movie> SortByRating = new ArrayList<>();

        for (Movie movie: listOfMovies){
            if ((movie.getMovieStatus().toString()=="PREVIEW")||(movie.getMovieStatus().toString()=="NOW SHOWING")){
                SortBySales.add(movie);
                SortByRating.add(movie);
            } 
        }
        
        // for(int i=0; i<listOfMovies.size();i++){
        //     SortBySales.add(listOfMovies.get(i));
        //     SortByRating.add(listOfMovies.get(i));
        // }

        Collections.sort(SortBySales,((o1, o2) -> Integer.compare(o2.getSalesNum(),o1.getSalesNum())));
        while (SortBySales.size()>5) SortBySales.remove(5);


        Collections.sort(SortByRating,((o3, o4) -> Double.compare(getAvgMovieRating(o4),getAvgMovieRating(o3))));
        while (SortByRating.size()>5) SortByRating.remove(5);

        
        //retrieve the system Setting 0---- By Ratings 1---- By SalesNumbers
        if (retrieveSystemSettings().getTopFivechoice()==1){
            System.out.println("\nTop 5 Movies By SalesNumbers" );
            int count=0;
            if(listOfMovies.isEmpty()){
                System.out.println("\nSorry, There are no movies screening :(" );
            }
            else{
                for(int i=0; i<SortBySales.size();i++){
                    String sales = ++count + ". " + SortBySales.get(i).getMovieName() + " [" + SortBySales.get(i).getSalesNum() + " Tickets Sold]";
                    
                    System.out.printf(sales);
                    System.out.println();
                }
            }
            printMenu("\n" + (SortBySales.size() + 1) + ". Go back", "");

            int choice = readChoice(1, (SortBySales.size()  + 1));

            if (choice == (SortBySales.size()  + 1)) end();
            else {
                    Movie movie = SortBySales.get(choice - 1);
                    direct(this, new DisplayMovieDetailsBoundary(movie,userId));
            }
        }

        else if (retrieveSystemSettings().getTopFivechoice()==0){
            System.out.println("\nTop 5 Movies By MovieRating" );
            int count=0;
            if(listOfMovies.isEmpty()){
                System.out.println("\nSorry, There are no movies screening :(" );
            }
            else{
                for(int i=0; i<SortBySales.size();i++){
                    
                    String rating = ++count + ". " + SortByRating.get(i).getMovieName() + 
                    " [" + (getAvgMovieRating(SortByRating.get(i)) == 0.0 ? "NA" : getAvgMovieRating(SortByRating.get(i))) + "]" + "\n";
                    
                    System.out.printf( rating);
                }
            }
            printMenu("\n" + ( SortByRating.size() + 1) + ". Go back", "");

            int choice = readChoice(1, (SortByRating.size() + 1));

            if (choice == (SortByRating.size() + 1)) end();
            else {
                
                    Movie movie = SortByRating.get(choice - 1);
                    direct(this, new DisplayMovieDetailsBoundary(movie,userId));
                
            }
        }

        else{
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
                    direct(this, new DisplayMovieDetailsBoundary(movie,userId));
                }
                else{
                    Movie movie = SortByRating.get(choice - SortBySales.size() - 1);
                    direct(this, new DisplayMovieDetailsBoundary(movie,userId));
                }
            }
        }


        
    }
}
