package Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
    Entity Class representing a Customer
	A subclass inheriting from Person super class
    @version 1.0
    @since 2022-10-31
 */
public class Customer extends Person {

	/** UID for serialiser */
	private static final long serialVersionUID = 1L;

	/**
	 *  an ID for the movieGoer 
	 * unique 6 digit identification, based on first 3 characters of email, 
	 * followed by first 3 numbers of contact number
	 * */
	private String movieGoerId; 
	/**Arraylist of bookings pertaining to the person */
	private ArrayList<Booking> bookings;
	/** log in password of the movieGoer */
	private String movieGoerPassword;
	
	/** 
	 * method to set bookings
	 * @param bookings
	 */
	public void setBookings(ArrayList<Booking> bookings) {
		this.bookings = bookings;
	}

	
	/** 
	 * method to get the password of the movieGoer
	 * @return String
	 */
	public String getMovieGoerPassword() {
		return this.movieGoerPassword;
	}

	
	/** 
	 * method to set the password of the movieGoer
	 * @param movieGoerPassword
	 */
	public void setMovieGoerPassword(String movieGoerPassword) {
		this.movieGoerPassword = movieGoerPassword;
	}

	/**
	 * Customer Constructor with all 3 params inherited from the superclass
	 * @param name
	 * @param email
	 * @param contact
	 */
	public Customer(String name, String email, int contact)
	{
		super(name, email, contact);
		this.bookings=new ArrayList<Booking>();
	}

	
	/** 
	 * method to get movie Goer ID
	 * @return String
	 */
	public String getMovieGoerId() {
		return movieGoerId;
	}

	
	/** 
	 * method to set movie Goer ID
	 * @param movieGoerId
	 */
	public void setMovieGoerId(String movieGoerId) {
		this.movieGoerId = movieGoerId;
	}
	
	
	/** 
	 * method to get ArrayList of bookings
	 * @return ArrayList<Booking>
	 */
	public ArrayList<Booking> getBookings() {
		return bookings;
	}
	
	
	/** 
	 * method to add booking entries into the booking ArrayList
	 * @param booking
	 */
	public void addBookingEntries(Booking booking) {
		bookings.add(booking);
	}
	
	
	/** 
	 * equals method to locate the particular Booking object
	 * @param o
	 * @return boolean
	 */
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer customer1)) return false;
        return Objects.equals(getMovieGoerId(), customer1.getMovieGoerId());
    }

    
	/** 
	 * hash the booking Object based on id and password
	 * @return int
	 */
	@Override
    public int hashCode() {
        return Objects.hash(getMovieGoerId(),getMovieGoerPassword());
    }
}
