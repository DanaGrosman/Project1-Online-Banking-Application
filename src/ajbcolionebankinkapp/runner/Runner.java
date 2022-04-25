package ajbcolionebankinkapp.runner;

import ajbcolionebankinkapp.users.PhoneNumber;

public class Runner {

	public static void main(String[] args) {
		BankDB bankDB = new BankDB();
		(bankDB.bankManager).addUserToApprove(bankDB.acOwner1);
		
		boolean isLoginByUsernameAndPass = (bankDB.appManager).login("Bambi", "Dana");
		boolean isLoginByPhonenumber = (bankDB.appManager).login(new PhoneNumber(052, 6998773));

		(bankDB.appManager).openAccount();
	}

}
