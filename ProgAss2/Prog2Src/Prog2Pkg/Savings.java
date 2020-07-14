/**
 * 
 */
package Prog2Pkg;

/**
 * @author Jeffrey Vanarsdall
 *
 */
public class Savings extends Account {

	private int accountNumber;
	private double balance;
	/**
	 * 
	 */
	public Savings() {
		// TODO Auto-generated constructor stub
		this.accountNumber = 0;
		this.balance = 0.0;
	}

	public Savings(int savingsNumber, double savingsBalance) {
		// TODO Auto-generated constructor stub
		this.accountNumber = savingsNumber;
		this.balance = savingsBalance;
	}

	@Override
	public String toString() {
		return "Savings Account Number: " + accountNumber + ". Current balance is " + balance + ".";
	}

}
