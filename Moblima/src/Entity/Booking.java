package Entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Booking implements Serializable{
	private String transactionId;
	private String cinemaCode;
	private float price;
	private String movieName;
	private Date transactionDate;
	private Date showTime;

	public Booking(Date showTime, String cinemaCode, String movieName,float price) {
		setTransactionId(showTime, cinemaCode);
		this.showTime = showTime;
		this.movieName = movieName;
                this.price = price;
		transactionDate = new Date();
	} 
	
	public String getCinemaCode() {
		return cinemaCode;
	}
	
	public String getTransactionId() {
		return transactionId;
	}

	public float getPrice() {
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

	public void setTransactionId(Date dateTime, String cinemaCode) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("YYYYMMDDHHMM");
		this.transactionId = cinemaCode + dateFormatter.format(dateTime);
	}

	public void setTotalPayment(float totalPayment) {
		this.price = price;
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
