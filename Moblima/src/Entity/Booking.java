package Entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Booking implements Serializable{
	
	private static final long serialVersionUID=1L;
	
	private String customerName;
	private String customerEmail;
	private int customerNumber;
	
	private String transactionId;
	private ShowSchedule showtime;
	private double price;
	private Date transactionDate;
	private ArrayList<Seat> bookedSeats;
	

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
	
	public String getCustomerName() {
		return customerName;
	}
	
	public String getCustomerEmail() {
		return customerEmail;
	}
	
	public int getCustomerNumber() {
		return customerNumber;
	}
	
	public Date getTransactionDate() {
		return transactionDate;
	}
	
	public String transactionDatetoString() {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("YYYYMMDD");
		return dateFormatter.format(transactionDate);
	}
	
	public String getTransactionId() {
		return transactionId;
	}

	public double getPrice() {
		return price;
	}

	public Date getShowTime() {
		return showtime.getTime();
	}
	
	public ArrayList<Seat> getSeat() {
		return bookedSeats;
	}
	
	public void setCustomerName(String name) {
		this.customerName=name;
	}
	
	public void setCustomerEmail(String email) {
		this.customerEmail=email;
	}
	
	public void setCustomerNumber(int number) {
		this.customerNumber=number;
	}
	
	public void setTransactionDate(Date date) {
		this.transactionDate=date;
	}

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

	public void setTotalPayment(double totalPayment) {
		this.price = totalPayment;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booking that)) return false;
        return Objects.equals(getCustomerName(), that.getCustomerName()) && getCustomerEmail().equals(that.getCustomerEmail()) && getCustomerNumber()==that.getCustomerNumber();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomerName(), getCustomerEmail(), getCustomerNumber());
    }
}
