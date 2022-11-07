package Entity;

import java.io.Serializable;

/**
    Entity Class representing a Person
	For MovieGoer and Admin Sub-classes
    @version 1.0
    @since 2022-10-27
 */
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;
	/** Person Name */
	private String name;
	/** Person Email */
	private String email;
	/** Person contact number */
	private int contact;
	
	/** Empty Person Constructor */
	public Person(){
	}

	/** Person Constructor with
	 * @param name
	 * @param email
	 * @param contact 
	 */
	public Person(String name, String email, int contact) {
		super();
		this.name = name;
		this.email = email;
		this.contact = contact;
	}
	

	
	/** 
	 * getter of the name of the Person
	 * @return String
	 */
	public String getName() {
		return name;
	}
	

	
	/** 
	 * getter of the email of the Person
	 * @return String
	 */
	public String getEmail() {
		return email;
	}
	

	
	/** 
	 * getter of the contact of the Person
	 * @return int
	 */
	public int getContact() {
		return contact;
	}
	
	
	/** 
	 * setter of the name of the Person
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	

	
	/** 
	 * setter of the email of the Person
	 * @param email {@code String} email to be inputted
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	

	
	/** 
	 *  setter of the contact of the Person
	 * @param contact {@code String} contact to be inputted
	
	 */
	public void setContact(int contact) {
		this.contact = contact;
	}
}