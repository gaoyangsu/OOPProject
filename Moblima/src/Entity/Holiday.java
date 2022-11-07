package Entity;

import static Controller.MiscMethods.dateInput;

import java.io.Serializable;
import java.util.Date;

/**
    Entity Class representing a Holiday
	For booking tickets, when the showschedule date coincides with a holiday
	prices would be affected
    @version 1.0
    @since 2022-10-29
 */
public class Holiday implements Serializable {
	/** Date of the holiday */
    private Date date;
	/** name of the holiday */
	private String name;

	/**
	 * Constructor of the Holiday Class, for creating a new holiday for storage
	 * @param date
	 * @param name
	 */
	public Holiday(Date date, String name) {
		this.date = date;
		this.name = name;
	}
	
	/**
	 * method to set the date of the holiday
	 */
	public void setDate() {
		this.date=dateInput();
	}
	
	
	/** 
	 * method to set the holiday name of the holiday
	 * @param name
	 */
	public void setHolidayName(String name) {
		this.name = name;
	}
	
	
	/** 
	 * method to get the date of the holiday
	 * @return Date
	 */
	public Date getDate() {
		return this.date;
	}
	
	
	/** 
	 * method to get the holiday name of the holiday
	 * @return String
	 */
	public String getHolidayName() {
		return this.name;
	}

}