/**
 * 
 */
package Prog2Pkg;

/**
 * @author Jeffrey Vanarsdall
 *
 */
public class Checking extends Account {

	private int accountNumber;
	private double balance;
	/**
	 * 
	 */
	public Checking() {
		// TODO Auto-generated constructor stub
		this.accountNumber = 0;
		this.balance = 0.0;
	}

	public Checking(int checkingNumber, double checkingBalance) {
		// TODO Auto-generated constructor stub
		this.accountNumber = checkingNumber;
		this.balance = checkingBalance;
	}

	@Override
	public String toString() {
		return "Checking Account, Number: " + accountNumber + ". Current balance  is: " + balance + ".";
	}

}
