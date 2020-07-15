
/**
 *
 * @author
 */
public class Savings extends Account {
    // you can make transfers and some withdrawals only six times a session.

    private static final int MONTHLY_LIMIT = 6;
    private int counter;

    public Savings(int accNum, double balance, double interest) {
        super(accNum, balance, interest);
        this.counter = 0;
    }

    /**
     * getter
     *
     * @return account name
     */
    @Override
    public String getAccountName() {
        return "Savings-" + getAccNum();
    }

    /**
     * withdraw money from the account
     *
     * @param amount amount of money
     * @return true if it is successful
     */
    @Override
    public boolean withdraw(double amount) {
        if (amount < 0 || amount > balance
                || counter >= MONTHLY_LIMIT) {
            System.out.println("User cannot have negative money in an account "
                    + "(unless its credit) or withdraw more than "
                    + MONTHLY_LIMIT + " times in a session");
            return false;
        }
        counter++;
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
     *
     * @return expression of this object
     */
    @Override
    public String toString() {
        return super.toString() + System.lineSeparator()
                + "Number of allowed transaction "
                + "or withdraw in the current session: "
                + (MONTHLY_LIMIT - counter);
    }

    /**
     * fill the array which is used by the user
     * @param arr array of string
     */
    @Override
    public void fillArray(String[] arr) {
        arr[4] = getAccNum() + "";
        arr[12] = getBalance() + "";
    }

}
