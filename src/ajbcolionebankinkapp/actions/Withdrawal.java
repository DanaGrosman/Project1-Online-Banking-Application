package ajbcolionebankinkapp.actions;

import java.time.LocalDateTime;
import java.util.Scanner;

import ajbcolionebankinkapp.enumaretion.ActivityName;
import ajbcolionebankinkapp.users.AccountOwner;

public class Withdrawal implements Action {
	protected Scanner scanner = new Scanner(System.in);
	AccountOwner accountOwner;

	public Withdrawal(AccountOwner accountOwner) {
		this.accountOwner = accountOwner;
	}

	@Override
	public void run() {
		String activityInfo = "";

		System.out.println("Amount: ");
		double amount = scanner.nextDouble();

		double maxWithdrawal = accountOwner.getAccount().getAccountProperties().getMaxWithdrawal();
		if (amount <= maxWithdrawal || maxWithdrawal == 0) {
			accountOwner.withdrawal(amount);
			System.out.println("Your new balance: " + accountOwner.checkBalance());
			activityInfo = "Withdrawal succeeded";
		} else {
			activityInfo = "Withdrawal failed - over the daily maximum withdrawal";
		}
		System.out.println(activityInfo);
		accountOwner.getAccount().addActivityData(ActivityName.WITHDRAWAL, amount, LocalDateTime.now(), activityInfo);
	}

}
