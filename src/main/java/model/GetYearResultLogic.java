package model;

import dao.ExpensesDAO;

public class GetYearResultLogic {
	/**
	 * 当年データを渡して、findYearFromを実行させる
	 * 
	 * @return 年、合計金額、月ごとの合計金額をもつクラス
	 */
	public static YearResult executeFindFrom() {
		LocalDateTimeToString ldtToString = new LocalDateTimeToString();
		String year = ldtToString.convertToString("yyyy");
		ExpensesDAO dao = new ExpensesDAO();
		return dao.findYearFrom(year);
	}
	
	/**
	 * 一年前の年代をfindYearFromに渡して実行させる
	 * 
	 * @param thisYear String型の当年
	 * @return 年、合計金額、月ごとの合計金額をもつクラス
	 */
	public static YearResult executeFindFromBefore(String thisYear) {
		int yearInt = Integer.parseInt(thisYear) -1;
		String yearStr = "" + yearInt;
		ExpensesDAO dao = new ExpensesDAO();
		return dao.findYearFrom(yearStr);
	}
	
	/**
	 * 一年後の年代をfindYearFromに渡して実行させる
	 * 
	 * @param thisYear String型の当年
	 * @return 年、合計金額、月ごとの合計金額をもつクラス
	 */
	public static YearResult executeFindFromAfter(String thisYear) {
		int yearInt = Integer.parseInt(thisYear) +1;
		String yearStr = "" + yearInt;
		ExpensesDAO dao = new ExpensesDAO();
		return dao.findYearFrom(yearStr);
	}
}
