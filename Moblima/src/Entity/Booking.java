package Entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
    Entity Class representing a Booking
    @version 1.0
    @since 2022-10-31
 */
public class Booking implements Serializable{
	
	private static final long serialVersionUID=1L;
	/** customer name */
	private String customerName;

	/** customer email */
	private String customerEmail;

	/** customer number */
	private int customerNumber;

	/** TID*/
	private String transactionId;

	/** showtime the booking refers to*/
	private ShowSchedule showtime;

	/** price of booking*/
	private double price;

	/** Date of Transaction*/
	private Date transactionDate;

	/** ArrayList of Seat classes */
	private ArrayList<Seat> bookedSeats;
	

	/**
	 * Constructor of a Booking Class which includes
	 * @param customerName
	 * @param customerEmail
	 * @param customerNumber
	 * @param schedule
	 * @param transactionDate
	 * @param price
	 * @param bookedSeats
	 */
	public Booking(String customerName,String customerEmail,int customerNumber,ShowSchedule schedule,Date transactionDate,double price,ArrayList<Seat> bookedSeats) {
		this.customerName=customerName;
		this.customerEmail=customerEmail;
		this.customerNumber=customerNumber;
		
		this.showtime=schedule;
		
		this.price=price;
		this.bookedSeats=bookedSeats;
		this.transactionDate=transactionDate;
		setTransactionId(transactionDate,schedule.getTheatre().getCode());
	} 
	
	
	/** 
	 * method to get customer name 
	 * @return String
	 */
	public String getCustomerName() {
		return customerName;
	}
	
	
	/** 
	 * method to get customer email
	 * @return String
	 */
	public String getCustomerEmail() {
		return customerEmail;
	}
	
	
	/** 
	 * method to get customer number
	 * @return int
	 */
	public int getCustomerNumber() {
		return customerNumber;
	}
	
	
	/** 
	 *  method to get Transaction Date
	 * @return Date
	 */
	public Date getTransactionDate() {
		return transactionDate;
	}
	
	
	/** 
	 *  method to convert transaction date to String
	 * @return String
	 */
	public String transactionDatetoString() {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("YYYYMMDD");
		return dateFormatter.format(transactionDate);
	}
	
	
	/** 
	 * method to get TID
	 * @return String
	 */
	public String getTransactionId() {
		return transactionId;
	}

	
	/** 
	 * method to get double
	 * @return double
	 */
	public double getPrice() {
		return price;
	}

	
	/** 
	 * method to get Date of showtime
	 * @return Date
	 */
	public Date getShowTime() {
		return showtime.getTime();
	}
	
	
	/** 
	 * method to get a class of all booked Seats
	 * @return ArrayList<Seat>
	 */
	public ArrayList<Seat> getSeat() {
		return bookedSeats;
	}
	
	
	/** 
	 * method to set Customer name
	 * @param name
	 */
	public void setCustomerName(String name) {
		this.customerName=name;
	}
	
	
	/** 
	 * method to set Customer email
	 * @param email
	 */
	public void setCustomerEmail(String email) {
		this.customerEmail=email;
	}
	
	
	/** 
	 * method to set Customer number
	 * @param number
	 */
	public void setCustomerNumber(int number) {
		this.customerNumber=number;
	}
	
	
	/** method to set Customer date
	 * @param date
	 */
	public void setTransactionDate(Date date) {
		this.transactionDate=date;
	}

	
	/** 
	 * method to set TID based on current Date and Cinema Code
	 * @param dateTime
	 * @param cinemaCode
	 */
	public void setTransactionId(Date dateTime, String cinemaCode) {
		Calendar cal=Calendar.getInstance();
		String Month;
		String Day;
		String Hour;
		String Minutes;
		
		int month=cal.get(Calendar.MONTH)+1;
		if (month<=9) {
			Month="0"+month;
		}
		else {
			Month=String.valueOf(month);
		}
		
		int day=cal.get(Calendar.DAY_OF_MONTH);
		if (day<=9) {
			Day="0"+day;
		}
		else {
			Day=String.valueOf(day);
		}
		
		int hour=cal.get(Calendar.HOUR_OF_DAY);
		if (hour<=9) {
			Hour="0"+hour;
		}
		else {
			Hour=String.valueOf(hour);
		}
		
		int minutes=cal.get(Calendar.MINUTE);
		if (minutes<=9) {
			Minutes="0"+minutes;
		}
		else {
			Minutes=String.valueOf(minutes);
		}
		
		this.transactionId = cinemaCode + cal.get(Calendar.YEAR) + Month+Day+Hour+Minutes;
	}

	
	/** 
	 * method to set total Payment
	 * @param totalPayment
	 */
	public void setTotalPayment(double totalPayment) {
		this.price = totalPayment;
	}
	
	
	/** 
	 * equals method to find the particular booking object
	 * @param o
	 * @return boolean
	 */
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booking that)) return false;
        return Objects.equals(getCustomerName(), that.getCustomerName()) && getCustomerEmail().equals(that.getCustomerEmail()) && getCustomerNumber()==that.getCustomerNumber();
    }

    
	/** 
	 * hashing of the Booking object based on customername, email and number
	 * @return int
	 */
	@Override
    public int hashCode() {
        return Objects.hash(getCustomerName(), getCustomerEmail(), getCustomerNumber());
    }
}
