package model;

import dao.ExpensesDAO;

public class GetMonthResultLogic {
	/**
	 * 月画面に必要なデータを取得するfindFrom(String month)を実行する
	 * 
	 * @return MonthResult 月データを持つクラス
	 */
	public static MonthResult executeFindFrom() {
		LocalDateTimeToString ldtToString = new LocalDateTimeToString();
		String thisMonth = ldtToString.convertToString("yyyy-MM");
		ExpensesDAO dao = new ExpensesDAO();
		MonthResult monthResult = dao.findMonthFrom(thisMonth);
		return monthResult;
	}

	/**
	 * 先月のデータを探すようにfindFromメソッドに月データを渡し実行させる
	 * 
	 * @param yearAndMonth
	 * @return MonthResult
	 */
	public static MonthResult executeFindFromBefore(String yearAndMonth) {
		int year = Integer.parseInt(yearAndMonth.substring(0,4));
		int month = Integer.parseInt(yearAndMonth.substring(5,7));

		//渡されたデータの中身が1月なら前年の12月を渡す
		if(month == 1) {
			year --;
			month = 12;
		}else {
			month --;
		}

		ExpensesDAO dao = new ExpensesDAO();
		MonthResult monthResult = dao.findMonthFrom(prependZero(year, month));
		return monthResult;
	}

	/**
	 * 翌月のデータを探すようにfindFromメソッドに月データを渡し実行させる
	 * 
	 * @param yearAndMonth
	 * @return MonthResult
	 */
	public static MonthResult executeFindFromAfter(String yearAndMonth) {
		int year = Integer.parseInt(yearAndMonth.substring(0,4));
		int month = Integer.parseInt(yearAndMonth.substring(5,7));

		//渡されたデータの中身が12月なら翌年の1月を渡す
		if(month == 12) {
			year ++;
			month = 1;
		}else {
			month ++;
		}

		ExpensesDAO dao = new ExpensesDAO();
		MonthResult monthResult = dao.findMonthFrom(prependZero(year, month));
		return monthResult;
	}

	/**
	 * int型の年と月データを受け取り、「"yyyy-MM"」に整形する
	 * @param year 年
	 * @param month 月(int型なので)
	 * @return
	 */
	public static String prependZero(int year, int month) {
		String targetingMonth = year + "-" + month;
		if(month < 10) {
			String strMonth = "0" + Integer.valueOf(month);
			targetingMonth = year + "-" + strMonth;
		}	
		return targetingMonth;
	}
}
