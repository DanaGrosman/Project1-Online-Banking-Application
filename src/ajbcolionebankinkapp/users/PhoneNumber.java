package ajbcolionebankinkapp.users;

public class PhoneNumber {
	protected int prefix;
	protected int number;
	
	public PhoneNumber(int prefix, int number) {
		this.prefix = prefix;
		this.number = number;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhoneNumber other = (PhoneNumber) obj;
		return number == other.number && prefix == other.prefix;
	}
	
	
	
}
