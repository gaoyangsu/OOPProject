package Entity;

import java.util.ArrayList;
import java.util.List;


public class Customer extends Person {
	private static final long serialVersionUID = 1L;
	private String movieGoerId;
	

	public Customer(String name, String email, int contact)
	{
		super(name, email, contact);
	}

	public String getMovieGoerId() {
		return movieGoerId;
	}

	public void setMovieGoerId(String movieGoerId) {
		this.movieGoerId = movieGoerId;
	}
}
