package Controller;
import Entity.Movie;
import Entity.Review;
import Entity.ShowSchedule;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;


import static Controller.RWController.*;

/**
 A class of CRUD operations on the ShowSchedule.dat file
 Calls RWController to read and write data into .dat file
 @version 1.0
 @since 2022-11-01
 */
public class CRUDShowSchedule {
    /**
     * {@code String } denoting the location of the ShowSchedule.dat file
     */
    private static final String SHOWSCHEDULE_FILE="Data/ShowSchedule.dat";
    /**
     * {@code HashMap<Movie, ArrayList<ShowSchedule>>} denoting the list of {@code ShowSchedule} classes with reference
     * to an individual movie be retrieved while the ShowSchedule.dat is being read
     */
    private static HashMap<Movie, ArrayList<ShowSchedule>> movieShowScheduleList;
    
    
    /** 
     * calls readMovieShowSchedule() to initialise the file
     * @return boolean
     */
    public static boolean CRUDShowScheduleInitialise() {
        try {
            readMovieShowSchedule();
        } catch (IOException ex) {
            return false;
        } catch (ClassNotFoundException ex) {
            return true;
        }

        return true;
    }

    
    /** 
     * Thiss method calls the RWController, to read the file, and put them into {@code HashMap<Movie, ArrayList<ShowSchedule>>}.
     * THIS READ FUNCTION HAS TO BE USED EVERYTIME THE PROGRAMME STARTS
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("unchecked")

    public static void readMovieShowSchedule() throws IOException, ClassNotFoundException {
        if (serialisedRead(SHOWSCHEDULE_FILE) == null) movieShowScheduleList = new HashMap<>();
        else movieShowScheduleList = (HashMap<Movie, ArrayList<ShowSchedule>>) serialisedRead(SHOWSCHEDULE_FILE);
    }

    
    /** 
     * This method updates/overwrites the 
     * ShowSchedule.dat file with ShowSchedule Hashmap.
     * @throws IOException
     */
    public static void updateMovieShowSchedule() throws IOException {
        serialisedWrite(SHOWSCHEDULE_FILE, movieShowScheduleList);
    }

    
    /** 
     * This method gets the Hashmap previously being created in
     * the readMovieShowSchedule() function
     * @param movie
     * @return ArrayList<ShowSchedule>
     */
    public static ArrayList<ShowSchedule> retrieveMovieShowSchedule(Movie movie) {
        return movieShowScheduleList.get(movie);
    }

    
    /** 
     * This method adds a {@code ShowSchedule} class into the Hashmap.

     * @param showSchedule
     * @throws IOException
     */
    public static void addMovieShowSchedule(ShowSchedule showSchedule) throws IOException {
        Movie movie = showSchedule.getMovie();
        if (movieShowScheduleList.get(movie) == null) movieShowScheduleList.put(movie, new ArrayList<>());
        movieShowScheduleList.get(movie).add(showSchedule);
        updateMovieShowSchedule();
    }

    
    /** 
     * This method removes a particular showSchdule from the Hashmap
     * @param showSchedule
     * @throws IOException
     */
    public static void removeMovieShowSchedule(ShowSchedule showSchedule) throws IOException {
        movieShowScheduleList.get(showSchedule.getMovie()).remove(showSchedule);
        updateMovieShowSchedule();
    }

}
