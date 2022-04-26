package ajbcolionebankinkapp.account;

import java.time.LocalDateTime;

import ajbcolionebankinkapp.enumaretion.ActivityName;

public class ActivityData {
	protected ActivityName activityName;
	protected double balanceChange;
	protected LocalDateTime timeStamp;
	protected String info;
	
	public ActivityData(ActivityName activityName, double balanceChange, LocalDateTime timeStamp, String info) {
		this.activityName = activityName;
		this.balanceChange = balanceChange;
		this.timeStamp = timeStamp;
		this.info = info;
	}

	@Override
	public String toString() {
		return "ActivityData [activityName=" + activityName + ", balanceChange=" + balanceChange + ", timeStamp="
				+ timeStamp + ", info=" + info + "]";
	}
	
}
