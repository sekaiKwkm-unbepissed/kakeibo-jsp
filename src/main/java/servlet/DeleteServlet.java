package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DeleteExpenseLogic;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//削除する処理
		int id = Integer.parseInt(request.getParameter("id"));
		DeleteExpenseLogic logic = new DeleteExpenseLogic();
		logic.executeDelete(id);
		//日付情報をスコープ経由でjspに受け渡す処理
		String date = request.getParameter("date");
		HttpSession session = request.getSession();
		session.setAttribute("date",date);
		//画面遷移させる処理
		RequestDispatcher dispatcher = request.getRequestDispatcher("/DayServlet");
		dispatcher.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
