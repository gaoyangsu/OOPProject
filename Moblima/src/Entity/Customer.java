package Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


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
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer customer1)) return false;
        return Objects.equals(getMovieGoerId(), customer1.getMovieGoerId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMovieGoerId());
    }
}
