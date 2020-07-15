
/**
 *
 * @author
 */
public class Checking extends Account {

    public Checking(int accNum, double balance, double interest) {
        super(accNum, interest);
        if (balance >= 0) {
            this.balance = balance;
        } else {
            throw new IllegalArgumentException("User cannot have negative"
                    + " money in an account");
        }
        BankStatement statement = this.getStatement();
        statement.setEndingBalance(balance);
        statement.setStartingBalance(balance);
    }

    /**
     * getter
     *
     * @return account name
     */
    @Override
    public String getAccountName() {
        return "Checking-" + getAccNum();
    }

    /**
     * withdraw money from the account
     *
     * @param amount amount of money
     * @return true if it is successful
     */
    @Override
    public boolean withdraw(double amount) {
        if (amount < 0 || amount > balance) {
            System.out.println("User cannot have negative money in an account "
                    + "(unless its credit)");
            return false;
        }
        balance -= amount;
        return true;
    }

    /**
     * deposit money to the account
     *
     * @param amount amount of money
     * @return true if it is successful
     */
    @Override
    public boolean deposit(double amount) {
        if (amount <= 0) {
            return false;
        }
        balance += amount;
        return true;
    }

    /**
     * fill the array which is used by the user
     *
     * @param arr array of string
     */
    @Override
    public void fillArray(String[] arr) {
        arr[7] = getAccNum() + "";
        arr[11] = getBalance() + "";
    }

}
