package ajbcolionebankinkapp.users;

import java.time.LocalDate;
import java.util.Random;

import ajbcolionebankinkapp.account.Account;

public class AccountOwner extends Person {
	protected Account account;
	protected Cerdetianls cerdetianls;
	protected double monthlyIncome;

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

	public int setAuthCode() {
		Random rand = new Random();
		int authCode = rand.nextInt(10000) + 1000;
		return authCode;
	}

	public void deposit(double amount) {
		account.setBalance(account.getBalance() + amount);
	}

	public void withdrawal(double amount) {
		account.setBalance(account.getBalance() - amount);
	}

	@Override
	public String toString() {
		return "AccountOwner [monthlyIncome=" + monthlyIncome + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", birthDate=" + birthDate + "]";
	}
}
