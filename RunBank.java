
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author
 */
public class RunBank {

    /**
     * main function
     *
     * @param args
     */
    public static void main(String[] args) {
        /**
         * Read a file with information, and store it appropriately
         *
         * a. Pick a data structure that is appropriate
         *
         * i. Consider the time complexity b. Consider how your objects will
         * interact with each other
         */
        ArrayList<User> users = new ArrayList<>();
        ArrayList<String> log = new ArrayList<>();
        Scanner keyboard = new Scanner(System.in);

        System.out.print("Enter name of input file: ");
        String path = keyboard.nextLine().trim();
        readFile(path, users);

        char selection;
        do {

            System.out.println("Who are you\n"
                    + "a. An individual person\n"
                    + "b. A Bank Manager\n"
                    + "c. Transaction Reader\n"
                    + "s. Bank Statement\n"
                    + "q. Quit");
            selection = keyboard.nextLine().charAt(0);

            switch (selection) {
                case 'a':
                    individualPerson(users, log, keyboard);
                    break;
                case 'b':
                    bankManager(users, log, keyboard);
                    break;
                case 'c':
                    transactionReader(users, log, keyboard);
                    break;
                case 's':
                    bankStatement(users, keyboard);
                    break;
            }

            System.out.println("");
        } while (selection != 'q');

        writeUserToFile(users, keyboard);

    }

