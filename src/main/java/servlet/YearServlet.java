package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GetYearResultLogic;
import model.YearResult;

@WebServlet("/YearServlet")
public class YearServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); //テンプレ
		String year = request.getParameter("year");
		String action = request.getParameter("action");
		
//		クラス名に動詞はつかわないほうが…
		GetYearResultLogic logic = new GetYearResultLogic();
		YearResult yearResult = null;
		if(year == null) {
			yearResult = logic.executeFindFrom();
		}else if(action.equals("<")) {
			yearResult = logic.executeFindFromBefore(year);
		} else if(action.equals(">")) {
			yearResult = logic.executeFindFromAfter(year);
		}
		//スコープにデータ保存
		HttpSession session = request.getSession();
		session.setAttribute("yearResult", yearResult);
		//画面遷移
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/year.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
