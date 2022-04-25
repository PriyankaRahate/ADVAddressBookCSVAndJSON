package AddressBookDay28;

import java.util.Scanner;

/**
 * Template class for creating a contact
 */
class ContactInfo {

	/**
	 * declaring variables
	 */
	String firstName, lastName, address, city, state, zipcode, phoneNo, email;

	/**
	 * getter setter methods created method getFirstName() The get method returns
	 * the value of the variable firstName.
	 * 
	 * @return -return to method created
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * created method getlastName() The get method returns the value of the variable
	 * lastName.
	 * 
	 * @return -return to method created
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * The get method returns the value of the variable address.
	 * 
	 * @return -return to method created
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * The get method returns the value of the variable city
	 * 
	 * @return -return to method created
	 */
	public String getCity() {
		return city;
	}

	/**
	 * The get method returns the value of the variable state.
	 * 
	 * @return -return to method created
	 */
	public String getState() {
		return state;
	}

	/**
	 * The get method returns the value of the variable zipcode.
	 * 
	 * @return -return to method created
	 */
	public String getZipcode() {
		return zipcode;
	}

	/**
	 * The get method returns the value of the variable MobileNumber.
	 * 
	 * @return -return to method created
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 * The get method returns the value of the variable EmailId.
	 * 
	 * @return -return to method created
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * The set method takes a parameter (firstName) and assigns it to the firstName
	 * variable. The this keyword is used to refer to the current object.
	 * 
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * The set method takes a parameter (lastName) and assigns it to the lastName
	 * variable. The this keyword is used to refer to the current object.
	 * 
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * The set method takes a parameter (address) and assigns it to the address
	 * variable. The this keyword is used to refer to the current object.
	 * 
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * The set method takes a parameter (city) and assigns it to the city variable.
	 * The this keyword is used to refer to the current object.
	 * 
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * The set method takes a parameter (state) and assigns it to the state
	 * variable. The this keyword is used to refer to the current object.
	 * 
	 * @param state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * The set method takes a parameter (zipcode) and assigns it to the zipcode
	 * variable. The this keyword is used to refer to the current object.
	 * 
	 * @param zipcode
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	/**
	 * The set method takes a parameter (mobileNumber) and assigns it to the
	 * mobileNumber variable. The this keyword is used to refer to the current
	 * object.
	 * 
	 * @param mobileNumber
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	/**
	 * The set method takes a parameter (emailId) and assigns it to the emailId
	 * variable. The this keyword is used to refer to the current object.
	 * 
	 * @param emailId
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Method to save contact details
	 */
	public void setContactInfo() {

		Scanner sc = new Scanner(System.in);
		System.out.print(
				"Enter First Name: \n Last Name: \n Address: \n City: \n State: \n Zipcode: \n PhoneNO: \n Email: \n");
		setFirstName(sc.nextLine());
		setLastName(sc.nextLine());
		setAddress(sc.nextLine());
		setCity(sc.nextLine());
		setState(sc.nextLine());
		setZipcode(sc.nextLine());
		setPhoneNo(sc.nextLine());
		setEmail(sc.nextLine());
	}

	/**
	 * Method to display the contact details
	 */
	public void displayContactInfo() {
		System.out.print(" First Name: " + firstName + "\n Last Name: " + lastName + "\n Address: " + address
				+ "\n City: " + city + "\n State: " + state + "\n Zipcode: " + zipcode + "\n PhoneNO: " + phoneNo
				+ "\n Email: " + email + "\n");
	}

	public String showContact() {
		return firstName + "," + lastName + "," + address + "," + city + "," + state + "," + zipcode + "," + phoneNo
				+ "," + email + "\n";
	}

	public String showContactCSV() {
		return firstName + "," + lastName + "," + address + "," + city + "," + state + "," + zipcode + "," + phoneNo
				+ "," + email;
	}
}