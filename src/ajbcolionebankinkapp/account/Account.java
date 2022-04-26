package ajbcolionebankinkapp.account;

import java.time.LocalDateTime;

import ajbcolionebankinkapp.enumaretion.AccountProperties;
import ajbcolionebankinkapp.enumaretion.ActivityName;

public class Account {
	protected final static int MAX_ACTIVITY_DATA = 100;
	protected double balance;
	protected AccountProperties accountProperties;
	protected ActivityData[] history = new ActivityData[MAX_ACTIVITY_DATA];
	protected int indexOfNextActivityData = 0;

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

	public void addActivityData(ActivityName activityName, double balanceChange, LocalDateTime timeStamp, String info) {
		ActivityData activityData = new ActivityData(activityName, balanceChange, timeStamp, info);
		history[indexOfNextActivityData] = activityData;
		indexOfNextActivityData++;
	}

	public ActivityData[] getHistory() {
		return history;
	}

	public void setHistory(ActivityData[] history) {
		this.history = history;
	}

	public void printHistory() {
		for (int i = 0; i < indexOfNextActivityData; i++) {
			System.out.println(history[i].toString());
		}
	}
}
