package ajbcolionebankinkapp.appmanager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import ajbcolionebankinkapp.actions.CheckBalance;
import ajbcolionebankinkapp.actions.Deposit;
import ajbcolionebankinkapp.enumaretion.ActivityName;
import ajbcolionebankinkapp.runner.Menu;
import ajbcolionebankinkapp.users.AccountOwner;
import ajbcolionebankinkapp.users.BankManager;
import ajbcolionebankinkapp.users.Cerdetianls;
import ajbcolionebankinkapp.users.PhoneNumber;

public class AppManager {
	protected static final int MAX_AMOUNT_TO_TRANSFER = 2000;
	protected static final int MAX_AMOUNT_TO_PAY_BILL = 5000;
	protected static final int MAX_MONTHLY_PAYMENT = 6;
	protected static int nextIndexAvaliableInUsersArray = 0;
	protected final static int NUM_OF_USERS = 100;
	public static AccountOwner[] users = new AccountOwner[NUM_OF_USERS];
	protected static Scanner scanner = new Scanner(System.in);

	protected int selection = -1;
	protected AccountOwner currUser;
	protected BankManager bankManager;
	protected Menu menu;

	public AppManager(BankManager bankManager) {
		this.currUser = null;
		this.bankManager = bankManager;
	}

	public void runApp() {
		Menu.printLogin();
		selection = scanner.nextInt();

		while (selection != 0) {
			switch (selection) {
			case 1: { // LOGIN
				Menu.printLoginOptions();
				selection = scanner.nextInt();
				handleLogin(selection);
				break;
			}
			case 2: { // OPEN ACCOUNT
				openAccount();
				System.out.println(Menu.FINISH_REGISTER_MESSAGE);
				break;
			}
			}

			Menu.printLogin();
			selection = scanner.nextInt();
		}
	}

	private void handleLogin(int selection) {
		Login login = new Login(this);

		switch (selection) {
		case 1: { // LOGIN_WITH_USERNAME_AND_PASSWORD
			if (login.loginWithUsernameAndPassword())
				handleMenu();
			break;
		}
		case 2: { // LOGIN_WITH_PHONENUMBER
			login.loginWithPhoneNumber();
			handleMenu();
			break;
		}
		}
	}

	private void handleMenu() {
		Menu.printMenu();
		if (getCurrUser().equals(bankManager))
			Menu.printBankManagerMenu();

		selection = scanner.nextInt();
		while (selection != 0) {
			switch (selection) {
			case 1: { // CHECK_BALANCE
				(new CheckBalance(getCurrUser())).run();
				break;
			}
			case 2: // DEPOSIT_CASH
			case 3: { // DEPOSIT_CHECK
				(new Deposit(getCurrUser())).run();
				break;
			}
			case 4: { // WITHDRAWAL_CASH
				handleWithdrawal();
				break;
			}
			case 5: { // TRANSFER_FUNDS
				handleTransferFunds();
				break;
			}
			case 6: { // PAY_BILL
				handlePayBill();
				break;
			}
			case 7: { // GET_LOAN
				handleGetLoan();
				break;
			}
			case 8: { // GET_REPORT
				handleGetReport();
				break;
			}
			case 9: { // LOGOUT
				logout();
				return;
			}
			case 10: { // ACCOUNT_DETAILS
				handleAccountDetails();
				break;
			}
			case 0: { // EXIT
				break;
			}
			case 11: { // APPROVE_NEW_ACCOUNTS
				bankManager.setAndApproveAcc();
				System.out.println("Approval new accounts successfully");
				break;
			}
			}
			Menu.printMenu();
			if (getCurrUser().equals(bankManager))
				Menu.printBankManagerMenu();
			selection = scanner.nextInt();
		}
	}

	private void handleAccountDetails() {
		System.out.println(getCurrUser().toString());
		System.out.println(getCurrUser().getAccount().toString());
	}

	private void handleGetReport() {
		getCurrUser().getAccount().printHistory();
	}

	private void handleGetLoan() {
		String activityInfo = "";

		System.out.println("Loan amount: ");
		double amount = scanner.nextDouble();
		System.out.println("Monthly payments: ");
		int monthlyPayments = scanner.nextInt();

		double maxLoanAmount = getCurrUser().getAccount().getAccountProperties().getMaxLoan();
		if (amount > maxLoanAmount && maxLoanAmount != 0) {
			activityInfo = "Failed to get loan - over the maximum amount";
		} else if (monthlyPayments > MAX_MONTHLY_PAYMENT) {
			activityInfo = "Failed to get loan - over the maximum monthly payment";
		} else {
			double interestRate = getCurrUser().getAccount().getAccountProperties().getInterestRateMin();
			double monthlyPaymentReturn = ((amount * (interestRate / 100)) + amount) / monthlyPayments;
			System.out.println("Monthly payment return: " + monthlyPaymentReturn);
			bankManager.withdrawal(amount);
			getCurrUser().deposit(amount);
			getCurrUser().getAccount().addActivityData(ActivityName.DEPOSIT, amount, LocalDateTime.now(), "Get loan");
			bankManager.getAccount().addActivityData(ActivityName.WITHDRAWAL, amount, LocalDateTime.now(), "Give loan");
			activityInfo = "Get loan succeeded";
		}
		System.out.println(activityInfo);
		getCurrUser().getAccount().addActivityData(ActivityName.GET_LOAN, amount, LocalDateTime.now(), activityInfo);
	}

