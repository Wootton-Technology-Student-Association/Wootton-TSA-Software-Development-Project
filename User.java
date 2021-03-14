public class User {

	private String name;
	private String address;
	private String dateOfBirth;
	private String email;
	private String phoneNumber;
	private String password;

	public User(String n,String a,String dob, String e, String phone,String p) {
		setName(n);
		setAddress(a);
		setDateOfBirth(dob);
		setEmail(e);
		setPhoneNumber(phone);
		setPassword(p);
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public boolean setName(String other) {

		if(other.equals("")) {
			return false;
		}
		name=other;
		return true;

	}

	public boolean setAddress(String other) {

		if(other.equals("")) {
			return false;
		}
		address=other;
		return true;

	}

	public boolean setDateOfBirth(String other) {

		if(!other.matches("^(19|20)\\d\\d([- /.])(0[1-9]|1[012])\\2(0[1-9]|[12][0-9]|3[01])$")) {
			return false;
		}
		dateOfBirth=other;
		return true;

	}

	public boolean setEmail(String other) {

		if(other.equals("")||!other.contains("@")) {
			return false;
		}
		email=other;
		return true;

	}

	public boolean setPhoneNumber(String other) {

		try {
			Long.parseLong(other);
		}catch(NumberFormatException e) {
			return false;
		}
		
		if(other.equals("")||other.length()!=10) {
			return false;
		}
		phoneNumber=other;
		return true;

	}

	public boolean setPassword(String other) {

		if(other.equals("")) {
			return false;
		}
		password=other;
		return true;

	}
}
