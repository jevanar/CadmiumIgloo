/**
 * 
 */
package Prog2Pkg;

/**
 * @author Jeffrey Vanarsdall
 *
 */
public class Credit extends Account {

	
	private int accountNumber;
	private double maximum;
	private double balance;
	
	
	public Credit() {
		// TODO Auto-generated constructor stub
		this.accountNumber = 0;
		this.maximum = 0.0;
		this.balance = 0.0;
	}

	public Credit(int creditNumber, double creditMax, double creditStartingBalance) {
		// TODO Auto-generated constructor stub
		this.accountNumber = creditNumber;
		this.maximum = creditMax;
		this.balance = creditStartingBalance;
	}

	@Override
	public String toString() {
		return "Credit Account Number: " + accountNumber + ". Credit Line Maximum is: " + maximum + ", and the current balance is" + balance + ".";
	}

}
