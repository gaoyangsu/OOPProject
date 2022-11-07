package Controller;
import Entity.Booking;


import java.io.*;
import java.util.*;

import static Controller.RWController.*;

/**
 A class of CRUD operations on the Booking.dat file
 Calls RWController to read and write data into .dat file
 @version 1.0
 @since 2022-11-01
 */
public class CRUDCustomerBooking {
	 /**
     * {@code String} denoting the location of the Booking.dat file
     */
	private static final String BOOKING_FILE="Data/Booking.dat";
    /**
     * {@code ArrayList<Booking>} denoting the list of {@code Booking} classes 
     * while the Booking.dat is being read
     */
	private static ArrayList<Booking> listOfBookings;
	
	
    /** 
     * This method intialises the .dat files and place all of them 
     * in runtime during execution of the java file
     * @return boolean true to denote all the .dat files are properly read
     * @return false to denote file integrity issues
     */
    public static boolean initialise() {
        try {
            readBookingList();
        } catch (IOException ex) {
            return false;
        } catch (ClassNotFoundException ex) {
            return true;
        }

        return true;
    }
	
	
    /** 
     * This method calls the RWController, to read the file, and put them into {@code listOfBookings} ArrayList.
     * THIS READ FUNCTION HAS TO BE USED EVERYTIME THE PROGRAMME STARTS
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("unchecked")
	
	public static void readBookingList() throws IOException,ClassNotFoundException{
        if(serialisedRead(BOOKING_FILE)==null) listOfBookings = new ArrayList<Booking>();
        else {
            listOfBookings = (ArrayList<Booking>) serialisedRead(BOOKING_FILE);
        }
    }

    
    /** 
     * This method gets the {@code ArrayList<Booking>} previously being created in
     * the readBookingList() function
     * @return ArrayList<Booking>
     */
    public static ArrayList<Booking> retrieveBookingList() {
        return listOfBookings;
    }
    
    
    /** 
     * This method updates/overwrites the Booking.dat file.
     * @throws IOException
     */
    public static void updateBookingList() throws IOException{
        serialisedWrite(BOOKING_FILE,listOfBookings);
    }
}
