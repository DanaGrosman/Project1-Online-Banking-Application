package ajbcolionebankinkapp.users;

import java.time.LocalDate;

import ajbcolionebankinkapp.account.Account;

public class AccountOwner extends Person {
	protected Account account;
	protected Cerdetianls cerdetianls;
	// protected double monthlyIncome;

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

}
