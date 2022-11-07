package Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
    Entity Class representing an Admin account
	Sub Class of a person
    @version 1.0
    @since 2022-10-30
 */
public class Admin extends Person {
	/** UID for serialiser */
	private static final long serialVersionUID = 1L;
	/** AdminID */
	private String adminId;
	/** AdminPW */
	private String adminPassword;

	/**
	 * Constructor of Admin class inheriting the
	 * @param name
	 * @param email
	 * @param contact
	 * from Person SuperClass
	 */
	public Admin(String name, String email, int contact)
	{
		super(name, email, contact);
	}

	
	/** 
	 * method to get admin ID
	 * @return String
	 */
	public String getAdminId() {
		return adminId;
	}

	
	/** 
	 * method to set admin ID
	 * @param adminId
	 */
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
    
	/** 
	 * method to get admin PW
	 * @return String
	 */
	public String getAdminPassword() {
		return adminPassword;
	}

	
	/** 
	 * method to set admin PW
	 * @param adminPassword
	 */
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	
	
	/** 
	 * equals method to find the Admin Class
	 * @param o
	 * @return boolean
	 */
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Admin admin1)) return false;
        return Objects.equals(getAdminId(), admin1.getAdminId());
    }

    
	/** 
	 * hash method to Hash Admin Class based on ID and PW
	 * @return int
	 */
	@Override
    public int hashCode() {
        return Objects.hash(getAdminId(),getAdminPassword());
    }
}
