
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 *
 * @author
 */
public abstract class Account {

    private int accNum;
    protected double balance;
    protected double interest;
    private User user = null;
    private BankStatement statement;

    /**
     * constructor
     *
     * @param accNum
     * @param interest
     * @param statement
     */
    public Account(int accNum, double interest) {
        this.accNum = accNum;
        this.interest = interest;        
        this.statement = new BankStatement(this);
    }

    /**
     * constructor
     *
     * @param accNum
     * @param balance
     * @param interest
     */
    public Account(int accNum, double balance, double interest) {
        this.accNum = accNum;
        this.balance = balance;
        this.interest = interest;        
        this.statement = new BankStatement(this);        
    }

    /**
     * getter
     *
     * @return
     */
    public int getAccNum() {
        return accNum;
    }

    /**
     * getter
     *
     * @return
     */
    public double getBalance() {
        return balance;
    }

    /**
     * getter
     *
     * @return
     */
    public double getInterest() {
        return interest;
    }

    public User getUser() {
        return user;
    }

    public BankStatement getStatement() {
        return statement;
    }

    /**
     * setter
     *
     * @param accNum
     */
    public void setAccNum(int accNum) {
        this.accNum = accNum;
    }

    /**
     * setter
     *
     * @param balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * setter
     *
     * @param interest
     */
    public void setInterest(double interest) {
        this.interest = interest;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * abstract method
     *
     * @return account name
     */
    public abstract String getAccountName();
    
    public abstract void fillArray(String [] arr);

    /**
     * abstract method
     *
     * @param amount amount to be withdrawn
     * @return true if it's successful
     */
    public abstract boolean withdraw(double amount);

    /**
     * abstract method
     *
     * @param amount amount to be deposit
     * @return true if it's successful
     */
    public abstract boolean deposit(double amount);

    /**
     * transfer an amount from this account to another account
     *
     * @param account another account
     * @param amount transfered amount
     * @return true if it is successful
     */
    public boolean transfer(Account account, double amount) {
        if (this.withdraw(amount) == true) {
            if (account.deposit(amount) == true) {
                return true;
            }
            balance += amount;
        }
        return false;
    }

    /**
     *
     * @return expression of this object
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        String str = "";
        str += "Account Number: " + accNum + System.lineSeparator()
                + "Balance: " + df.format(balance) + System.lineSeparator()
                + "Interest: " + df.format(interest) + "%";
        return str;
    }

    /**
     * inquire this object
     *
     * @param log list of the log
     */
    public void inquiring(ArrayList<String> log) {
        if (user == null) {
            System.out.println("Error");
            return;
        }
        String name = user.getFullName();

        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String str = name + "  made a balance inquiry on "
                + getAccountName() + ". ";
        str += name + "'s Balance for "
                + getAccountName() + ": " + formatter.format(balance);
        System.out.println(str);

        String[] transaction = new String[8];
        transaction[0] = user.getfName();
        transaction[1] = user.getlName();
        transaction[2] = this.getClass().getName();
        transaction[3] = "inquires";
        statement.addTransaction(transaction);
        log.add(str);
    }

    /**
     * deposit money to this account
     *
     * @param log list of the log
     * @param amount amount to be deposited
     */
    public void depositing(ArrayList<String> log, double amount) {
        if (user != null && deposit(amount)) {
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            String name = user.getFullName();
            String str = name + "  deposited "
                    + formatter.format(amount)
                    + " to "
                    + getAccountName() + ". ";
            str += name + "'s New Balance for "
                    + getAccountName() + ": " + formatter.format(balance);
            System.out.println(str);
            log.add(str);
            String[] transaction = new String[8];
            transaction[4] = user.getfName();
            transaction[5] = user.getlName();
            transaction[6] = this.getClass().getName();
            transaction[7] = amount + "";
            transaction[3] = "deposits";

            statement.addTransaction(transaction);
        } else {
            System.out.println("Error");
        }
    }

    /**
     * withdraw money from this account
     *
     * @param log list of the log
     * @param amount amount to be withdrawn
     */
    public void withdrawing(ArrayList<String> log, double amount) {
        if (user != null && withdraw(amount)) {
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            String str = user.getFullName() + "  withdrew "
                    + formatter.format(amount)
                    + " in cash from "
                    + getAccountName() + ". ";

            str += user.getFullName() + "'s New Balance for "
                    + getAccountName() + ": " + formatter.format(balance);
            System.out.println(str);
            log.add(str);

            String[] transaction = new String[8];
            transaction[0] = user.getfName();
            transaction[1] = user.getlName();
            transaction[2] = this.getClass().getName();
            transaction[7] = amount + "";
            transaction[3] = "withdraws";

            statement.addTransaction(transaction);
        } else {
            System.out.println("Error");
        }
    }

    /**
     * transfer money from this account to another account
     *
     * @param log list of the log
     * @param amount transfered amount
     * @param other another account
     */
    public void transfering(ArrayList<String> log, double amount, Account other) {
        if (user != null
                && other.getUser() != null
                && transfer(other, amount)) {
            NumberFormat formatter = NumberFormat.getCurrencyInstance();

            String name = user.getFullName();
            String otherName = other.getUser().getName();

            String str = name + " transferred "
                    + otherName + " "
                    + formatter.format(amount)
                    + " from "
                    + getAccountName() + " to ";

            str += other.getAccountName() + ". ";

            str += name + "'s Balance for "
                    + getAccountName() + ": "
                    + formatter.format(balance) + ". ";
            str += otherName + "'s Balance for "
                    + other.getAccountName() + ": "
                    + formatter.format(other.getBalance());

            System.out.println(str);
            log.add(str);

            String[] transaction = new String[8];
            transaction[0] = user.getfName();
            transaction[1] = user.getlName();
            transaction[2] = this.getClass().getName();
            transaction[3] = "transfers";
            transaction[4] = other.getUser().getfName();
            transaction[5] = other.getUser().getlName();
            transaction[6] = other.getClass().getName();
            transaction[7] = amount + "";

            statement.addTransaction(transaction);

            if (other != this) {
                other.getStatement().addTransaction(transaction);
            }

        } else {
            System.out.println("Error");
        }
    }

    /**
     * pay an amount from this account to another account
     *
     * @param log list of the log
     * @param amount paid amount
     * @param other another account
     */
    public void paying(ArrayList<String> log, double amount, Account other) {
        if (user != null
                && other.getUser() != null
                && transfer(other, amount)) {
            NumberFormat formatter = NumberFormat.getCurrencyInstance();

            String name = user.getFullName();
            String otherName = other.getUser().getFullName();

            String str = name + " paid "
                    + otherName + " "
                    + formatter.format(amount)
                    + " from "
                    + getAccountName() + ". ";

            str += name + "'s New Balance for "
                    + getAccountName() + ": " + formatter.format(balance);
            System.out.println(str);
            log.add(str);

            String accountName = other.getAccountName();
            str = otherName + " received "
                    + formatter.format(amount)
                    + " from "
                    + name + ". ";

            str += otherName + "'s New Balance for "
                    + accountName + ": " + formatter.format(other.getBalance());
            System.out.println(str);
            log.add(str);
            
            String[] transaction = new String[8];
            transaction[0] = user.getfName();
            transaction[1] = user.getlName();
            transaction[2] = this.getClass().getName();
            transaction[3] = "pays";
            transaction[4] = other.getUser().getfName();
            transaction[5] = other.getUser().getlName();
            transaction[6] = other.getClass().getName();
            transaction[7] = amount + "";

            statement.addTransaction(transaction);

            if (other != this) {
                other.getStatement().addTransaction(transaction);
            }
            
        } else {
            System.out.println("Error");
        }
    }

}
