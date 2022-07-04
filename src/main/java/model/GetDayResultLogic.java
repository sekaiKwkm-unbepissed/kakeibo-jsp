package model;

import dao.ExpensesDAO;

public class GetDayResultLogic {
	/**
	 * 日付を指定した場合に利用されるメソッド
	 * @param date 日付情報
	 * @return 日付 合計金額 金額とカテゴリーのリスト
	 */
	public DayResult execute(String date) {
		ExpensesDAO dao = new ExpensesDAO();
		DayResult dayResult = dao.findDay(date);
		//dao.findFrom(date);
		return dayResult;
	}
	
	/**
	 * ログイン成功時、今日の日付データを取得するために呼び出される方のメソッド
	 * @return DayResult
	 */
	public DayResult execute() {
		ExpensesDAO dao = new ExpensesDAO();
		//今日の日付データを取得し、整形する
		LocalDateTimeToString ldtToString = new LocalDateTimeToString();
		String today = ldtToString.convertToString("yyyy-MM-dd");
		DayResult dayResult = dao.findDay(today);
		return dayResult;
	}
	
}
