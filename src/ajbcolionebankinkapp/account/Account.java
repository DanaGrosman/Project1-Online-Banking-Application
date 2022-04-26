package ajbcolionebankinkapp.account;

import ajbcolionebankinkapp.enumaretion.AccountProperties;

public class Account {
	protected final static int MAX_ACTIVITY_DATA = 100;
	protected double balance;
	protected AccountProperties accountProperties;
	protected ActivityData[] history = new ActivityData[MAX_ACTIVITY_DATA];

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void setAccountProperties(AccountProperties accountProperties) {
		this.accountProperties = accountProperties;
	}

	public AccountProperties getAccountProperties() {
		return accountProperties;
	}
}
