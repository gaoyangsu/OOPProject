package Controller;
import Entity.Booking;


import java.io.*;
import java.util.*;

import static Controller.RWController.*;

public class CRUDCustomerBooking {
	
	private static final String BOOKING_FILE="Data/Booking.dat";
	private static ArrayList<Booking> listOfBookings;
	
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
	
	@SuppressWarnings("unchecked")
	
	public static void readBookingList() throws IOException,ClassNotFoundException{
        if(serialisedRead(BOOKING_FILE)==null) listOfBookings = new ArrayList<Booking>();
        else {
            listOfBookings = (ArrayList<Booking>) serialisedRead(BOOKING_FILE);
        }
    }

    public static ArrayList<Booking> retrieveBookingList() {
        return listOfBookings;
    }
    
    public static void updateBookingList() throws IOException{
        serialisedWrite(BOOKING_FILE,listOfBookings);
    }
}
