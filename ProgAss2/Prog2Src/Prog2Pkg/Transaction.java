/**
 * 
 */
package Prog2Pkg;

/**
 * @author Jeffrey Vanarsdall
 *
 */
public class Transaction {

	private User originatingUser;
	private Account originatingAccount;
	private User reciever;
	private Account recievingAccount;
	private String action;
	private double amount;
	
	
	public Transaction() {
		this.originatingUser = new User();
		this.originatingAccount = null;
		this.reciever = new User();
		this.recievingAccount = null;
		this.action = null;
		this.amount = 0.00;
	}
	
	public Transaction(User originatingUser, Account originatingAccount, User reciever, Account recievingAccount, String action, double amount) {
		this.originatingUser = originatingUser;
		this.originatingAccount = originatingAccount;
		this.reciever = reciever;
		this.recievingAccount = recievingAccount;
		this.action = action;
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Transaction [" + (originatingUser != null ? "User " + originatingUser.getFirstName() + " " + originatingUser.getLastName() : "")
				+ (action != null ?  action + "ed " : "") + "$" + amount + " from "
				+ (originatingAccount != null ? originatingAccount.getType() + " to " : "")
				+ (reciever != null ? "User " + reciever.getName() + ", " : "") + "$" + amount + "." + "]";
	}
	
	
}
