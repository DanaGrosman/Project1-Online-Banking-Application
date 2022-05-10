package ajbcolionebankinkapp.runner;

import java.time.LocalDate;

import ajbcolionebankinkapp.appmanager.AppManager;
import ajbcolionebankinkapp.users.AccountOwner;
import ajbcolionebankinkapp.users.BankManager;
import ajbcolionebankinkapp.users.Cerdetianls;
import ajbcolionebankinkapp.users.PhoneNumber;

public class BankDB {

	// Account Owners
	protected Cerdetianls cerdetianlsAcc1 = new Cerdetianls("Bambi", "Dana");
	public AccountOwner acOwner1 = new AccountOwner("Dana", "Grosman", new PhoneNumber(52, 6998773),
			LocalDate.of(1993, 1, 29), cerdetianlsAcc1);
	
	// Bank Manager
	public BankManager bankManager = new BankManager("Talya", "Grosman", new PhoneNumber(52, 6998772),
			LocalDate.of(2021, 3, 6), new Cerdetianls("Talya", "1234"));

	// App Manager
	public AppManager appManager = new AppManager(bankManager);

	public void initDB() {
		bankManager.addUserToApprove(acOwner1);
		appManager.addAccountOwnerToArray(acOwner1);
		appManager.addAccountOwnerToArray(bankManager);
		bankManager.setAndApproveAcc();		
	}

}
