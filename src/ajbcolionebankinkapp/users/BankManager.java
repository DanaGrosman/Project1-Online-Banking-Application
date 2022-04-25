package ajbcolionebankinkapp.users;

import java.time.LocalDate;

import ajbcolionebankinkapp.account.Account;
import ajbcolionebankinkapp.appmanager.AppManager;

public class BankManager extends AccountOwner {
	protected final int NUM_OF_USERS = 100;
	protected AccountOwner[] usersToApprove = new AccountOwner[NUM_OF_USERS];

	public BankManager(String firstName, String lastName, PhoneNumber phoneNumber, LocalDate birthDate,
			Cerdetianls cerdetianls) {
		super(firstName, lastName, phoneNumber, birthDate, cerdetianls);
	}

	public void addUserToApprove(AccountOwner accountOwnerToApprove) {
		for (int i = 0; i < usersToApprove.length; i++) {
			if (usersToApprove[i] == null) {
				usersToApprove[i] = accountOwnerToApprove;
				break;
			}
		}
	}

	public void setAndApproveAcc() {
		for (int i = 0; i < usersToApprove.length; i++) {
			if (usersToApprove[i] != null) {
				// TODO check and approve
				for (int j = 0; j < AppManager.users.length; j++)
					if (AppManager.users[j] != null) {
						if (AppManager.users[j].getCerdetianls().getUsername()
								.equals(usersToApprove[i].getCerdetianls().getUsername())) {
							AppManager.users[j].setAccount(new Account());
							AppManager.users[j].setAuthCode();
							usersToApprove[i] = null;
							return;
						}
					}
			}
		}
	}

}
