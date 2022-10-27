package Entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Booking implements Serializable{
	private String transactionId;
	private String cinemaCode;
	private float totalPayment;
	private String movieName;
	private Date transactionDate;
	private Date showTime;

	public Booking(Date showTime, String cinemaCode, String movieName,float totalPayment) {
		setTransactionId(showTime, cinemaCode);
		this.showTime = showTime;
		this.movieName = movieName;
                this.totalPayment = totalPayment;
		transactionDate = new Date();
	} 
	
	public String getTransactionId() {
		return transactionId;
	}

	public float getTotalPayment() {
		return totalPayment;
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
		this.totalPayment = totalPayment;
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
