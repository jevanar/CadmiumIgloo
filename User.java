
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author
 */
public class User extends Individual {

    private String address;
    private String phoneNumber;
    private ArrayList<Account> accounts;
    private static int id_ = 0;
    private int id;

    /**
     * constructor
     *
     * @param fName
     * @param lName
     * @param DOB
     * @param address
     * @param phoneNumber
     * @param savings
     */
    public User(String fName, String lName, String DOB,
            String address, String phoneNumber, Savings savings) {
        super(fName, lName, DOB);
        // Identification numbers should increment from the last user created        
        id_++;
        this.id = id_;
        this.address = address;
        this.phoneNumber = phoneNumber;
        accounts = new ArrayList<>();
        // Required for all users: Name, DOB, Address, Phone Number, and at 
        // least a savings account
        accounts.add(savings);
    }

    /**
     * constructor
     *
     * @param id
     * @param fName
     * @param lName
     * @param DOB
     * @param address
     * @param phoneNumber
     * @param savings
     */
    public User(int id, String fName, String lName, String DOB,
            String address, String phoneNumber, Savings savings) {
        super(fName, lName, DOB);
        this.id = id;
        if (id > id_) {
            id_ = id;
        }
        this.address = address;
        this.phoneNumber = phoneNumber;
        accounts = new ArrayList<>();
        // Required for all users: Name, DOB, Address, Phone Number, and at 
        // least a savings account
        accounts.add(savings);
        savings.setUser(this);
    }

    /**
     * getter
     *
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * getter
     *
     * @return
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * getter
     *
     * @return
     */
    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public int getID() {
        return id;
    }

    /**
     * setter
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * setter
     *
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Users should have the option to create a checking/credit account
    /**
     * create checking account
     *
     * @param accNum
     * @param balance
     * @param interest
     */
    public void createCheckingAccount(int accNum, double balance,
            double interest) {
        Account account = new Checking(accNum, balance, interest);
        account.setUser(this);
        accounts.add(account);
    }

    /**
     * create credit account
     *
     * @param accNum
     * @param balance
     * @param max
     */
    public void createCrreditAccount(int accNum, double balance,
            double max) {
        Account account = new Credit(accNum, balance, max);
        account.setUser(this);
        accounts.add(account);
    }

    @Override
    public String toString() {
        String output = "ID: " + id + System.lineSeparator()
                + super.toString() + System.lineSeparator();
        output += "Address: " + address + System.lineSeparator()
                + "Phone number: " + phoneNumber + System.lineSeparator();
        /* for (Account account : accounts) {
            output += account.toString() + System.lineSeparator()
                    + System.lineSeparator();
        }*/
        return output;
    }

    public void writeBankStatement(FileWriter fw) throws IOException {
        fw.write(this.toString());
        fw.write(System.lineSeparator());

        for (Account account : accounts) {
            fw.write(account.toString() + System.lineSeparator());
            fw.write(account.getStatement().toString()
                    + System.lineSeparator());
        }
    }

    public void writeUserToFile(FileWriter fw) throws IOException {
        String[] line = new String[13];
        for (Account account : accounts) {
            account.fillArray(line);
        }
        line[2] = "\"" + address + "\"";
        line[3] = id + "";
        line[5] = getlName();
        line[6] = "\"" + getDOB() + "\"";
        line[8] = getfName();
        line[10] = phoneNumber;
        fw.write(String.join(",", Arrays.asList(line))
                + System.lineSeparator());

    }
}
