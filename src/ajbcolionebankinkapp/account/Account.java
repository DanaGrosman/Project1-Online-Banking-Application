package ajbcolionebankinkapp.account;

import ajbcolionebankinkapp.enumaretion.AccountProperties;

public class Account {
	protected double balance;
	protected AccountProperties accountProperties;

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
