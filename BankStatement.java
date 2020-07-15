
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author
 */
public class BankStatement {

    // a. Customer Information
    // b. Account Information
    private Account account;
    // c. Starting Balance (Beginning of session)
    private double startingBalance;
    // d. Ending Balance (At the end of the program/at time requested)
    private double endingBalance;
    // e. All transactions for that person
    private ArrayList<String[]> transactions;
    // f. Date of transaction (date of running the code)
    private ArrayList<LocalDateTime> dates;

    /**
     * constructor
     *
     * @param account
     */
    public BankStatement(Account account) {
        this.account = account;
        this.startingBalance = account.getBalance();
        this.endingBalance = startingBalance;
        this.transactions = new ArrayList<>();
        this.dates = new ArrayList<>();
    }

    /**
     * setter
     *
     * @param startingBalance
     */
    public void setStartingBalance(double startingBalance) {
        this.startingBalance = startingBalance;
    }

    /**
     * setter
     *
     * @param endingBalance
     */
    public void setEndingBalance(double endingBalance) {
        this.endingBalance = endingBalance;
    }

    /**
     * store a transaction and its run-time
     *
     * @param transaction
     */
    public void addTransaction(String[] transaction) {
        transactions.add(transaction);
        dates.add(LocalDateTime.now());
        endingBalance = account.getBalance();
    }

    /**
     * string expression of this object
     *
     * @return
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        String output = "";

        output += "Starting Balance : " + df.format(startingBalance)
                + System.lineSeparator();
        output += "Ending Balance : " + df.format(endingBalance)
                + System.lineSeparator() + System.lineSeparator();

        output += String.format("%-20s%-20s%-15s%-15s%-20s%-20s%-15s%15s%28s",
                "From First Name", "From Last Name", "From Where",
                "Action", "To First Name", "To Last Name",
                "To Where", "Action Amount", "Date of transaction")
                + System.lineSeparator();

        for (int i = 0; i < transactions.size(); i++) {
            String[] sp = transactions.get(i);
            output += String.format("%-20s%-20s%-15s%-15s%-20s%-20s%-15s%15s%28s",
                    sp[0], sp[1], sp[2],
                    sp[3], sp[4], sp[5],
                    sp[6], sp[7], dates.get(i).toString())
                    + System.lineSeparator();
        }
        output += System.lineSeparator();
        return output;
    }

}
