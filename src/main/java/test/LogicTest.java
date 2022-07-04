package test;

import java.util.Map;

import model.Expense;
import model.GetMonthResultLogic;
import model.GetYearResultLogic;
import model.MonthResult;
import model.PostExpenseLogic;
import model.YearResult;

public class LogicTest {
	public static void main(String[] args) {
//		testExecuteDelete();
//		testExFindFromMonth();
//		testEFFAfterMonth();
//		testExecuteFindYearFrom();
//		testExecuteLogic();
		testFindFromBefore();
	}
	
	public static void testExecuteLogic() {
		PostExpenseLogic logic = new PostExpenseLogic();
		Expense ex = new Expense(0,"2022-06-25",3000,"衣類");
		if(logic.executeCreate(ex)) {
			System.out.println("success");
		}else {
			System.out.println("failure");
		}
	}
//	public static void testExecuteDelete() { 
//		TargetExpense targetExpense = new TargetExpense("衣類",1000);
//		DeleteExpenseLogic logic = new DeleteExpenseLogic();
//		boolean result = logic.executeDelte(targetExpense);
//		if(result) {
//			System.out.println("削除成功");
//		}
//	}
	public static void testExFindFromMonth() {
		GetMonthResultLogic logic = new GetMonthResultLogic();
		MonthResult result = logic.executeFindFrom();
		if(result != null) {
			System.out.println("Success:");
			System.out.println(result.getMonth());
			System.out.println(result.getSum());
			Map<String,Integer> map = result.getCategoryTotalMap();
			System.out.println(map.get("交際費"));
		}else {
			System.out.println("failure");
		}
	}
	
	public static void testEFFAfterMonth() {
		GetMonthResultLogic logic = new GetMonthResultLogic();
		MonthResult result = logic.executeFindFromAfter("2022-06");
		if(result != null) {
			System.out.println("success;");
			System.out.println(result.getMonth());
			System.out.println(result.getSum());
			Map<String,Integer> map = result.getCategoryTotalMap();
			System.out.println(map.get("交際費"));
		}else {
			System.out.println("failure");
		}
	}
	
	public static void testExecuteFindYearFrom() { 
		GetYearResultLogic logic = new GetYearResultLogic();
		YearResult result = logic.executeFindFrom();
		if(result != null) {
			System.out.println("success");
			System.out.println(result.getMonthTotalList());
		}
	}
	public static void testFindFromBefore() {
		GetYearResultLogic logic = new GetYearResultLogic();
		YearResult result = logic.executeFindFromBefore("2022");
		if(result != null) {
			System.out.println("success");
			System.out.println(result.getMonthTotalList());
		}
	}
}
