package ajbcolionebankinkapp.runner;

import java.time.LocalDate;

import ajbcolionebankinkapp.appmanager.AppManager;
import ajbcolionebankinkapp.users.AccountOwner;
import ajbcolionebankinkapp.users.Cerdetianls;
import ajbcolionebankinkapp.users.PhoneNumber;

public class Runner {

	public static void main(String[] args) {

		Cerdetianls cerdetianlsAcc1 = new Cerdetianls("Bambi", "Dana");
		AccountOwner acOwner1 = new AccountOwner("Dana", "Grosman", new PhoneNumber(52, 6998773),
				LocalDate.of(1993, 1, 29), cerdetianlsAcc1);
		
		AppManager appManager = new AppManager();
		appManager.addAccountOwnerToArray(acOwner1);
		
		boolean isLoginByUsernameAndPass = appManager.login("Bambi", "Dana");
		boolean isLoginByPhonenumber = appManager.login(new PhoneNumber(052, 6998773));

		appManager.openAccount();
	}

}
