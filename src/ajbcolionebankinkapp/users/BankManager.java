package ajbcolionebankinkapp.users;

import java.time.LocalDate;

public class BankManager extends AccountOwner {
	protected final int NUM_OF_USERS = 100;
	AccountOwner[] usersToApprove = new AccountOwner[NUM_OF_USERS];

	public BankManager(String firstName, String lastName, PhoneNumber phoneNumber, LocalDate birthDate,
			Cerdetianls cerdetianls) {
		super(firstName, lastName, phoneNumber, birthDate, cerdetianls);
		// TODO Auto-generated constructor stub
	}

	public void addUserToApprove(AccountOwner accountOwnerToApprove) {
		for (int i = 0; i < usersToApprove.length; i++) {
			if (usersToApprove[i] == null) {
				usersToApprove[i] = accountOwnerToApprove;
				break;
			}
		}
	}

}
