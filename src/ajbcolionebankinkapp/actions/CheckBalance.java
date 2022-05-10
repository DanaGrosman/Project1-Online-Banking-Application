package ajbcolionebankinkapp.actions;

import ajbcolionebankinkapp.users.AccountOwner;

public class CheckBalance implements Action {
	AccountOwner accountOwner;

	public CheckBalance(AccountOwner accountOwner) {
		this.accountOwner = accountOwner;
	}

	@Override
	public void run() {
		System.out.println("Balance: " + accountOwner.checkBalance());

	}

}
