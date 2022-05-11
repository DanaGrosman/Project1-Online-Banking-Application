package ajbcolionebankinkapp.appmanager;

import java.time.LocalDateTime;
import java.util.Scanner;

import ajbcolionebankinkapp.runner.Menu;
import ajbcolionebankinkapp.users.AccountOwner;
import ajbcolionebankinkapp.users.Cerdetianls;
import ajbcolionebankinkapp.users.PhoneNumber;

public class Login {
	protected static Scanner scanner = new Scanner(System.in);
	protected AppManager appManager;

	public Login(AppManager appManager) {
		this.appManager = appManager;
	}

	public boolean loginWithUsernameAndPassword() {
		int attempts = 0;
		boolean login = false;

		while (attempts < 3 && !login) {
			System.out.println(Menu.USERNAME);
			String username = scanner.next();
			System.out.println(Menu.PASSWORD);
			String password = scanner.next();

			if (login(username, password)) {
				login = true;
			} else
				attempts++;
		}

		if (attempts == 3 && !login) {
			LocalDateTime localDateTime = LocalDateTime.now();
			localDateTime.plusMinutes(30);
			System.out.println(Menu.USER_IS_BLOCKED);
			// TODO: block user
			System.out.println("The account will be unblocked on: " + localDateTime.toString());
		}

		return login;
	}

	private boolean login(String username, String password) {
		Cerdetianls cerdetianlsToCheck = new Cerdetianls(username, password);
		for (int i = 0; i < AppManager.nextIndexAvaliableInUsersArray; i++) {
			if (cerdetianlsToCheck.equals(AppManager.users[i].getCerdetianls())) {
				appManager.setCurrUser(AppManager.users[i]);
				return true;
			}
		}
		return false;
	}

	public boolean loginWithPhoneNumber() {
		System.out.println(Menu.PHONENUMBER);
		String phonenumber = scanner.next();
		
		return login(Utils.parseStringToPhonenumber(phonenumber)) ? true : false;
	}

	private boolean login(PhoneNumber phoneNumberToCheck) {
		AccountOwner accountOwner = AppManager.getOwnerByPhoneNumber(phoneNumberToCheck);
		if (accountOwner != null) {
			appManager.setCurrUser(accountOwner);
			return true;
		}
		return false;
	}
}
