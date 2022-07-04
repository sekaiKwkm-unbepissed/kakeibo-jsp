package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Expense;
import model.PostExpenseLogic;

@WebServlet("/InputServlet")
public class InputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/input.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String date = request.getParameter("date");
		int money = Integer.parseInt(request.getParameter("money"));
		String category = request.getParameter("category");

		//logicを呼び出して、受け取った情報を元にデータ追加
		//引数のid「0」は、クラスのフィールドに合わせて渡す仮の情報
		Expense expense = new Expense(0,date,money,category);
		PostExpenseLogic logic = new PostExpenseLogic();

		if(logic.executeCreate(expense)) {
			//登録に成功したら日画面へ遷移
			RequestDispatcher dispatcher = request.getRequestDispatcher("/DayServlet");
			dispatcher.forward(request,response);
		}else{
			//登録失敗なら画面更新
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/input.jsp");
			dispatcher.forward(request,response);
		}
	}

}
