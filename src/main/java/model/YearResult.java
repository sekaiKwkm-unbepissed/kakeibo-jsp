package model;

import java.util.List;

public class YearResult {
	private String year;
	private int sum;
	private List<Integer> monthTotalList;
	
	public YearResult(String year, int sum, List<Integer> monthTotalList) {
		this.year = year;
		this.sum = sum;
		this.monthTotalList = monthTotalList;
	}

	public String getYear() {
		return year;
	}
	public int getSum() {
		return sum;
	}
	public List<Integer> getMonthTotalList() {
		return monthTotalList;
	}
	
	
}
