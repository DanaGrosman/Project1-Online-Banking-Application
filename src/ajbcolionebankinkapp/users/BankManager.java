package ajbcolionebankinkapp.users;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

import ajbcolionebankinkapp.account.Account;
import ajbcolionebankinkapp.appmanager.AppManager;
import ajbcolionebankinkapp.enumaretion.AccountProperties;
import ajbcolionebankinkapp.enumaretion.ActivityName;

public class BankManager extends AccountOwner {
	protected final int NUM_OF_USERS = 100;
	protected AccountOwner[] usersToApprove = new AccountOwner[NUM_OF_USERS];

	public BankManager(String firstName, String lastName, PhoneNumber phoneNumber, LocalDate birthDate,
			Cerdetianls cerdetianls) {
		super(firstName, lastName, phoneNumber, birthDate, cerdetianls);
		setAccount(new Account());
		getAccount().setAccountProperties(AccountProperties.TITANIUM);
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
							AccountProperties accountProperties = randAccountProperties();
							AppManager.users[j].getAccount().setAccountProperties(accountProperties);
							AppManager.users[j].getAccount().addActivityData(ActivityName.OPEN_ACCOUNT, 0,
									LocalDateTime.now(), "Open account - approved by bank manager");
							usersToApprove[i] = null;
							return;
						}
					}
			}
		}
	}

	private AccountProperties randAccountProperties() {
		AccountProperties accountProperties = null;
		Random rand = new Random();
		int randType = rand.nextInt(4);
		switch (randType) {
		case 0: {
			accountProperties = AccountProperties.BRONZE;
			break;
		}
		case 1: {
			accountProperties = AccountProperties.GOLD;
			break;
		}
		case 2: {
			accountProperties = AccountProperties.SILVER;
			break;
		}
		case 3: {
			accountProperties = AccountProperties.TITANIUM;
			break;
		}
		}
		return accountProperties;
	}

}
