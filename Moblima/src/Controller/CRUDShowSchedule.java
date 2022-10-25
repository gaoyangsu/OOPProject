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


public class CRUDShowSchedule {

    private static final String SHOWSCHEDULE_FILE="Data/ShowSchedule.dat";
    private static HashMap<Movie, ArrayList<ShowSchedule>> movieShowScheduleList;


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

    @SuppressWarnings("unchecked")

    public static void readMovieShowSchedule() throws IOException, ClassNotFoundException {
        if (serialisedRead(SHOWSCHEDULE_FILE) == null) movieShowScheduleList = new HashMap<>();
        else movieShowScheduleList = (HashMap<Movie, ArrayList<ShowSchedule>>) serialisedRead(SHOWSCHEDULE_FILE);
    }

    public static void updateMovieShowSchedule() throws IOException {
        serialisedWrite(SHOWSCHEDULE_FILE, movieShowScheduleList);
    }

    public static ArrayList<ShowSchedule> retrieveMovieShowSchedule(Movie movie) {
        return movieShowScheduleList.get(movie);
    }

    public static void addMovieShowSchedule(ShowSchedule showSchedule) throws IOException {
        Movie movie = showSchedule.getMovie();
        if (movieShowScheduleList.get(movie) == null) movieShowScheduleList.put(movie, new ArrayList<>());
        movieShowScheduleList.get(movie).add(showSchedule);
        updateMovieShowSchedule();
    }

    public static void removeMovieShowSchedule(ShowSchedule showSchedule) throws IOException {
        movieShowScheduleList.get(showSchedule.getMovie()).remove(showSchedule);
        updateMovieShowSchedule();
    }





}
