package model;

import java.util.List;

public class DayResult {
	private String date;
	private int sum;
	private List<Expense> detail;
	
	public DayResult(String date, int sum, List<Expense> detail) {
		this.date = date;
		this.sum = sum;
		this.detail = detail;
	}

	public String getDate() {
		return date;
	}

	public int getSum() {
		return sum;
	}

	public List<Expense> getDetail() {
		return detail;
	}

	
}
