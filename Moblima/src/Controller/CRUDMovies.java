package Controller;
import Entity.Movie;
import Entity.Review;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

import static Controller.RWController.*;
import static Controller.CRUDShowSchedule.*;
import static Controller.CRUDTheatre.*;


public class CRUDMovies {

    private static final String MOVIE_FILE="Data/Movies.dat";
    private static final String REVIEW_FILE="Data/Reviews.dat";
    private static ArrayList<Movie> listofMovie;
    private static HashMap<Movie,ArrayList<Review>> listOfMovieReviews;

    public static boolean initialize() {
        try {
            readMovieList();
            readMovieShowSchedule();
            readReviewList();
            readTheatreList();
        } catch (IOException ex) {
            ex.printStackTrace();

            return false;
        } catch (ClassNotFoundException ex) {
            return true;
        }

        return true;
    }

    public static void readMovieList() throws IOException,ClassNotFoundException{
        if(serialisedRead(MOVIE_FILE)==null) listofMovie = new ArrayList<>();
        else {
            listofMovie = (ArrayList<Movie>) serialisedRead(MOVIE_FILE);
            Collections.sort(listofMovie, Comparator.comparing(o -> o.getMovieStatus().toString()));
        }
    }

    public static ArrayList<Movie> retrieveMovieList() {
        return listofMovie;
    }

    public static ArrayList<Movie> retrieveTopFiveMovie(){
        ArrayList<Movie> topFive=new ArrayList<>();
        boolean wayofOrder = true;
        if(wayofOrder){
            Collections.sort(topFive,((o1, o2) -> Integer.compare(o2.getSalesNum(),o1.getSalesNum())));
        }
        while (topFive.size()>5) topFive.remove(5);
        return topFive;
    }

    public static void removeMovieFromList(Movie movie) throws IOException{
        Iterator<Movie> itr = listofMovie.iterator();
        while(itr.hasNext()){
            Movie name = itr.next();
            if(name.getMovieName() == movie.getMovieName()){
                itr.remove();
            }
        }
        updateMovieList();
    }

    public static void addMovieIntoList(Movie movie) throws IOException{
        listofMovie.add(movie);
        updateMovieList();
    }

    public static void updateMovieList() throws IOException{
        serialisedWrite(MOVIE_FILE,listofMovie);
    }

    public static void overWriteMovieListNewList(ArrayList<Movie> newList) throws IOException{
        serialisedWrite(MOVIE_FILE,newList);
    }

    ////BELOW IS REVIEW PART/////
    public static void readReviewList() throws IOException, ClassNotFoundException{
        if (serialisedRead(REVIEW_FILE) == null) listOfMovieReviews = new HashMap<>();
        else listOfMovieReviews = (HashMap<Movie, ArrayList<Review>>) serialisedRead(REVIEW_FILE);
    }

    public static void updateReviewList() throws IOException {
        serialisedWrite(REVIEW_FILE, listOfMovieReviews);
    }

    public static ArrayList<Review> retrieveReviewList(Movie movie) {
        return listOfMovieReviews.get(movie);
    }

    public static void addReviewIntoList(Movie movie, Review review) throws IOException {
        if(listOfMovieReviews.get(movie) == null) listOfMovieReviews.put(movie, new ArrayList<>());
        listOfMovieReviews.get(movie).add(review);
        updateReviewList();
    }

    public static double getAvgMovieRating(Movie movie) {
        ArrayList<Review> reviewList = retrieveReviewList(movie);
        if (reviewList == null || reviewList.isEmpty()) return 0;
        else {
            double sum = 0;
            for (Review review : reviewList) sum += review.getRating();
            return round((sum / reviewList.size()), 1);
        }
    }

    private static double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

//    public static void addMovie(Movie movie){
//
//        ArrayList<Movie> Movies = null;
//        Movies = getAllMovieData();
//        Movies.add(movie);
//        SaveToAllMovieData(Movies);
//        PrintAllMovieData();
//
//        return;
//    }
//
//
//
//    public static void removeMovie(Movie movie){
//        ArrayList<Movie> Movies = null;
//        Movies = getAllMovieData();
//
//        Iterator<Movie> itr = Movies.iterator();
//        while(itr.hasNext()){
//            Movie name = itr.next();
//            if(name.getMovieID() == movie.getMovieID()){
//                itr.remove();
//            }
//        }
//        SaveToAllMovieData(Movies);
//        PrintAllMovieData();
//
//        return;
//    }
//
//
//
//
//
//
//
//
//
//
//    public static ArrayList<Movie> getAllMovieData(){
//        ArrayList<Movie> Movies = null;
//        try{
//            //deserialize the Movies.ser file
//            FileInputStream file = new FileInputStream("Data\\Movies.ser");
//            ObjectInputStream in = new ObjectInputStream(file);
//            Movies = (ArrayList<Movie>)in.readObject();
//            in.close();
//            file.close();
//        }
//        catch (IOException i) {
//            i.printStackTrace();
//            return Movies;
//        }
//        catch (ClassNotFoundException c) {
//            System.out.println("Movies class not found");
//            c.printStackTrace();
//            return Movies;
//        }
//
//        return Movies;
//    }
//
//    public static void SaveToAllMovieData(ArrayList<Movie> Movies){
//        try{
//            //serialize the Movies.ser file
//            FileOutputStream file2 = new FileOutputStream("Data\\Movies.ser");
//            ObjectOutputStream out = new ObjectOutputStream(file2);
//            out.writeObject(Movies);
//            out.close();
//            file2.close();
//        }
//        catch (IOException i) {
//            i.printStackTrace();
//            return;
//        }
//        return;
//    }
//
//
//    public static void PrintAllMovieData(){
//        ArrayList<Movie> Movies = null;
//        Movies = getAllMovieData();
//        for(Movie z: Movies){
//            System.out.println(z.getMovieID());
//            System.out.println(z.getMovieName());
//            System.out.println(z.getMovieDesc());
//        }
//
//        return;
//    }
//
//    public static void PrintAllMovieNames(){
//        ArrayList<Movie> Movies = null;
//        int count = 1;
//        Movies = getAllMovieData();
//        for(Movie z: Movies){
//            System.out.println(count + ". " + z.getMovieName());
//            count++;
//        }
//
//        return;
//    }

}
