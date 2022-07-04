package model;

public class Expense {
	private final int id;
	private final String date;
	private final int money;
	private final String category;

	public Expense(int id, String date, int money, String category) {
		this.id = id;
		this.date = date;
		this.money = money;
		this.category = category;
	}

	public int getId() {
		return id;
	}
	public String getDate() {
		return date; 
	}
	public int getMoney() { 
		return money;
	}
	public String getCategory() { 
		return category; 
	}

}
