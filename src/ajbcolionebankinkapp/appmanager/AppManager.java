package ajbcolionebankinkapp.appmanager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import ajbcolionebankinkapp.users.AccountOwner;
import ajbcolionebankinkapp.users.BankManager;
import ajbcolionebankinkapp.users.Cerdetianls;
import ajbcolionebankinkapp.users.PhoneNumber;

public class AppManager {
	protected final int NUM_OF_USERS = 100;
	protected static int nextIndexAvaliableInUsersArray = 0;
	protected Scanner scanner = new Scanner(System.in);
	protected AccountOwner currUser;
	protected AccountOwner[] users = new AccountOwner[NUM_OF_USERS];
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

		LocalDate birthDate = handleBirthDate(birthday);
		PhoneNumber phoneNumber = handlePhoneNumber(phone);

		System.out.println("Username: ");
		String username = scanner.nextLine();
		System.out.println("Password: ");
		String password = scanner.nextLine();

		Cerdetianls cerdetianls = handleCerdetianls(username, password);

		AccountOwner accountOwnerToApprove = new AccountOwner(firstName, lastName, phoneNumber, birthDate, cerdetianls);

		bankManager.addUserToApprove(accountOwnerToApprove);

	}

	private Cerdetianls handleCerdetianls(String username, String password) {
		boolean isUsernameAvailable = checkIfUsernameIsAvailable(username);

		while (!isUsernameAvailable) {
			System.out.println("Username is not available. \nPlease try another username: ");
			username = scanner.nextLine();
			isUsernameAvailable = checkIfUsernameIsAvailable(username);
		}

		return new Cerdetianls(username, password);
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

}
