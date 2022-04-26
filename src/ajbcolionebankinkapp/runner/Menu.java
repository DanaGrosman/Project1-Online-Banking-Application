package ajbcolionebankinkapp.runner;

public abstract class Menu {
	// ACTIVITIES
	public final static String CHECK_BALANCE = "Check balance";
	public final static String DEPOSIT_CASH = "Deposit cash";
	public final static String DEPOSIT_CHECK = "Deposit check";
	public final static String WITHDRAWAL_CASH = "Withdrawal of cash";
	public final static String PAY_BILL = "Pay bill";
	public final static String TRANSFER_FUNDS = "Transfer funds";
	public final static String GET_LOAN = "Get Loan";
	public final static String GET_REPORT = "Get report";
	public final static String OPEN_ACCOUNT = "Open account";
	public final static String EXIT = "Exit";

	// MENU
	public final static String MENU_START = "Welcome to our banking app!";
	public final static String SELECTION_LOGIN_OR_OPEN_ACCOUNT = "Please enter you selection: ";
	public final static String SELECTION_ACTIVITY = "What action would you like to take?: ";

	// LOGIN AND OUT
	public final static String LOGIN = "Login";
	public final static String LOGOUT = "Logout";
	public final static String USERNAME = "Username";
	public final static String PASSWORD = "Password";
	public final static String LOGIN_WITH_USERNAME_AND_PASSWORD = "Login in with username and password";
	public final static String LOGIN_WITH_PHONENUMBER = "Login in phonenumber";
	public final static String USER_IS_BLOCKED = "The account is blockted for 30 minutes";

	// OPEN ACCOUNT
	public final static String FIRST_NAME = "First name";
	public final static String LAST_NAME = "Last name";
	public final static String PHONENUMBER = "Phonenumber";
	public final static String BIRTHDATE = "Birth date";
	public final static String MONTHLY_INCOME = "Monthly income";
	public final static String FINISH_REGISTER_MESSAGE = "The registration was successful, now your request has been forwarded to the bank manager :)";

	// BANK MANAGER MENU
	public final static String APPROVE_NEW_ACCOUNTS = "Approve new accounts";

	// PAY BILL MENU
	public final static String BANK = "BANK - Loan return";
	public final static String PHONE_COMPANY = "Phone company";
	public final static String WATER_COMPANY = "Water company";
	public final static String ELECTRIC_COMPANY = "Electric company";

	
	public static void printMenu() {
		System.out.println(SELECTION_ACTIVITY);
		System.out.println("1 - " + CHECK_BALANCE);
		System.out.println("2 - " + DEPOSIT_CASH);
		System.out.println("3 - " + DEPOSIT_CHECK);
		System.out.println("4 - " + WITHDRAWAL_CASH);
		System.out.println("5 - " + TRANSFER_FUNDS);
		System.out.println("6 - " + PAY_BILL);
		System.out.println("7 - " + GET_LOAN);
		System.out.println("8 - " + GET_REPORT);
		System.out.println("9 - " + LOGOUT);
		System.out.println("0 - " + EXIT);
	}

	public static void printLogin() {
		System.out.println(MENU_START);
		System.out.println(SELECTION_LOGIN_OR_OPEN_ACCOUNT);
		System.out.println("1 - " + LOGIN);
		System.out.println("2 - " + OPEN_ACCOUNT);
	}

	public static void printLoginOptions() {
		System.out.println("1 - " + LOGIN_WITH_USERNAME_AND_PASSWORD);
		System.out.println("2 - " + LOGIN_WITH_PHONENUMBER);
	}
	
	public static void printBankManagerMenu() {
		System.out.println("10 - " + APPROVE_NEW_ACCOUNTS);
	}
	
	public static void printPayBillOptions() {
		System.out.println("1 - " + BANK);
		System.out.println("2 - " + PHONE_COMPANY);
		System.out.println("3 - " + WATER_COMPANY);
		System.out.println("4 - " + ELECTRIC_COMPANY);
	}
	
}
