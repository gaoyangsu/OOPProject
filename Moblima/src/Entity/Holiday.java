package Entity;

import static Controller.MiscMethods.dateInput;

import java.io.Serializable;
import java.util.Date;

public class Holiday implements Serializable {

    private Date date;
	private String name;

	public Holiday(Date date, String name) {
		this.date = date;
		this.name = name;
	}
	
	public void setDate() {
		this.date=dateInput();
	}
	
	public void setHolidayName(String name) {
		this.name = name;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public String getHolidayName() {
		return this.name;
	}

}