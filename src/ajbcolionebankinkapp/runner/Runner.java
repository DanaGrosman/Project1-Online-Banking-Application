package ajbcolionebankinkapp.runner;

import ajbcolionebankinkapp.appmanager.AppManager;

public class Runner {

	public static void main(String[] args) {
		BankDB bankDB = new BankDB();
		bankDB.initDB();
		AppManager appManager = bankDB.appManager;
		
		appManager.runApp();
	}
}
