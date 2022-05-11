package ajbcolionebankinkapp.actions;

import java.time.LocalDateTime;
import java.util.Scanner;

import ajbcolionebankinkapp.appmanager.AppManager;
import ajbcolionebankinkapp.appmanager.Utils;
import ajbcolionebankinkapp.enumaretion.ActivityName;
import ajbcolionebankinkapp.users.AccountOwner;
import ajbcolionebankinkapp.users.PhoneNumber;

public class TransferFunds implements Action {
	protected static final int MAX_AMOUNT_TO_TRANSFER = 2000;
	protected Scanner scanner = new Scanner(System.in);
	protected AccountOwner accountOwner;
	
	public TransferFunds(AccountOwner accountOwner) {
		this.accountOwner = accountOwner;
	}
	
	@Override
	public void run() {
		String activityInfo = "";
		String output = "";

		System.out.println("Reciving user phone number: ");
		String phone = scanner.next();
		PhoneNumber phoneNumber = Utils.parseStringToPhonenumber(phone);

		System.out.println("Amount: ");
		double amount = scanner.nextDouble();

		if (amount > MAX_AMOUNT_TO_TRANSFER) {
			output = "The maximum amount that can be transferred is " + MAX_AMOUNT_TO_TRANSFER;
			activityInfo = "Failed to transfer funds - over the maximum amount";
		}

		AccountOwner accountOwnerTarget = AppManager.getOwnerByPhoneNumber(phoneNumber);
		if (accountOwnerTarget == null) {
			output = "The phone number dosent found";
			activityInfo = "Failed to transfer funds - Phone number dosen't found";
		} else {
			accountOwner.withdrawal(amount);
			accountOwnerTarget.deposit(amount);
			output = "Transfer succeeded!";
			System.out.println("Your new balance: " + accountOwner.checkBalance());
			activityInfo = "Transfer funds to " + phone + " succeeded";
		}

		System.out.println(output);
		accountOwner.getAccount().addActivityData(ActivityName.TRANSFER, amount, LocalDateTime.now(), activityInfo);
	}

}
