package ajbcolionebankinkapp.actions;

import java.time.LocalDateTime;
import java.util.Scanner;

import ajbcolionebankinkapp.enumaretion.ActivityName;
import ajbcolionebankinkapp.users.AccountOwner;

public class Deposit implements Action {
	protected Scanner scanner = new Scanner(System.in);
	AccountOwner accountOwner;

	public Deposit(AccountOwner accountOwner) {
		this.accountOwner = accountOwner;
	}
	@Override
	public void run() {
		System.out.println("Amount: ");
		double amount = scanner.nextDouble();
		accountOwner.deposit(amount);
		System.out.println("Deposit succeeded!");
		System.out.println("Your new balance: " + accountOwner.checkBalance());
		accountOwner.getAccount().addActivityData(ActivityName.DEPOSIT, amount, LocalDateTime.now(), "Succeeded");
	}

}
