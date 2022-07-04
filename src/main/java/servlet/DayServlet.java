package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DayResult;
import model.GetDayResultLogic;

@WebServlet("/DayServlet")
public class DayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String date = request.getParameter("date");
		GetDayResultLogic logic = new GetDayResultLogic();
		DayResult dayResult = logic.execute(date);
		HttpSession session = request.getSession();
		session.setAttribute("dayResult", dayResult);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/day.jsp");
		dispatcher.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); //テンプレ(不要なのでは？)
		GetDayResultLogic logic = new GetDayResultLogic();
		DayResult dayResult = logic.execute();
		
		//スコープに格納
		HttpSession session = request.getSession();
		session.setAttribute("dayResult", dayResult);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/day.jsp");
		dispatcher.forward(request,response);
		
	}

}
