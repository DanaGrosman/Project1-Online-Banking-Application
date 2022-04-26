package ajbcolionebankinkapp.runner;

import java.time.LocalDateTime;
import java.util.Scanner;

import ajbcolionebankinkapp.enumaretion.ActivityName;
import ajbcolionebankinkapp.users.AccountOwner;
import ajbcolionebankinkapp.users.PhoneNumber;

public class Runner {
	protected static Scanner scanner = new Scanner(System.in);
	protected static BankDB bankDB = new BankDB();
	protected static int selection = -1;
	protected static final int MAX_AMOUNT_TO_TRANSFER = 2000;
	protected static final int MAX_AMOUNT_TO_PAY_BILL = 5000;
	protected static final int MAX_MONTHLY_PAYMENT = 6;

	public static void main(String[] args) {
		String username, password, phonenumber;
		int attempts = 0;
		boolean login = false;

		bankDB.bankManager.addUserToApprove(bankDB.acOwner1);
		bankDB.appManager.addAccountOwnerToArray(bankDB.acOwner1);
		bankDB.appManager.addAccountOwnerToArray(bankDB.bankManager);
		bankDB.bankManager.setAndApproveAcc();

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
				System.out.println(Menu.FINISH_REGISTER_MESSAGE);
				break;
			}
			}
			Menu.printLogin();
			selection = scanner.nextInt();
		}
	}

	private static void handleMenu() {
		Menu.printMenu();
		if (bankDB.appManager.getCurrUser().equals(bankDB.bankManager))
			Menu.printBankManagerMenu();

		selection = scanner.nextInt();
		while (selection != 0) {
			switch (selection) {
			case 1: { // CHECK_BALANCE
				handleCheckBalance();
				break;
			}
			case 2: // DEPOSIT_CASH
			case 3: { // DEPOSIT_CHECK
				handleDesposit();
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
				bankDB.appManager.logout();
				return;
			}
			case 0: { // EXIT
				break;
			}
			case 10: { // APPROVE_NEW_ACCOUNTS
				bankDB.bankManager.setAndApproveAcc();
				System.out.println("Approval new accounts successfully");
				break;
			}
			}
			Menu.printMenu();
			if (bankDB.appManager.getCurrUser().equals(bankDB.bankManager))
				Menu.printBankManagerMenu();
			selection = scanner.nextInt();
		}
	}

	private static void handleGetLoan() {
		String activityInfo = "";

		System.out.println("Loan amount: ");
		double amount = scanner.nextDouble();
		System.out.println("Monthly payments: ");
		int monthlyPayments = scanner.nextInt();

		double maxLoanAmount = bankDB.appManager.getCurrUser().getAccount().getAccountProperties().getMaxLoan();
		if (amount > maxLoanAmount && maxLoanAmount != 0) {
			activityInfo = "Failed to get loan - over the maximum amount";
		} else if (monthlyPayments > MAX_MONTHLY_PAYMENT) {
			activityInfo = "Failed to get loan - over the maximum monthly payment";
		} else {
			double interestRate = bankDB.appManager.getCurrUser().getAccount().getAccountProperties()
					.getInterestRateMin();
			double monthlyPaymentReturn = ((amount * (interestRate / 100)) + amount) / monthlyPayments;
			System.out.println("Monthly payment return: " + monthlyPaymentReturn);
			bankDB.bankManager.withdrawal(amount);
			bankDB.appManager.getCurrUser().deposit(amount);
			activityInfo = "Get loan succeeded";
		}
		System.out.println(activityInfo);
		bankDB.appManager.getCurrUser().getAccount().addActivityData(ActivityName.TRANSFER, amount, LocalDateTime.now(),
				activityInfo);
	}

	private static void handleGetReport() {
		bankDB.appManager.getCurrUser().getAccount().printHistory();
	}

	private static void handleCheckBalance() {
		System.out.println("Balance: " + bankDB.appManager.getCurrUser().checkBalance());
	}

	private static void handleWithdrawal() {
		String activityInfo = "";
		System.out.println("Amount: ");
		double amount = scanner.nextDouble();

		if (amount <= bankDB.appManager.getCurrUser().getAccount().getAccountProperties().getMaxWithdrawal()) {
			bankDB.appManager.getCurrUser().withdrawal(amount);
			System.out.println("Withdrawal succeeded!");
			System.out.println("Your new balance: " + bankDB.appManager.getCurrUser().checkBalance());
			activityInfo = "Withdrawal succeeded";

		} else {
			activityInfo = "Withdrawal failed - over the daily maximum withdrawal";
		}
		bankDB.appManager.getCurrUser().getAccount().addActivityData(ActivityName.WITHDRAWAL, amount,
				LocalDateTime.now(), activityInfo);
	}

	private static void handleDesposit() {
		System.out.println("Amount: ");
		double amount = scanner.nextDouble();
		bankDB.appManager.getCurrUser().deposit(amount);
		System.out.println("Deposit succeeded!");
		System.out.println("Your new balance: " + bankDB.appManager.getCurrUser().checkBalance());
		bankDB.appManager.getCurrUser().getAccount().addActivityData(ActivityName.DEPOSIT, amount, LocalDateTime.now(),
				"Succeeded");
	}

	private static void handlePayBill() {
		String target = "";

		Menu.printPayBillOptions();
		int selection = scanner.nextInt();

		System.out.println("Amount: ");
		double amount = scanner.nextDouble();

		if (amount > MAX_AMOUNT_TO_PAY_BILL)
			return;

		switch (selection) {
		case 1: { // BANK
			bankDB.bankManager.deposit(amount);
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

		bankDB.appManager.getCurrUser().withdrawal(amount);
		System.out.printf("Pay bill to %s succeeded!\n", target);
		System.out.println("Balance: " + bankDB.appManager.getCurrUser().checkBalance());
		bankDB.appManager.getCurrUser().getAccount().addActivityData(ActivityName.PAY_BILL, amount, LocalDateTime.now(),
				"Pay bill to " + target + " succeeded");
	}

	private static void handleTransferFunds() {
		String activityInfo = "";
		String output = "";

		System.out.println("Reciving user phone number: ");
		String phone = scanner.next();
		PhoneNumber phoneNumber = bankDB.appManager.parseStringToPhonenumber(phone);

		System.out.println("Amount: ");
		double amount = scanner.nextDouble();

		if (amount > MAX_AMOUNT_TO_TRANSFER) {
			output = "The maximum amount that can be transferred is " + MAX_AMOUNT_TO_TRANSFER;
			activityInfo = "Failed to transfer funds - over the maximum amount";
		}

		AccountOwner accountOwnerTarget = bankDB.appManager.getOwnerByPhoneNumber(phoneNumber);
		if (accountOwnerTarget == null) {
			output = "The phone number dosent found";
			activityInfo = "Failed to transfer funds - Phone number dosen't found";
		} else {
			bankDB.appManager.getCurrUser().withdrawal(amount);
			accountOwnerTarget.deposit(amount);
			output = "Transfer succeeded!";
			System.out.println("Your new balance: " + bankDB.appManager.getCurrUser().checkBalance());
			activityInfo = "Transfer funds to " + phone + " succeeded";
		}

		System.out.println(output);
		bankDB.appManager.getCurrUser().getAccount().addActivityData(ActivityName.TRANSFER, amount, LocalDateTime.now(),
				activityInfo);
	}
}