    /**
     * write all user to file
     *
     * @param users
     * @param keyboard
     */
    private static void writeUserToFile(ArrayList<User> users, Scanner keyboard) {
        System.out.print("Enter output file name: ");
        String path = keyboard.nextLine().trim();

        try {
            FileWriter fw = new FileWriter(path);

            fw.write("Credit Max,Credit Starting Balance,Address,"
                    + "Identification Number,Savings Account Number,"
                    + "Last Name,Date of Birth,Checking Account Number,"
                    + "First Name,Credit Account Number,Phone Number,"
                    + "Checking Starting Balance,Savings Starting Balance"
                    + System.lineSeparator());

            for (User user : users) {
                user.writeUserToFile(fw);
            }
            fw.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Write a Bank Statement file for a specific user i. Choose a user by name
     * ii. The formatting is up to you (Google sample bank statements for
     * inspiration.) – Does not have to be fancy, but functional iii. All
     * information about the user should be on the statement 1. Name, address,
     * phone, etc. iv. All transactions should be written 1. For a particular
     * session of running the code
     *
     * @param users
     */
    private static void bankStatement(ArrayList<User> users, Scanner keyboard) {
        User user = selectAUser(users, keyboard);
        if (user == null) {
            System.out.println("Invalid user");
            return;
        }

        System.out.print("Enter output file name: ");
        String path = keyboard.nextLine().trim();

        try {
            FileWriter fw = new FileWriter(path);
            user.writeBankStatement(fw);
            fw.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * select a user by name
     *
     * @param users
     * @param keyboard
     * @return
     */
    private static User selectAUser(ArrayList<User> users, Scanner keyboard) {
        System.out.print("Enter name of the user: ");
        String name = keyboard.nextLine().trim();

        for (User user : users) {
            if (user.getFullName().equals(name)) {
                return user;
            }
        }

        return null;
    }

    /**
     * read transaction file and run all tasks
     *
     * @param users
     * @param log
     * @param keyboard
     */
    private static void transactionReader(ArrayList<User> users,
            ArrayList<String> log, Scanner keyboard) {
        System.out.print("Enter name of transaction file: ");
        String path = keyboard.nextLine().trim();
        try {
            Scanner input = new Scanner(new File(path));
            String line = input.nextLine();
            while (input.hasNext()) {
                line = input.nextLine();
                takeAction(users, log, line);
            }

            input.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * take one task
     *
     * @param users
     * @param log
     * @param line
     */
    private static void takeAction(ArrayList<User> users,
            ArrayList<String> log, String line) {
        System.out.println("");
        System.out.println(line);
        String[] sp = line.split(",");
        String action = sp[3];

        switch (action) {
            case "pays":
                autoPay(users, log, sp);
                break;
            case "transfers":
                autoTransfer(users, log, sp);
                break;
            case "inquires":
                autoInquire(users, log, sp);
                break;
            case "withdraws":
                autoWithdraw(users, log, sp);
                break;
            case "deposits":
                autoDeposit(users, log, sp);
                break;
        }
    }

    /**
     * depositing
     *
     * @param users
     * @param log
     * @param sp
     */
    private static void autoDeposit(ArrayList<User> users,
            ArrayList<String> log, String[] sp) {
        Account account = findAccount(users, sp[4], sp[5], sp[6]);
        if (account != null) {
            try {
                account.depositing(log, Double.parseDouble(sp[7]));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * withdrawing
     *
     * @param users
     * @param log
     * @param sp
     */
    private static void autoWithdraw(ArrayList<User> users,
            ArrayList<String> log, String[] sp) {
        Account account = findAccount(users, sp[0], sp[1], sp[2]);
        if (account != null) {
            try {
                account.withdrawing(log, Double.parseDouble(sp[7]));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * inquiring
     *
     * @param users
     * @param log
     * @param sp
     */
    private static void autoInquire(ArrayList<User> users,
            ArrayList<String> log, String[] sp) {
        Account account = findAccount(users, sp[0], sp[1], sp[2]);
        if (account != null) {
            account.inquiring(log);
        }
    }

    /**
     * transferring
     *
     * @param users
     * @param log
     * @param sp
     */
    private static void autoTransfer(ArrayList<User> users,
            ArrayList<String> log, String[] sp) {
        Account sender = findAccount(users, sp[0], sp[1], sp[2]);
        Account receiver = findAccount(users, sp[4], sp[5], sp[6]);
        if (sender == null || receiver == null) {
            System.out.println("One or both accounts are invalid");
            return;
        }
        try {
            sender.transfering(log, Double.parseDouble(sp[7]), receiver);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * paying
     *
     * @param users
     * @param log
     * @param sp
     */
    private static void autoPay(ArrayList<User> users,
            ArrayList<String> log, String[] sp) {
        Account sender = findAccount(users, sp[0], sp[1], sp[2]);
        Account receiver = findAccount(users, sp[4], sp[5], sp[6]);
        if (sender == null || receiver == null) {
            System.out.println("One or both accounts are invalid");
            return;
        }
        try {
            sender.paying(log, Double.parseDouble(sp[7]), receiver);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * find an account which has the same name and the same type
     *
     * @param users
     * @param fName
     * @param lName
     * @param type
     * @return
     */
    private static Account findAccount(ArrayList<User> users,
            String fName, String lName, String type) {
        User user = findUser(users, fName, lName);
        if (user == null) {
            return null;
        }
        for (Account account : user.getAccounts()) {
            if (account.getClass().getName().equals(type)) {
                return account;
            }
        }
        System.out.println(user.getFullName()
                + " doesn't have account type " + type);
        return null;
    }

    /**
     * find a user by name
     *
     * @param users
     * @param fName
     * @param lName
     * @return
     */
    private static User findUser(ArrayList<User> users,
            String fName, String lName) {
        String name = fName + " " + lName;
        for (User user : users) {
            if (user.getFullName().equals(name)) {
                return user;
            }
        }
        System.out.println(name + " isn't in the system");
        return null;
    }

    /**
     * individual person task
     *
     * @param accounts list of all account
     * @param log list of all logs
     * @param keyboard input stream
     */
    private static void individualPerson(ArrayList<User> users,
            ArrayList<String> log, Scanner keyboard) {

        Account account = selectAnAccount(users, keyboard);

        if (account == null) {
            return;
        }
        char selection;
        System.out.println("a. Inquire a balance\n"
                + "b. Deposit money\n"
                + "c. Withdraw money\n"
                + "d. Transfer money (i.e. from checking to credit account)\n"
                + "e. Pay someone (i.e. Mickey pays Donald)"
        );
        selection = keyboard.nextLine().charAt(0);

        switch (selection) {
            case 'a':
                inquire(account, log);
                break;
            case 'b':
                deposit(account, log, keyboard);
                break;
            case 'c':
                withdraw(account, log, keyboard);
                break;
            case 'd':
                transfer(account, users, log, keyboard);
                break;
            case 'e':
                pay(account, users, log, keyboard);
                break;
        }
    }

    /**
     * paying
     *
     * @param account an account
     * @param accounts list of all account
     * @param log list of all logs
     * @param keyboard input stream
     */
    private static void pay(Account account,
            ArrayList<User> users,
            ArrayList<String> log, Scanner keyboard) {

        System.out.println("Enter the information the receiver: ");
        Account receiver = selectAnAccount(users, keyboard);

        if (receiver == null) {
            return;
        }

        try {
            System.out.print("Enter the the amount: ");
            double amount = Double.parseDouble(keyboard.nextLine().trim());

            account.paying(log, amount, receiver);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * transferring payment
     *
     * @param account an account
     * @param accounts list of all account
     * @param log list of all logs
     * @param keyboard input stream
     */
    private static void transfer(Account account,
            ArrayList<User> users,
            ArrayList<String> log, Scanner keyboard) {

        System.out.println("Enter the information the receiver: ");
        Account receiver = selectAnAccount(users, keyboard);

        if (receiver == null) {
            return;
        }

        try {
            System.out.print("Enter the the amount: ");
            double amount = Double.parseDouble(keyboard.nextLine().trim());
            account.transfering(log, amount, receiver);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * withdrawing
     *
     * @param accounts list of all account
     * @param log list of all logs
     * @param keyboard input stream
     */
    private static void withdraw(Account account,
            ArrayList<String> log, Scanner keyboard) {

        try {
            System.out.print("Enter the the amount: ");
            double amount = Double.parseDouble(keyboard.nextLine().trim());
            account.withdrawing(log, amount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * depositing
     *
     * @param accounts list of all account
     * @param log list of all logs
     * @param keyboard input stream
     */
    private static void deposit(Account account,
            ArrayList<String> log, Scanner keyboard) {

        try {
            System.out.print("Enter the the amount: ");
            double amount = Double.parseDouble(keyboard.nextLine().trim());
            account.depositing(log, amount);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void inquire(Account account, ArrayList<String> log) {
        account.inquiring(log);
    }

    private static Account selectAnAccount(ArrayList<User> users, Scanner keyboard) {
        System.out.print("Enter the user id: ");
        int id = Integer.parseInt(keyboard.nextLine().trim());
        User user = null;
        for (User a_user : users) {
            if (a_user.getID() == id) {
                user = a_user;
                break;
            }
        }

        if (user == null) {
            System.out.println("Invalid user id");
            return null;
        }

        System.out.print("Enter the account number: ");
        int accNum = Integer.parseInt(keyboard.nextLine().trim());

        Account account = null;

        for (Account acc : user.getAccounts()) {
            if (acc.getAccNum() == accNum) {
                account = acc;
                break;
            }
        }
        if (account == null) {
            System.out.println("Invalid account nubmer");
        }
        return account;
    }

    private static void bankManager(ArrayList<User> users,
            ArrayList<String> log, Scanner keyboard) {
        System.out.println("A. Inquire\n"
                + "B. Print all accounts\n"
                + "C. Print log");
        char selection = keyboard.nextLine().toLowerCase().charAt(0);

        switch (selection) {
            case 'a':
                inquire(users, keyboard);
                break;
            case 'b':
                for (User user : users) {
                    for (Account account : user.getAccounts()) {
                        System.out.println(account.toString());
                        System.out.println("");
                    }
                }
                break;
            case 'c':
                for (String string : log) {
                    System.out.println("\"" + string + "\"");
                }
                break;
        }
    }

    /**
     *
     * inquiring
     *
     * @param accounts list of all account
     * @param keyboard input stream
     */
    private static void inquire(ArrayList<User> users,
            Scanner keyboard) {
        System.out.println("A. Inquire account by name\n"
                + "B. Inquire account by type/number\n");
        char selection = keyboard.nextLine().toLowerCase().charAt(0);

        switch (selection) {
            case 'a':
                inquireByName(users, keyboard);
                break;
            case 'b':
                inquireByTypeAndNumber(users, keyboard);
                break;
        }
    }

    /**
     *
     * inquiring by type and number
     *
     * @param accounts list of all account
     * @param keyboard input stream
     */
    private static void inquireByTypeAndNumber(ArrayList<User> users,
            Scanner keyboard) {
        System.out.println("What account type?");
        String type = keyboard.nextLine().trim();

        System.out.println("What is the account number?");
        int number = Integer.parseInt(keyboard.nextLine().trim());

        for (User user : users) {
            for (Account account : user.getAccounts()) {
                if (account.getClass().getName().equalsIgnoreCase(type)
                        && account.getAccNum() == number) {
                    System.out.println(account.toString());
                    System.out.println("");
                }
            }
        }

    }

    /**
     *
     * inquiring by name
     *
     * @param accounts list of all account
     * @param keyboard input stream
     */
    private static void inquireByName(ArrayList<User> users,
            Scanner keyboard) {
        System.out.println("Who's account would you like to inquire about?");
        String name = keyboard.nextLine().trim();
        for (User user : users) {
            if (user.getFullName().equals(name)) {
                System.out.println(user.toString());
                for (Account account : user.getAccounts()) {
                    System.out.println(account.toString());
                }
                System.out.println("");
            }
        }
    }

    private static void readFile(String path, ArrayList<User> users) {
        try {
            Scanner input = new Scanner(new File(path));
            /// skip this first line 
            input.nextLine();
            while (input.hasNext()) {
                String line = input.nextLine().trim();
                parseUser(line, users);
            }
            input.close();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * parse an input line to create a new user or add account an old one
     *
     * @param line
     */
    private static void parseUser(String line, ArrayList<User> users) {
        line = fixString(line);
        String[] sp = line.split(",");
        double creditMax = Double.parseDouble(sp[0]);
        double creditBalance = Double.parseDouble(sp[1]);
        String address = sp[2].replaceAll("##", ",");
        int id = Integer.parseInt(sp[3]);
        int savingAccNum = Integer.parseInt(sp[4]);
        String lName = sp[5].replaceAll("##", ",");
        String DOB = sp[6].replaceAll("##", ",");
        int checkingAccNum = Integer.parseInt(sp[7]);
        String fName = sp[8].replaceAll("##", ",");
        int creditAccNum = Integer.parseInt(sp[9]);
        String phoneNum = sp[10].replaceAll("##", ",");
        double checkingBalance = Double.parseDouble(sp[11]);
        double savingBalance = Double.parseDouble(sp[12]);

        User user = null;
        for (User a_user : users) {
            if (a_user.getID() == id) {
                user = a_user;
                break;
            }
        }

        if (user == null) {
            user = new User(id, fName, lName, DOB, address, phoneNum,
                    new Savings(savingAccNum, savingBalance, 0));
            users.add(user);
        }

        user.createCheckingAccount(checkingAccNum, checkingBalance, 0);

        user.createCrreditAccount(creditAccNum, creditBalance, creditMax);
    }

    /**
     * Fix format of the line
     *
     * @param line
     * @return
     */
    private static String fixString(String line) {
        String newline = "";
        boolean quote = false;
        for (int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);
            if (ch == '"') {
                quote = !quote;
            } else if (ch == ',' && quote == true) {
                newline += "##";
            } else {
                newline += ch;
            }
        }
        return newline;
    }
}
