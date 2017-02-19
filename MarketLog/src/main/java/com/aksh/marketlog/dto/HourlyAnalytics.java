package com.aksh.marketlog.dto;

import java.util.Date;

public class HourlyAnalytics extends DailyAnalytics{
	private Date hour;

	public Date getHour() {
		return hour;
	}

	public void setHour(Date hour) {
		this.hour = hour;
	}
	
}
