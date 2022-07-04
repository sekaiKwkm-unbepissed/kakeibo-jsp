package test;

import dao.ExpensesDAO;
import model.Expense;
import model.MonthResult;
import model.YearResult;

public class ExpensesDAOTest {
	public static void main(String[] args) {
//		testDelete1();
//		testDelete2();
//		testFindFromMonth();
//		testFindYearFrom();
//		testCreate();
		int[] monthArray = new int[12];
		
		
	}
	public static void testCreate() {
		ExpensesDAO dao = new ExpensesDAO();
		Expense ex = new Expense(0,"2022-06-25",2000,"日用品");
		boolean result = dao.create(ex);
		System.out.println("result in DAOtest:"+result);
		if(result) {
			System.out.println("成功");
		}else {
			System.out.println("失敗");
		}
	}
	
	public static void testDelete1() {
		ExpensesDAO dao = new ExpensesDAO();
		boolean result = dao.delete(23);
		if(result) {
			System.out.println("削除成功");
		} else {
			System.out.println("削除失敗");
		}
	}
	public static void testFindFromMonth() {
		ExpensesDAO dao = new ExpensesDAO();
		MonthResult monthResult = dao.findMonthFrom("2022-06");
		System.out.println(monthResult.getCategoryTotalMap());
	}
	public static void testFindYearFrom() {
		ExpensesDAO dao = new ExpensesDAO();
		YearResult result = dao.findYearFrom("2022");
		System.out.println(result.getMonthTotalList());
	}
}
