package model;

import dao.ExpensesDAO;

public class DeleteExpenseLogic {
	/**
	 * DAOのdeleteメソッドを実行するためのメソッド
	 * @param id
	 * @return boolean 削除成功か失敗
	 */
	public boolean executeDelete(int id) {
		ExpensesDAO dao = new ExpensesDAO();
		return dao.delete(id);
	}
}
