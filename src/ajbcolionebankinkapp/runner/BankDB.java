package ajbcolionebankinkapp.runner;

import java.time.LocalDate;

import ajbcolionebankinkapp.users.AccountOwner;
import ajbcolionebankinkapp.users.Cerdetianls;
import ajbcolionebankinkapp.users.PhoneNumber;

public class BankDB {
	

	protected Cerdetianls cerdetianlsAcc1 = new Cerdetianls("Bambi", "Dana");
	public 	AccountOwner acOwner1 = new AccountOwner("Dana", "Grosman", new PhoneNumber(52, 6998773),
			LocalDate.of(1993, 1, 29), cerdetianlsAcc1);
}
