package ajbcolionebankinkapp.runner;

import java.time.LocalDateTime;
import java.util.Scanner;

import ajbcolionebankinkapp.users.AccountOwner;
import ajbcolionebankinkapp.users.PhoneNumber;

public class Runner {
	protected static Scanner scanner = new Scanner(System.in);
	protected static BankDB bankDB = new BankDB();
	protected static int selection = -1;
	protected static final int MAX_AMOUNT_TO_TRANSFER = 2000;

	public static void main(String[] args) {
		String username, password, phonenumber;
		int attempts = 0;
		boolean login = false;

		bankDB.bankManager.addUserToApprove(bankDB.acOwner1);
		bankDB.appManager.addAccountOwnerToArray(bankDB.acOwner1);
		bankDB.bankManager.setAndApproveAcc();

//		boolean isLoginByUsernameAndPass = (bankDB.appManager).login("Bambi", "Dana");
//		boolean isLoginByPhonenumber = (bankDB.appManager).login(new PhoneNumber(052, 6998773));
//
		// OPEN ACCOUNT
//		(bankDB.appManager).openAccount();
//		
//		// Deposit 
//		bankDB.acOwner1.deposit(500);

		Menu.printLogin();
		selection = scanner.nextInt();

		while (selection != 0) {
			switch (selection) {
			case 1: { // LOGIN
				Menu.printLoginOptions();
				selection = scanner.nextInt();
				switch (selection) {
				case 1: { // LOGIN_WITH_USERNAME_AND_PASSWORD
					while (attempts < 3 && !login) {
						System.out.println(Menu.USERNAME);
						username = scanner.next();
						System.out.println(Menu.PASSWORD);
						password = scanner.next();
						if (bankDB.appManager.login(username, password)) {
							login = true;
							handleMenu();
						} else
							attempts++;
					}
					if (attempts == 3 && !login) {
						LocalDateTime localDateTime = LocalDateTime.now();
						localDateTime.plusMinutes(30);
						System.out.println(Menu.USER_IS_BLOCKED);
						System.out.println("The account will be unblocked on: " + localDateTime.toString());
					}
					break;
				}
				case 2: { // LOGIN_WITH_PHONENUMBER
					System.out.println(Menu.PHONENUMBER);
					phonenumber = scanner.next();
					if (bankDB.appManager.login(bankDB.appManager.parseStringToPhonenumber(phonenumber))) {
						handleMenu();
					}
					break;
				}
				}
				break;
			}
			case 2: { // OPEN ACCOUNT
				bankDB.appManager.openAccount();
				handleMenu();
				break;
			}
			}
			Menu.printLogin();
			selection = scanner.nextInt();
		}
	}

	private static void handleMenu() {
		double amount;

		Menu.printMenu();
		selection = scanner.nextInt();
		while (selection != 0) {
			switch (selection) {
			case 1: { // CHECK_BALANCE
				System.out.println("Balance: " + bankDB.appManager.currUser.checkBalance());
				break;
			}
			case 2: // DEPOSIT_CASH
			case 3: { // DEPOSIT_CHECK
				System.out.println("Amount: ");
				amount = scanner.nextDouble();
				bankDB.appManager.currUser.deposit(amount);
				System.out.println("Deposit succeeded!");
				System.out.println("Your new balance: " + bankDB.appManager.currUser.checkBalance());
				break;
			}
			case 4: { // WITHDRAWAL_CASH
				System.out.println("Amount: ");
				amount = scanner.nextDouble();
				bankDB.appManager.currUser.withdrawal(amount);
				System.out.println("Withdrawal succeeded!");
				System.out.println("Your new balance: " + bankDB.appManager.currUser.checkBalance());
				break;
			}
			case 5: { // TRANSFER_FUNDS
				transferFunds();
				break;
			}
			case 6: { // PAY_BILL

				break;
			}
			case 7: { // WITHDRAWAL_FEE_COLLECTION
				break;
			}
			case 8: { // LOGOUT
				bankDB.appManager.logout();
				return;
			}
			case 0: { // EXIT
				break;
			}
			}
			Menu.printMenu();
			selection = scanner.nextInt();
		}
	}

	private static void transferFunds() {
		System.out.println("Reciving user phone number: ");
		String phone = scanner.next();
		PhoneNumber phoneNumber = bankDB.appManager.parseStringToPhonenumber(phone);

		System.out.println("Amount: ");
		double amount = scanner.nextDouble();
		if (amount > MAX_AMOUNT_TO_TRANSFER) {
			System.out.println("The maximum amount that can be transferred is " + MAX_AMOUNT_TO_TRANSFER);
			return;
		}

		AccountOwner accountOwnerTarget = bankDB.appManager.getOwnerByPhoneNumber(phoneNumber);
		if (accountOwnerTarget == null) {
			System.out.println("The phone number dosent found");
			return;
		} else {
			bankDB.appManager.currUser.deposit(amount);
			accountOwnerTarget.withdrawal(amount);
			System.out.println("Transfer succeeded!");
			System.out.println("Your new balance: " + bankDB.appManager.currUser.checkBalance());
		}
	}
}
