package Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Admin extends Person {
	private static final long serialVersionUID = 1L;
	private String adminId;
	private String adminPassword;

	public Admin(String name, String email, int contact)
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
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Admin admin1)) return false;
        return Objects.equals(getAdminId(), admin1.getAdminId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAdminId(),getAdminPassword());
    }
}
