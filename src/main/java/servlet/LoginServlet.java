package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import model.UserLogic;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータ(入力されたユーザー名とパス)をもってくる
		request.setCharacterEncoding("UTF-8"); //文字コードの指定(リクパラ取得時のお約束)
		String name = request.getParameter("name");
		String pass = request.getParameter("pass"); 
		
		//それらをつかってDBに検索かける
		//Uesrインスタンスをつくり
		User user = new User(name,pass); 
		UserLogic userLogic = new UserLogic();
		
		//リダイレクトよりかはフォワードのほうが好ましい ひとつのサーバ内で完結しているため
		//execute()メソッドの結果がtrue(＝認証成功)なら
		if(userLogic.execute(user)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/DayServlet");
			dispatcher.forward(request,response);
		} else {
			//認証失敗なら
			//リクエストスコープにエラーメッセージを格納して
			request.setAttribute("errorMsg", "ユーザー名、パスワードが一致しません");
			//LoginServletにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request,response);
		}
	}

}
