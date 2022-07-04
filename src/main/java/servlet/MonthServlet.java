package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GetMonthResultLogic;
import model.MonthResult;

@WebServlet("/MonthServlet")
public class MonthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクパラからmonth,before,afterの値を取得
		request.setCharacterEncoding("UTF-8");
		String month = request.getParameter("month");
		String action = request.getParameter("action");
		
		//当月表示ならnull,null 先月表示ならbefore,yyyy-MM 翌月表示ならafter,yyyy-MM
		GetMonthResultLogic logic = new GetMonthResultLogic();
		MonthResult monthResult = null;
		if(month == null) {
			monthResult = logic.executeFindFrom();
		}else if(action.equals("<")) {
			monthResult = logic.executeFindFromBefore(month);
		} else if(action.equals(">")) {
			monthResult = logic.executeFindFromAfter(month);
		}
		//スコープに格納
		HttpSession session = request.getSession();
		session.setAttribute("monthResult", monthResult);
		//遷移
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/month.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
