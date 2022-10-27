package Entity;

import java.io.Serializable;


public class Person implements Serializable {
	private static final long serialVersionUID = 1L;
	/** Person Name */
	private String name;
	/** Person Email */
	private String email;
	/** Person contact number */
	private int contact;
	

	public Person(){
	}

	public Person(String name, String email, int contact) {
		super();
		this.name = name;
		this.email = email;
		this.contact = contact;
	}
	

	public String getName() {
		return name;
	}
	

	public String getEmail() {
		return email;
	}
	

	public int getContact() {
		return contact;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	

	public void setEmail(String email) {
		this.email = email;
	}
	

	public void setContact(int contact) {
		this.contact = contact;
	}
}