package ajbcolionebankinkapp.users;

import java.time.LocalDate;

public abstract class Person {
	protected String firstName;
	protected String lastName;
	protected PhoneNumber phoneNumber;
	protected LocalDate birthDate;

	public Person(String firstName, String lastName, PhoneNumber phoneNumber, LocalDate birthDate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.birthDate = birthDate;
	}

	public PhoneNumber getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(PhoneNumber phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber
				+ ", birthDate=" + birthDate + "]";
	}
}
