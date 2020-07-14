package Prog2Pkg;
/**
 * This package exists to contain the source code of Programming Lab 2 for Professor Mejia's AOOP Course
 */

/**
 * @author Jeffrey Vanarsdall
 *
 */
public abstract class Account {

	private int accountNumber;
	private double balance;
	private String type;
	
	public Account() {
		this.accountNumber = 0000;
		this.balance = 00.00;
	}
	
	public boolean pay(Account to, double amount) {
		boolean returner = false;
		return returner;
	}
	
	public void deposit(double newAmount) {
		
	}
	
	public double withdraw(double drawAmount) {
		return drawAmount;
		}
	
	public boolean transfer(Account to, double amount) {
		boolean returner = false;
		return returner;
	}

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", balance=" + balance + "]";
	}

	protected abstract String getType();
	
	
}
