package ajbcolionebankinkapp.appmanager;

import ajbcolionebankinkapp.users.PhoneNumber;

public class Utils {

	public static PhoneNumber parseStringToPhonenumber(String phone) {
		return new PhoneNumber(Integer.parseInt(phone.substring(0, 3)), Integer.parseInt(phone.substring(3, 10)));
	}

}
