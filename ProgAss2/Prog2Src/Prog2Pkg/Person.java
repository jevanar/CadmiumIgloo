/**
 * 
 */
package Prog2Pkg;

import java.util.Date;

/**
 * @author Jeffrey Vanarsdall
 *
 */
public abstract class Person {

	//Use array for data structure to hold accounts, Access is most important feature
	private String name;
	private String dateOfBirth;
	private String address;
	private String phoneNumber;
	
	public Person() {
		this.name = null;
		this.dateOfBirth = null;
		this.address = null;
		this.phoneNumber = null;
	}
	public Person(String name, String dateOfBirth, String address, String phoneNumber) {
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return the dateOfBirth
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
