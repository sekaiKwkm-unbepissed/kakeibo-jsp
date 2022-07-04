package model;

import dao.ExpensesDAO;

public class PostExpenseLogic {
	/**
	 * createメソッドを呼び出す
	 * 
	 * @param expense ニセid、日付、金額、カテゴリーの情報をもつ
	 * @return 無事に作成できたか否か
	 */
	public boolean executeCreate(Expense expense) {
		ExpensesDAO dao = new ExpensesDAO();
		return dao.create(expense);
	}
}
