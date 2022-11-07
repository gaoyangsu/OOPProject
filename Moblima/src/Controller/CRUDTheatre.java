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

/**
 A class of CRUD operations on the Theatre.dat file
 Calls RWController to read and write data into .dat file
 @version 1.0
 @since 2022-10-25
 */
public class CRUDTheatre {
     /**
     * {@code String } denoting the location of the Theatres.dat file
     */
    private static final String THEATRES_FILE = "Data/Theatres.dat";
    /**
     * {@code HashMap<TheatreEnums.Cineplex, ArrayList<Theatre>> } denoting the list of {@code Theatres} classes 
     * of a Cineplex to be retrieved 
     * while the Theatres.dat is being read
     */
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

   
    /** 
     * This method calls the RWController, to read the file, and put them into {@code listOfTheatres} Hashmap.
     * THIS READ FUNCTION HAS TO BE USED EVERYTIME THE PROGRAMME STARTS
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void readTheatreList() throws IOException, ClassNotFoundException {
        if (serialisedRead(THEATRES_FILE) == null) listOfTheatres = new HashMap<>();
        else listOfTheatres = (HashMap<TheatreEnums.Cineplex, ArrayList<Theatre>>) serialisedRead(THEATRES_FILE);
    }

    
    /** 
     * This method updates/overwrites the Theatres.dat file.
     * @throws IOException
     */
    public static void updateTheatreList() throws IOException {
        serialisedWrite(THEATRES_FILE, listOfTheatres);
    }

    
    /** 
     * This method gets the Hashmap previous being created in
     * the readTheatreList() function
     * @param cineplex to be inputted to get the list of theatres 
     * pertaining to the Cineplex
     * @return ArrayList<Theatre>
     */
    //AFTER READING FROM FILE, THE PROGRAMME CAN THEN RETRIEVE THE HASHMAP DATA
    public static ArrayList<Theatre> retrieveTheatreList(TheatreEnums.Cineplex cineplex) {
        return listOfTheatres.get(cineplex);
    }

    
    /** 
     * This method adds a {@code Theatre} class into the {@code listOfTheatres} Hashmap.
     * @param theatre
     * @throws IOException
     */
    public static void addTheatreIntoList(Theatre theatre) throws IOException {
        if (listOfTheatres.get(theatre.getCineplex()) == null) listOfTheatres.put(theatre.getCineplex(), new ArrayList<>());
        listOfTheatres.get(theatre.getCineplex()).add(theatre);
        updateTheatreList();

    }

    
    /** 
     * This method removes a Theatre class from the Hashmap
     * @param theatre
     * @throws IOException
     */
    public static void removeTheatreFromList(Theatre theatre) throws IOException{
        listOfTheatres.get(theatre.getCineplex()).remove(theatre);
        updateTheatreList();
    }

}
