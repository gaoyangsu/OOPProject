package Entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Booking implements Serializable{
	
	private static final long serialVersionUID=1L;
	
	private String transactionId;
	private String cinemaCode;
	private double price;
	private String movieName;
	private Date transactionDate;
	private Date showTime;
	private ArrayList<Seat> bookedSeats;
	
	public Booking() {
		
	}

	public Booking(Date showTime, String cinemaCode, String movieName,double price,ArrayList<Seat> bookedSeats) {
		this.showTime = showTime;
		this.movieName = movieName;
        this.price = price;
        this.cinemaCode=cinemaCode;
		transactionDate = new Date();
		this.bookedSeats=bookedSeats;
		setTransactionId(transactionDate,cinemaCode);
	} 
	
	public String getCinemaCode() {
		return cinemaCode;
	}
	
	public String getTransactionId() {
		return transactionId;
	}

	public double getPrice() {
		return price;
	}

	public String getMovieName() {
		return movieName;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public Date getShowTime() {
		return showTime;
	}
	
	public ArrayList<Seat> getSeat() {
		return bookedSeats;
	}

	public void setTransactionId(Date dateTime, String cinemaCode) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("YYYYMMDDHHMM");
		this.transactionId = cinemaCode + dateFormatter.format(dateTime);
	}

	public void setTotalPayment(double totalPayment) {
		this.price = totalPayment;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public void setShowTime(Date showTime) {
		this.showTime = showTime;
	}
}
