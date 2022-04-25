package ajbcolionebankinkapp.appmanager;

import ajbcolionebankinkapp.users.AccountOwner;
import ajbcolionebankinkapp.users.Cerdetianls;
import ajbcolionebankinkapp.users.PhoneNumber;

public class AppManager {
	protected final int NUM_OF_USERS = 100;
	protected static int nextIndexAvaliableInUsersArray = 0;
	protected AccountOwner currUser;
	protected AccountOwner[] users = new AccountOwner[NUM_OF_USERS];

	public boolean login(String username, String password) {
		Cerdetianls cerdetianlsToCheck = new Cerdetianls(username, password);
		for (int i = 0; i < users.length; i++) {
			if (cerdetianlsToCheck.equals(users[i].getCerdetianls())) {
				currUser = users[i];
				return true;
			}
		}
		return false;
	}

	public boolean login(PhoneNumber phoneNumberToCheck) {
		AccountOwner accountOwner = getOwnerByPhoneNumber(phoneNumberToCheck);
		if (accountOwner != null) {
			currUser = accountOwner;
			return true;
		}

		return false;
	}

	private AccountOwner getOwnerByPhoneNumber(PhoneNumber phoneNumberToCheck) {
		AccountOwner accountOwner = null;
		for (int i = 0; i < users.length; i++) {
			if (phoneNumberToCheck.equals(users[i].getPhoneNumber())) {
				accountOwner = users[i];
			}
		}
		return accountOwner;
	}

	public void openAccount() {

	}

	public void addAccountOwnerToArray(AccountOwner accountOwner) {
		users[nextIndexAvaliableInUsersArray] = accountOwner;
		nextIndexAvaliableInUsersArray++;
	}

}
