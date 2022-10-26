package Entity;

import java.util.*;

public class MovieGoer extends Person{
	
	private static final long serialVersionUID = 1L;
	
	private String movieGoerId;
	
	private List<Bookings> bookingList;
	
	public MovieGoer() {
		
	}
	
	public MovieGoer(String name,String email,int contactNumber) {
		super(name,email,contactNumber);
		this.bookingList=new ArrayList<>();
	}
	
	public String getMovieGoerId() {
		return movieGoerId;
	}
	
	public List<Bookings> getBookingHistory() {
		return bookingList;
	}
	
	public void setMovieGoerId(String movieGoerId) {
		this.movieGoerId=movieGoerId;
	}
	
	public void setBookingList(List<Bookings> bookingList) {
		this.bookingList=bookingList;
	}
	
	public void addBooking(Booking booking) {
		this.bookingList.add(booking);
	}
}
