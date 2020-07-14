/**
 * 
 */
package Prog2Pkg;


/**
 * @author Jeffrey Vanarsdall
 *
 */
public class User extends Person {

	private int identNumber;
	private Account[] accountsList;
	/**
	 * 
	 */
	public User() {
		super();
	}
	public User(String name, String dateOfBirth, String address, String phoneNumber, int identNumber, Account[] accountsList) {
		super(name, dateOfBirth, address, phoneNumber);
		this.identNumber = identNumber;
		this.accountsList = accountsList;
	}
	
	public String toString() {
		String returner = "User number " + this.getIdentNumber() + ", " + this.getName() + ": born " + this.getDateOfBirth() 
		+ ", lives at " + this.getAddress() + ", can be reached at " + this.getPhoneNumber() + ".";
		returner.concat(" This user has accounts: " + this.getAccountsList(returner));
		
		return returner;
	}
	
	/**
	 * @return the identNumber
	 */
	public int getIdentNumber() {
		return identNumber;
	}
	/**
	 * @return the accountsList
	 */
	public String getAccountsList(String built) {
		for(int i = 0; i < this.accountsList.length; i++) {
			if(this.accountsList[i] != null) {
				built.concat(this.accountsList[i].toString());
			}
		}
		return built;
	}
	public Account[] getAccountsList() {
		// TODO Auto-generated method stub
		return this.accountsList;
	}
	public String getFirstName() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getLastName() {
		// TODO Auto-generated method stub
		return null;
	}

}
