package ajbcolionebankinkapp.users;

import java.time.LocalDate;
import java.util.Random;

import ajbcolionebankinkapp.account.Account;

public class AccountOwner extends Person {
	protected Account account;
	protected Cerdetianls cerdetianls;
	protected double monthlyIncome;
	protected int authCode;

	public AccountOwner(String firstName, String lastName, PhoneNumber phoneNumber, LocalDate birthDate,
			Cerdetianls cerdetianls) {
		super(firstName, lastName, phoneNumber, birthDate);
		setCerdetianls(cerdetianls);
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Cerdetianls getCerdetianls() {
		return cerdetianls;
	}

	public void setCerdetianls(Cerdetianls cerdetianls) {
		this.cerdetianls = cerdetianls;
	}
	
	public void setMonthlyIncome(double monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public double checkBalance() {
		return account.getBalance();
	}

	public void setAuthCode() {
		Random rand = new Random();
		authCode = rand.nextInt(10000) + 1000;
	}

	public void deposit(double amount) {
		double newBalance = account.getBalance() - amount;
		account.setBalance(newBalance);
	}
	
	public void withdrawal(double amount) {
		// TODO
	}
}
