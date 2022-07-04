package model;

import java.util.Map;

public class MonthResult {
	private String month;
	private final int sum;
	private final Map<String,Integer> categoryTotalMap;
	
	public MonthResult(String month, int sum, Map<String,Integer> categoryTotalMap) {
		this.month = month;
		this.sum = sum;
		this.categoryTotalMap = categoryTotalMap;
	}

	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public int getSum() {
		return sum;
	}
	public Map<String, Integer> getCategoryTotalMap() {
		return categoryTotalMap;
	}
	
}
