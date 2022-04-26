package ajbcolionebankinkapp.appmanager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import ajbcolionebankinkapp.users.AccountOwner;
import ajbcolionebankinkapp.users.BankManager;
import ajbcolionebankinkapp.users.Cerdetianls;
import ajbcolionebankinkapp.users.PhoneNumber;

public class AppManager {
	protected final static int NUM_OF_USERS = 100;
	protected static int nextIndexAvaliableInUsersArray = 0;
	protected Scanner scanner = new Scanner(System.in);
	protected AccountOwner currUser;
	public static AccountOwner[] users = new AccountOwner[NUM_OF_USERS];
	protected BankManager bankManager;

	public AppManager(BankManager bankManager) {
		this.currUser = null;
		this.bankManager = bankManager;
	}

	public boolean login(String username, String password) {
		Cerdetianls cerdetianlsToCheck = new Cerdetianls(username, password);
		for (int i = 0; i < nextIndexAvaliableInUsersArray; i++) {
			if (cerdetianlsToCheck.equals(users[i].getCerdetianls())) {
				currUser = users[i];
				return true;
			}
		}
		return false;
	}

	public boolean login(PhoneNumber phoneNumberToCheck) {
		AccountOwner accountOwner = getOwnerByPhoneNumber(phoneNumberToCheck);
		if (accountOwner != null) {
			currUser = accountOwner;
			return true;
		}
		return false;
	}

	private AccountOwner getOwnerByPhoneNumber(PhoneNumber phoneNumberToCheck) {
		AccountOwner accountOwner = null;
		for (int i = 0; i < nextIndexAvaliableInUsersArray; i++) {
			if (phoneNumberToCheck.equals(users[i].getPhoneNumber())) {
				accountOwner = users[i];
			}
		}
		return accountOwner;
	}

	public void logout() {
		currUser = null;
	}

	public void openAccount() {
		System.out.println("First name: ");
		String firstName = scanner.nextLine();
		System.out.println("Last name: ");
		String lastName = scanner.nextLine();
		System.out.println("Phonenumber (10 digits): ");
		String phone = scanner.nextLine();
		System.out.println("Birthday (yyyy/MM/dd format): ");
		String birthday = scanner.nextLine();

		firstName = handleName(firstName);
		lastName = handleName(lastName);

		LocalDate birthDate = handleBirthDate(birthday);
		PhoneNumber phoneNumber = handlePhoneNumber(phone);

		System.out.println("Username: ");
		String username = scanner.nextLine();
		System.out.println("Password: ");
		String password = scanner.nextLine();

		Cerdetianls cerdetianls = handleCerdetianls(username, password);

		System.out.println("Monthly income: ");
		double monthlyIncome = scanner.nextDouble();
		monthlyIncome = handleMonthlyIncome(monthlyIncome);

		AccountOwner accountOwnerToApprove = new AccountOwner(firstName, lastName, phoneNumber, birthDate, cerdetianls);
		accountOwnerToApprove.setMonthlyIncome(monthlyIncome);

		bankManager.addUserToApprove(accountOwnerToApprove);
		users[nextIndexAvaliableInUsersArray] = accountOwnerToApprove;
	}

	private double handleMonthlyIncome(double monthlyIncome) {
		while (monthlyIncome < 0) {
			System.out.println("Monthly income must be >= 0. Please try again: ");
			monthlyIncome = scanner.nextDouble();
		}

		return monthlyIncome;
	}

	private String handleName(String name) {
		return name.replaceAll("\\d", "");
	}

	private Cerdetianls handleCerdetianls(String username, String password) {
		String errUsername = checkUsername(username);
		while (errUsername != "") {
			System.out.println(errUsername);
			username = scanner.nextLine();

			errUsername = checkUsername(username);
		}

		while (!password.matches("^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]{4,8}$")) {
			System.out.println(
					"Password must have 4-8 chars and contains at least one digit and one letter. \nPlease enter new password: ");
			password = scanner.nextLine();
		}

		return new Cerdetianls(username, password);
	}

	private String checkUsername(String username) {
		if (!username.matches("[a-zA-Z0-9]*"))
			return "Username must contains lettesr and digit only. \nPlease try another username: ";
		else if (!checkIfUsernameIsAvailable(username))
			return "Username is not available. \nPlease try another username: ";
		return "";
	}

	private boolean checkIfUsernameIsAvailable(String username) {
		for (int i = 0; i < nextIndexAvaliableInUsersArray; i++) {
			if (username == users[i].getCerdetianls().getUsername())
				return false;
		}
		return true;
	}

	private PhoneNumber handlePhoneNumber(String phone) {
		boolean phoneIsExists = true;
		PhoneNumber phoneNumber = new PhoneNumber(Integer.parseInt(phone.substring(0, 3)),
				Integer.parseInt(phone.substring(3, 10)));
		phoneIsExists = checkIfPhoneNumberIsAlreadyExists(phoneNumber);

		while (phoneIsExists) {
			System.out.println("PhoneNuber is already exists. \nPlease enter another phonenumber: ");
			phone = scanner.nextLine();
			phoneNumber = new PhoneNumber(Integer.parseInt(phone.substring(0, 3)),
					Integer.parseInt(phone.substring(3, 10)));
			phoneIsExists = checkIfPhoneNumberIsAlreadyExists(phoneNumber);
		}

		return phoneNumber;
	}

	private LocalDate handleBirthDate(String birthday) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		return LocalDate.parse(birthday, formatter);
	}

	private boolean checkIfPhoneNumberIsAlreadyExists(PhoneNumber phoneNumber) {
		return (getOwnerByPhoneNumber(phoneNumber) == null) ? false : true;
	}

	public void addAccountOwnerToArray(AccountOwner accountOwner) {
		users[nextIndexAvaliableInUsersArray] = accountOwner;
		nextIndexAvaliableInUsersArray++;
	}

	public AccountOwner[] getUsers() {
		return users;
	}
}
