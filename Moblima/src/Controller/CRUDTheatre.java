package Controller;
import Entity.Movie;
import Entity.Review;
import Entity.Theatre;
import Entity.TheatreEnums;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

import static Controller.RWController.*;

public class CRUDTheatre {
    private static final String THEATRES_FILE = "Data/Theatres.dat";
    private static HashMap<TheatreEnums.Cineplex, ArrayList<Theatre>> listOfTheatres;

//    public static boolean CRUDTheatresInitialise() {
//        try {
//            readTheatreList();
//        } catch (IOException ex) {
//            return false;
//        } catch (ClassNotFoundException ex) {
//            return true;
//        }
//
//        return true;
//    }

    //THIS READ FUNCTION HAS TO BE USED EVERYTIME THE PROGRAMME STARTS
    public static void readTheatreList() throws IOException, ClassNotFoundException {
        if (serialisedRead(THEATRES_FILE) == null) listOfTheatres = new HashMap<>();
        else listOfTheatres = (HashMap<TheatreEnums.Cineplex, ArrayList<Theatre>>) serialisedRead(THEATRES_FILE);
    }

    public static void updateTheatreList() throws IOException {
        serialisedWrite(THEATRES_FILE, listOfTheatres);
    }

    //AFTER READING FROM FILE, THE PROGRAMME CAN THEN RETRIEVE THE HASHMAP DATA
    public static ArrayList<Theatre> retrieveTheatreList(TheatreEnums.Cineplex cineplex) {
        return listOfTheatres.get(cineplex);
    }

    public static void addTheatreIntoList(Theatre theatre) throws IOException {
        if (listOfTheatres.get(theatre.getCineplex()) == null) listOfTheatres.put(theatre.getCineplex(), new ArrayList<>());
        listOfTheatres.get(theatre.getCineplex()).add(theatre);
        updateTheatreList();

    }

    public static void removeTheatreFromList(Theatre theatre) throws IOException{
        listOfTheatres.get(theatre.getCineplex()).remove(theatre);
        updateTheatreList();
    }

}
