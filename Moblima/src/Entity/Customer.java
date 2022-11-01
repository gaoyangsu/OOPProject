package Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Customer extends Person {
	private static final long serialVersionUID = 1L;
	private String movieGoerId; // unique 6 digit identification, based on first 3 characters of email, followed by first 3 numbers of contact number
	private ArrayList<Booking> bookings;
	private String movieGoerPassword;
	public void setBookings(ArrayList<Booking> bookings) {
		this.bookings = bookings;
	}

	public String getMovieGoerPassword() {
		return this.movieGoerPassword;
	}

	public void setMovieGoerPassword(String movieGoerPassword) {
		this.movieGoerPassword = movieGoerPassword;
	}

	public Customer(String name, String email, int contact)
	{
		super(name, email, contact);
		this.bookings=new ArrayList<Booking>();
	}

	public String getMovieGoerId() {
		return movieGoerId;
	}

	public void setMovieGoerId(String movieGoerId) {
		this.movieGoerId = movieGoerId;
	}
	
	public ArrayList<Booking> getBookings() {
		return bookings;
	}
	
	public void addBookingEntries(Booking booking) {
		bookings.add(booking);
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer customer1)) return false;
        return Objects.equals(getMovieGoerId(), customer1.getMovieGoerId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMovieGoerId(),getMovieGoerPassword());
    }
}
