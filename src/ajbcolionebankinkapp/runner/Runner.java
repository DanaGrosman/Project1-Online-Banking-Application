package ajbcolionebankinkapp.runner;

import java.util.Scanner;

import ajbcolionebankinkapp.users.PhoneNumber;

public class Runner {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		BankDB bankDB = new BankDB();
		int selection = -1;

		bankDB.bankManager.addUserToApprove(bankDB.acOwner1);
		bankDB.appManager.addAccountOwnerToArray(bankDB.acOwner1);
		bankDB.bankManager.setAndApproveAcc();

//		boolean isLoginByUsernameAndPass = (bankDB.appManager).login("Bambi", "Dana");
//		boolean isLoginByPhonenumber = (bankDB.appManager).login(new PhoneNumber(052, 6998773));
//
//		(bankDB.appManager).openAccount();
//		
//		// Deposit 
//		bankDB.acOwner1.deposit(500);

		while (selection != 0) {
			Menu.printLogin();
			selection = scanner.nextInt();
		}

	}

}
