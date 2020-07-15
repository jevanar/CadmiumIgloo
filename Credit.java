
/**
 *
 * @author
 */
public class Credit extends Account {
    
    private double max;
    
    public Credit(int accNum, double balance, double max) {
        super(accNum, balance, 0);
        this.max = max;
    }

    /**
     * getter
     *
     * @return account name
     */
    @Override
    public String getAccountName() {
        return "Credit-" + getAccNum();
    }

    public double getMax() {
        return max;
    }
        

    /**
     * withdraw money from the account
     *
     * @param amount amount of money
     * @return true if it is successful
     */
    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) {
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
        /**
         * Credit accounts should not be able to accept more than the balance
         */
        if (amount <= 0 || amount > balance || balance + amount > max) {
            return false;
        }
        balance += amount;
        return true;
    }

    /**
     * fill the array which is used by the user
     * @param arr array of string
     */
    @Override
    public void fillArray(String[] arr) {
        arr[0] = max + "";
        arr[1] = getBalance() + "";
        arr[9] = getAccNum() + "";
    }   
    
}
