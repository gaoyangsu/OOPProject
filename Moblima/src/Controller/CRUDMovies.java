package Controller;
import Entity.Admin;
import Entity.Movie;
import Entity.Review;
import Entity.SystemSettings;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

import static Controller.RWController.*;
import static Controller.CRUDShowSchedule.*;
import static Controller.CRUDTheatre.*;
import static Controller.AdminController.*;
import static Controller.CustomerController.*;
/**
 A class of CRUD operations on the Movies.dat & Reviews.dat file
 Calls RWController to read and write data into .dat file
 @version 1.0
 @since 2022-10-25
 */
public class CRUDMovies implements Initialiser{
    /**
     * {@code String } denoting the location of the Movies.dat file
     */
    private static final String MOVIE_FILE="Data/Movies.dat";
    /**
     * {@code String } denoting the location of the Reviews.dat file
     */
    private static final String REVIEW_FILE="Data/Reviews.dat";
    /**
     * {@code ArrayList<Movie> } denoting the list of {@code Movie} classes 
     * to be shown in listofMovie
     * while the Movies.dat is being read
     */
    private static ArrayList<Movie> listofMovie;
    /**
     * {HashMap<Movie,ArrayList<Review>> } denoting the list of {@code Review} classes 
     * pertaining an individual movie to be shown in {@code listOfMovieReviews}
     * while the Reviews.dat is being read
     */
    private static HashMap<Movie,ArrayList<Review>> listOfMovieReviews;
    /** 
     * Method to initialize the .dat files and place all of them 
     * in runtime during execution of the java file
     * @return boolean yes to denote all the .dat files are properly read
     * @return boolean no to denote file integrity issues
     */
    @Override
    public boolean initialise() {
        try {
            readMovieList();
            // readMovieShowSchedule();
            readReviewList();
            // readTheatreList();
            // readAdminList();
            // readHolidays();
            // readSystemSettings();
            // readUserList();
            // // Admin one = new Admin("admin","default@default.com",81234567);
            // // Admin two = new Admin("moe","default@default.com",65234567);
            // // one.setAdminId("default");
            // // two.setAdminId("kingsmil");
            // // one.setAdminPassword("password");
            // // two.setAdminPassword("123456");
            // // addAdminIntoList(one);
            // // addAdminIntoList(two);
            // ///SystemSettings one = new SystemSettings(2.50, 1.50, 4.50, 3, 8.50, 2.50, 1, 1);
            // ///retrieveSystemSettings().
            // ////updateSystemSettings();
            
        } catch (IOException ex) {
            ex.printStackTrace();

            return false;
        } catch (ClassNotFoundException ex) {
            return true;
        }

        return true;
    }

    
    /** 
     * This method calls the RWController, to read the file, and put them into {@code listOfMovie} Arraylist.
     * THIS READ FUNCTION HAS TO BE USED EVERYTIME THE PROGRAMME STARTS
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void readMovieList() throws IOException,ClassNotFoundException{
        if(serialisedRead(MOVIE_FILE)==null) listofMovie = new ArrayList<>();
        else {
            listofMovie = (ArrayList<Movie>) serialisedRead(MOVIE_FILE);
            Collections.sort(listofMovie, Comparator.comparing(o -> o.getMovieStatus().toString()));
        }
    }

    
    /** This method gets the Hashmap previous being created in
     * the readTheatreList() function
     * @return  ArrayList<Movie>
     */
    public static ArrayList<Movie> retrieveMovieList() {
        return listofMovie;
    }

    
    /** 
     * This method retrieves the top5 movies based on salesnumber
     * @return ArrayList<Movie>
     */
    public static ArrayList<Movie> retrieveTopFiveMovie(){
        ArrayList<Movie> topFive=new ArrayList<>();
        boolean wayofOrder = true;
        if(wayofOrder){
            Collections.sort(topFive,((o1, o2) -> Integer.compare(o2.getSalesNum(),o1.getSalesNum())));
        }
        while (topFive.size()>5) topFive.remove(5);
        return topFive;
    }

    
    /** 
     * This method enables a movie to be removed from the current listOfMovie
     * @param movie
     * @throws IOException
     */
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

    
    /** 
     * This method adds an individual {@code Movie} class into the {@code listOfMovie}
     * calls updateMovieList()
     * @param movie the Movie to be inserted 
     * @throws IOException
     */
    public static void addMovieIntoList(Movie movie) throws IOException{
        listofMovie.add(movie);
        updateMovieList();
    }

    
    /** 
     * This method updates the current {@code listOfMovie}
     * @throws IOException
     */
    public static void updateMovieList() throws IOException{
        serialisedWrite(MOVIE_FILE,listofMovie);
    }

    
    /** 
     * This method overwrites the current {@code listOfMovie}
     * with a new list of input {@code newList} replacing as the new {@code listOfMovie}
     * @param newList
     * @throws IOException
     */
    public static void overWriteMovieListNewList(ArrayList<Movie> newList) throws IOException{
        serialisedWrite(MOVIE_FILE,newList);
    }

    
    /** 
     * This method calls the RWController, to read the file, and put them into {@code listOfMovieReviews} Hashmap.
     * THIS READ FUNCTION HAS TO BE USED EVERYTIME THE PROGRAMME STARTS
     * @throws IOException
     * @throws ClassNotFoundException
     */
    ////BELOW IS REVIEW PART/////
    public static void readReviewList() throws IOException, ClassNotFoundException{
        if (serialisedRead(REVIEW_FILE) == null) listOfMovieReviews = new HashMap<>();
        else listOfMovieReviews = (HashMap<Movie, ArrayList<Review>>) serialisedRead(REVIEW_FILE);
    }

    
    /** 
     * This method updates/overwrites the Reviews.dat file.
     * @throws IOException
     */
    public static void updateReviewList() throws IOException {
        serialisedWrite(REVIEW_FILE, listOfMovieReviews);
    }

    
    /** 
     * This method gets the Hashmap previous being created in
     * the readReviewList() function
     * @param movie to be inputted to get the list of reviews 
     * pertaining to the individual movie
     * @return ArrayList<Review>
     */
    public static ArrayList<Review> retrieveReviewList(Movie movie) {
        return listOfMovieReviews.get(movie);
    }

    
    /** 
     * This method adds a {@code review} class into the {@code listOfMovieReviews} Hashmap.
     * @param movie the Reviews pertaining to the individual movie
     * @param review the {@code Review} to be appended to the Hashmap
     * @throws IOException
     */
    public static void addReviewIntoList(Movie movie, Review review) throws IOException {
        if(listOfMovieReviews.get(movie) == null) listOfMovieReviews.put(movie, new ArrayList<>());
        listOfMovieReviews.get(movie).add(review);
        updateReviewList();
    }

    
    /** 
     * This method enables to movie to calculate average movie rating
     * based on the the {@code review.getRating} from each of the {@code Review} 
     * classes retreived from {@code retrieveReviewList(movie)}
     * @param movie the Reviews pertaining to the individual movie
     * @return double, the average score of a movie
     */
    public static double getAvgMovieRating(Movie movie) {
        ArrayList<Review> reviewList = retrieveReviewList(movie);
        if (reviewList == null || reviewList.isEmpty()) return 0;
        else {
            double sum = 0;
            for (Review review : reviewList) sum += review.getRating();
            return round((sum / reviewList.size()), 1);
        }
    }

    
    /** 
     * The method rounds the double values to a number with specified number
     * of decimal places
     * @param value, the num inputted
     * @param precision, no. of decimal places
     * @return double, the truncated num
     */
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
