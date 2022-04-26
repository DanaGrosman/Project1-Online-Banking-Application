package ajbcolionebankinkapp.runner;

public abstract class Menu {
	// ACTIVITIES
	public final static String DEPOSIT_CASH = "Deposit cash";
	public final static String DEPOSIT_CHECK = "Deposit check";
	public final static String WITHDRAWAL_CASH = "Withdrawal of cash";
	public final static String PAY_BILL = "Pay bill";
	public final static String TRANSFER_FUNDS = "Transfer funds";
	public final static String WITHDRAWAL_FEE_COLLECTION = "Withdrawal fee collection";
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

	// OPEN ACCOUNT
	public final static String FIRST_NAME = "First name";
	public final static String LAST_NAME = "Last name";
	public final static String PHONENUMBER = "Phonenumber";
	public final static String BIRTHDATE = "Birth date";
	public final static String MONTHLY_INCOME = "Monthly income";

	public static void printMenu() {
		System.out.println(MENU_START);
		System.out.println(SELECTION_ACTIVITY);
		System.out.println("1 - " + DEPOSIT_CASH);
		System.out.println("2 - " + DEPOSIT_CHECK);
		System.out.println("3 - " + WITHDRAWAL_CASH);
		System.out.println("4 - " + PAY_BILL);
		System.out.println("5 - " + TRANSFER_FUNDS);
		System.out.println("6 - " + WITHDRAWAL_FEE_COLLECTION);
		System.out.println("7 - " + LOGOUT);
		System.out.println("0 - " + EXIT);
	}

	public static void printLogin() {
		System.out.println(MENU_START);
		System.out.println(SELECTION_LOGIN_OR_OPEN_ACCOUNT);
		System.out.println("1 - " + LOGIN);
		System.out.println("2 - " + OPEN_ACCOUNT);
	}
}
