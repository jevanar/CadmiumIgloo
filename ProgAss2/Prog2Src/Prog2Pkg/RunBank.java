package Prog2Pkg;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class RunBank {
	public static final boolean DB = true;
	private static ArrayList<User> users = new ArrayList<User>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner keyboard = new Scanner(System.in);
		String lineHolder;
		String wordHolder;
		
		try {
		Path userPath = FileSystems.getDefault().getPath("Prog2Src\\Prog2Pkg\\resources", "users.csv");
		BufferedReader userReader = new BufferedReader(new InputStreamReader(new FileInputStream(userPath.toFile()), "utf-8"));
		lineHolder = userReader.readLine().trim();
		lineHolder = userReader.readLine().trim();
		while((lineHolder = userReader.readLine()) != null) {
			//Puts each line through the information extraction method, then prints the user
			int windex = 0;
			users.add(userPuller(lineHolder));
			if(DB)System.out.println("User added, displaying info:");
			User check = users.get(windex);
			for(int i = 0; i < check.getAccountsList().length; i++) {
				if(check.getAccountsList()[i] != null) {	
					if(DB)System.out.println(check.getAccountsList()[i]);
					}
			}
			windex++;
			}
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		//INITIALIZATION COMPLETE
		System.out.println("Initialization Complete: Welcome to the Bank Thingie. If you have an account with us you may [L]ogin"
				+ " with your Account Number and Name. If you are a Bank Thingie Manager, you may [M]anage your Branch from here."
				+ " If you do not have an account with us at this time, we can help you create a [N]ew User. What would you like to do?");
		System.out.println("[L]ogin as existing User:");
		System.out.println("[M]anage your Branch:");
		System.out.println("[N]ew User Account:");
		String uInput;
		String uInput2;
		double uAmount;
		int identNum;
		User validUser;
		User newUser;
		User payee;
		boolean runItBack = true;
		
		while(runItBack) {
			uInput = keyboard.nextLine().toUpperCase();
			if(uInput != null) {
				switch(uInput.substring(0, 1)) {
				case "L":
					System.out.println("You've selected Login. Please give me your Identification Number.");
					identNum = keyboard.nextInt();
					if(isValidID(identNum)) {
						validUser = getUserByID(identNum);
						System.out.println("Thank you!");
						System.out.println("What would you like to do?");
						System.out.println("[I]nquire about balances");
						System.out.println("[W]ithdraw funds?");
						System.out.println("[D]eposit funds?");
						System.out.println("[T]ransfer funds between accounts?");
						System.out.println("[P]ay another user?");
						uInput = keyboard.nextLine();
						if(uInput != null) {
							switch(uInput.substring(0, 1)) {
							case "I":
								System.out.println("Just a moment, retreiving that information for you, " + validUser.getName().substring(validUser.getName().indexOf(",")+1) + ".");
								validUser.getAccountsList();
								break;
							case "W":
								System.out.println("From which account?");
								System.out.println("[1] Checking");
								System.out.println("[2] Savings");
								System.out.println("[3] Credit");
								uInput = keyboard.nextLine();
								if(uInput != null) {
									switch(uInput.substring(0, 1)) {
									case "1":
										System.out.println("How much would you like to withdraw?");
										uAmount = keyboard.nextDouble();
										validUser.getAccountsList()[1].withdraw(uAmount);
										break;
										
									case "2":
										System.out.println("How much would you like to withdraw?");
										uAmount = keyboard.nextDouble();
										validUser.getAccountsList()[2].withdraw(uAmount);
										break;
										
									case "3":
										System.out.println("How much would you like to withdraw?");
										uAmount = keyboard.nextDouble();
										validUser.getAccountsList()[3].withdraw(uAmount);
										break;
										
									}
								}break;
							case "D":
								System.out.println("To which account?");
								System.out.println("[1] Checking");
								System.out.println("[2] Savings");
								System.out.println("[3] Credit");
								uInput = keyboard.nextLine();
								if(uInput != null) {
									switch(uInput.substring(0, 1)) {
									case "1":
										System.out.println("How much would you like to deposit?");
										uAmount = keyboard.nextDouble();
										validUser.getAccountsList()[1].deposit(uAmount);
										break;
										
									case "2":
										System.out.println("How much would you like to withdraw?");
										uAmount = keyboard.nextDouble();
										validUser.getAccountsList()[2].deposit(uAmount);
										break;
										
									case "3":
										System.out.println("How much would you like to withdraw?");
										uAmount = keyboard.nextDouble();
										validUser.getAccountsList()[3].deposit(uAmount);
										break;
										
									}
								}break;
							case "T":
								System.out.println("From which account?");
								System.out.println("[1] Checking");
								System.out.println("[2] Savings");
								System.out.println("[3] Credit");
								uInput = keyboard.nextLine();
								System.out.println("To which account?");
								System.out.println("[1] Checking");
								System.out.println("[2] Savings");
								System.out.println("[3] Credit");
								uInput2 = keyboard.nextLine();
								if(uInput == uInput2) {
									System.out.println("You cannot transfer to the same account.");
									break;
								}
								else if(uInput != null && uInput2 != null) {
									System.out.println("How much would you like to transfer?");
										uAmount = keyboard.nextDouble();
										validUser.getAccountsList()[(Integer.parseInt(uInput)-1)].transfer(validUser.getAccountsList()[(Integer.parseInt(uInput2)-1)], uAmount);
										System.out.println("Thank you! " + validUser.getAccountsList()[Integer.parseInt(uInput)-1].toString());
										break;
										}
								else {
									System.out.println("I don't understand, please try again.");
									break;
									}
							case "P":
								System.out.println("From which account?");
								System.out.println("[1] Checking");
								System.out.println("[2] Savings");
								System.out.println("[3] Credit");
								uInput = keyboard.nextLine();
								System.out.println("Who are you paying?");
								uInput2 = keyboard.nextLine().toUpperCase().trim();
								System.out.println("How much are you paying?");
								uAmount = keyboard.nextDouble();
								if(uInput != null && uInput2 != null) {
									payee = findUserByName(uInput2);
									validUser.getAccountsList()[(Integer.parseInt(uInput)-1)].transfer(payee.getAccountsList()[0], uAmount);
									break;
							}
						}
					}
					else {
						System.out.println("That's not a valid account number. Restarting function selection.");
						break;
					}
					}
					//Finish case M, give ability to look at everyone by Number or Name
				case "M":
					System.out.println("Welcome Manager! What would you like to do?");
					System.out.println("View Account by name/[T]ype?");
					System.out.println("View Account by [N]umber?");
					System.out.println("[G]enerate a statement?");
					System.out.println("[S]tate of the bank?");
					uInput = keyboard.nextLine();
					if(uInput != null) {
						switch(uInput.substring(0, 1)) {
						case "T":
							System.out.println("Whose account would you like to view today?");
							uInput = keyboard.nextLine().toUpperCase().trim();
							System.out.println("Okay! And which account of theirs would you like to view?");
							System.out.println("[1] Checking");
							System.out.println("[2] Savings");
							System.out.println("[3] Credit");
							uInput2 = keyboard.nextLine().trim();
							if(uInput2.startsWith("1")) {
								System.out.println("Viewing the Checking account of " + uInput);
								payee = findUserByName(uInput);
								payee.getAccountsList()[0].toString();
								break;
							}
							else if(uInput2.startsWith("2")) {
								System.out.println("Viewing the Savings account of " + uInput);
								payee = findUserByName(uInput);
								payee.getAccountsList()[1].toString();
								break;
							}
							else if(uInput2.startsWith("3")) {
								System.out.println("Viewing the Credit account of " + uInput);
								payee = findUserByName(uInput);
								payee.getAccountsList()[2].toString();
								break;
							}
							else {
								System.out.println("Account not found.");
								break;
							}
						case "N":
							System.out.println("What is the number of the account you're looking for?");
							identNum = keyboard.nextInt();
							if(identNum > 999 && identNum < 2000) {
								System.out.println("You're looking for a checking account! Just one moment and I'll have that for you.");
								payee = findUserByAccountNumber(identNum);
								payee.getAccountsList()[0].toString();
								break;
							}
							else if(identNum > 1999 && identNum < 3000) {
								System.out.println("One savings account, coming right up.");
								payee = findUserByAccountNumber(identNum);
								payee.getAccountsList()[1].toString();
								break;
							}
							else if(identNum > 2999 && identNum < 4000) {
								System.out.println("A credit account, eh? Don't be a predator.");
								payee = findUserByAccountNumber(identNum);
								payee.getAccountsList()[2].toString();
								break;
							}
							else {
								System.out.println("I'm sorry, that's not a valid account number. Please try again.");
								break;
							}
							
						case "G":
							System.out.println("Okay, who are we making a statement for?");
							uInput = keyboard.nextLine().toUpperCase();
							System.out.println("Stand by, creating statement.");
							validUser = findUserByName(uInput);
							
							
						case "S":
						
						}
					}
					//case N
				case "N":
					//Give new user prompt, maybe a whole ass method?
				}
			}
		}
	}

	private static User findUserByAccountNumber(int identNum) {
		// TODO Auto-generated method stub
		return null;
	}

	private static User findUserByName(String uInput2) {
		// TODO Auto-generated method stub
		return null;
	}

	private static User getUserByID(int identNum) {
		// TODO Auto-generated method stub
		User returner = new User();
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).getIdentNumber() == identNum) {
				returner = users.get(i);
				System.out.println("Match Found! Loading account:");
			}
			else {
				System.out.println("User Account not found:");
			}
		}
		
		
		return returner;
	}

	private static boolean isValidID(int identNum) {
		// TODO Auto-generated method stub
		boolean returner = false;
		for(int i = 0; i < users.size(); i++) {
			if(identNum == users.get(i).getIdentNumber()) {
				returner = true;
				System.out.println("Account Found:");
			}
			else {
				System.out.println("Account not Found:");
			}
		}
		return returner;
	}

	private static User userPuller(String lineHolder) {
		// Creates users and accounts out of the string given
		
		//Credit account info
		double creditMax;
		double creditStartingBalance;
		int creditNumber;
		
		//checking account info
		int checkingNumber;
		double checkingBalance;
		
		//savings account info
		int savingsNumber;
		double savingsBalance;
		
		//user info
		String lastName;
		String address;
		int identNumber;
		String dateOfBirth;
		String firstName;
		String phoneNumber;
		
		//Begin extraction
		creditMax = Double.parseDouble(lineHolder.substring(0, lineHolder.indexOf(",")));
		if(DB)System.out.println("Extracted credit max is "+ creditMax);
		lineHolder = lineHolder.substring(lineHolder.indexOf(",")+1);
		creditStartingBalance = Double.parseDouble(lineHolder.substring(0, lineHolder.indexOf(",")));
		if(DB)System.out.println("Extracted credit balance is "+ creditStartingBalance);
		lineHolder = lineHolder.substring(lineHolder.indexOf(",")+2);
		address = lineHolder.substring(0, lineHolder.indexOf("\""));
		if(DB)System.out.println("Extracted address is "+ address);
		lineHolder = lineHolder.substring(lineHolder.indexOf("\"")+2);
		identNumber = Integer.parseInt(lineHolder.substring(0, lineHolder.indexOf(",")));
		if(DB)System.out.println("Extracted identification number is " + identNumber);
		lineHolder = lineHolder.substring(lineHolder.indexOf(",")+1);
		savingsNumber = Integer.parseInt(lineHolder.substring(0, lineHolder.indexOf(",")));
		if(DB)System.out.println("Extracted savings number is " + identNumber);
		lineHolder = lineHolder.substring(lineHolder.indexOf(",")+1);
		lastName = lineHolder.substring(0, lineHolder.indexOf(","));
		if(DB)System.out.println("Extracted last name is " + lastName);
		lineHolder = lineHolder.substring(lineHolder.indexOf(",")+2);
		dateOfBirth = lineHolder.substring(0, lineHolder.indexOf("\""));
		if(DB)System.out.println("Extracted date of birth is " + dateOfBirth);
		lineHolder = lineHolder.substring(lineHolder.indexOf("\"")+2);
		checkingNumber = Integer.parseInt(lineHolder.substring(0, lineHolder.indexOf(",")));
		if(DB)System.out.println("Extracted checking number is " + checkingNumber);
		lineHolder = lineHolder.substring(lineHolder.indexOf(",")+1);
		firstName = lineHolder.substring(0, lineHolder.indexOf(","));
		if(DB)System.out.println("Extracted first name is " + firstName);
		lineHolder = lineHolder.substring(lineHolder.indexOf(",")+1);
		creditNumber = Integer.parseInt(lineHolder.substring(0, lineHolder.indexOf(",")));
		if(DB)System.out.println("Extracted credit number is " + creditNumber);
		lineHolder = lineHolder.substring(lineHolder.indexOf(",")+1);
		phoneNumber = lineHolder.substring(0, lineHolder.indexOf(","));
		if(DB)System.out.println("Extracted phone number is " + phoneNumber);
		lineHolder = lineHolder.substring(lineHolder.indexOf(",")+1);
		checkingBalance = Double.parseDouble(lineHolder.substring(0, lineHolder.indexOf(",")));
		if(DB)System.out.println("Extracted checking balance is " + checkingBalance);
		lineHolder = lineHolder.substring(lineHolder.indexOf(",")+1);
		savingsBalance = Double.parseDouble(lineHolder.substring(0));
		if(DB)System.out.println("Extracted savings balance is " + savingsBalance);
		
		//Use extracted information to form users and accounts
		String name = lastName + ", " + firstName;
		Account[] accountList = new Account[4];
		accountList[0] = new Savings(savingsNumber, savingsBalance);
		accountList[1] = new Checking(checkingNumber, checkingBalance);
		accountList[2] = new Credit(creditNumber, creditMax, creditStartingBalance);
		
		User temparino = new User(name, dateOfBirth, address, phoneNumber, identNumber, accountList);
		if(DB)System.out.println(temparino.getName());
		return temparino;
	}

}
