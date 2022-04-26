package ajbcolionebankinkapp.enumaretion;

public enum AccountProperties {
	BRONZE(4.5, 6, 5, 7.5, 10000, 2500), SILVER(3, 4.5, 3.8, 5, 20000, 4000), GOLD(1.5, 3, 1.75, 3.8, 50000, 6000),
	TITANIUM(0, 0, 0, 0, 0, 0);

	double interestRateMin;
	double interestRateMax;
	double operationFeeMin;
	double operationFeeMax;
	double maxLoan;
	double maxWithdrawal;

	AccountProperties(double interestRateMin, double interestRateMax, double operationFeeMin, double operationFeeMax,
			double maxLoan, double maxWithdrawal) {
		this.interestRateMin = interestRateMin;
		this.interestRateMax = interestRateMax;
		this.operationFeeMin = operationFeeMin;
		this.operationFeeMax = operationFeeMax;
		this.maxLoan = maxLoan;
		this.maxWithdrawal = maxWithdrawal;
	}
	
	public double getMaxLoan() {
		return maxLoan;
	}
	
	public double getMaxWithdrawal() {
		return maxWithdrawal;
	}
	
	public double getInterestRateMin() {
		return interestRateMin;
	}

	public double getOperationFeeMax() {
		return operationFeeMax;
	}
	
}
