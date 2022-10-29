package Entity;

public class Staff extends Person {
	private static final long serialVersionUID = 1L;
	private String adminId;
    private String adminPassword;
	
	public Staff(String name, String email, int contact)
	{
		super(name, email, contact);
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

    public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	
}