	private void handlePayBill() {
		String target = "";

		Menu.printPayBillOptions();
		int selection = scanner.nextInt();

		System.out.println("Amount: ");
		double amount = scanner.nextDouble();

		if (amount > MAX_AMOUNT_TO_PAY_BILL)
			return;

		switch (selection) {
		case 1: { // BANK
			bankManager.deposit(amount);
			target = "Bank";
			break;
		}
		case 2: { // PHONE_COMPANY
			target = "Phone company";
			break;
		}
		case 3: {// WATER_COMPANY
			target = "Water company";
			break;
		}
		case 4: { // ELECTRIC_COMPANY
			target = "Electric company";
			break;
		}
		}

		getCurrUser().withdrawal(amount);
		System.out.printf("Pay bill to %s succeeded!\n", target);
		System.out.println("Balance: " + getCurrUser().checkBalance());
		getCurrUser().getAccount().addActivityData(ActivityName.PAY_BILL, amount, LocalDateTime.now(),
				"Pay bill to " + target + " succeeded");
	}

	private void handleTransferFunds() {
		String activityInfo = "";
		String output = "";

		System.out.println("Reciving user phone number: ");
		String phone = scanner.next();
		PhoneNumber phoneNumber = parseStringToPhonenumber(phone);

		System.out.println("Amount: ");
		double amount = scanner.nextDouble();

		if (amount > MAX_AMOUNT_TO_TRANSFER) {
			output = "The maximum amount that can be transferred is " + MAX_AMOUNT_TO_TRANSFER;
			activityInfo = "Failed to transfer funds - over the maximum amount";
		}

		AccountOwner accountOwnerTarget = getOwnerByPhoneNumber(phoneNumber);
		if (accountOwnerTarget == null) {
			output = "The phone number dosent found";
			activityInfo = "Failed to transfer funds - Phone number dosen't found";
		} else {
			getCurrUser().withdrawal(amount);
			accountOwnerTarget.deposit(amount);
			output = "Transfer succeeded!";
			System.out.println("Your new balance: " + getCurrUser().checkBalance());
			activityInfo = "Transfer funds to " + phone + " succeeded";
		}

		System.out.println(output);
		getCurrUser().getAccount().addActivityData(ActivityName.TRANSFER, amount, LocalDateTime.now(), activityInfo);
	}

	private void handleWithdrawal() {
		String activityInfo = "";

		System.out.println("Amount: ");
		double amount = scanner.nextDouble();

		double maxWithdrawal = getCurrUser().getAccount().getAccountProperties().getMaxWithdrawal();
		if (amount <= maxWithdrawal || maxWithdrawal == 0) {
			getCurrUser().withdrawal(amount);
			System.out.println("Your new balance: " + getCurrUser().checkBalance());
			activityInfo = "Withdrawal succeeded";
		} else {
			activityInfo = "Withdrawal failed - over the daily maximum withdrawal";
		}
		System.out.println(activityInfo);
		getCurrUser().getAccount().addActivityData(ActivityName.WITHDRAWAL, amount, LocalDateTime.now(), activityInfo);
	}


	public AccountOwner getOwnerByPhoneNumber(PhoneNumber phoneNumberToCheck) {
		AccountOwner accountOwner = null;
		for (int i = 0; i < nextIndexAvaliableInUsersArray; i++) {
			if (phoneNumberToCheck.equals(users[i].getPhoneNumber())) {
				accountOwner = users[i];
			}
		}
		return accountOwner;
	}

	public AccountOwner getCurrUser() {
		return currUser;
	}

	public void setCurrUser(AccountOwner currUser) {
		this.currUser = currUser;
	}

	public void logout() {
		setCurrUser(null);
	}

	public boolean openAccount() {
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
		nextIndexAvaliableInUsersArray++;
		return true;
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
		else if (!checkIfUsernameIsAvailable(username)) {
			return "Username is not available. \nPlease try another username: ";
		}
		return "";
	}

	private boolean checkIfUsernameIsAvailable(String username) {
		for (int i = 0; i < nextIndexAvaliableInUsersArray; i++) {
			if (username.equals(users[i].getCerdetianls().getUsername()))
				return false;
		}
		return true;
	}

	private PhoneNumber handlePhoneNumber(String phone) {
		boolean phoneIsExists = true;
		PhoneNumber phoneNumber = parseStringToPhonenumber(phone);
		phoneIsExists = checkIfPhoneNumberIsAlreadyExists(phoneNumber);

		while (phoneIsExists) {
			System.out.println("PhoneNuber is already exists. \nPlease enter another phonenumber: ");
			phone = scanner.nextLine();
			phoneNumber = parseStringToPhonenumber(phone);
			phoneIsExists = checkIfPhoneNumberIsAlreadyExists(phoneNumber);
		}

		return phoneNumber;
	}

	public PhoneNumber parseStringToPhonenumber(String phone) {
		return new PhoneNumber(Integer.parseInt(phone.substring(0, 3)), Integer.parseInt(phone.substring(3, 10)));
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
