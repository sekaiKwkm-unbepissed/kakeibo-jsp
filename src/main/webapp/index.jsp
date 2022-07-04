<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String errorMsg = (String) request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>My家計簿</title>
	</head>
	<body>
		<h1>ログイン画面</h1>
s		<%-- LoginServletのdoPostを実行したいので、以下のように指定 --%>
		<form action="/MyKakeiboJSP/LoginServlet" method="post">
			ユーザー名：<input type="text" name="name" /><br>
			PASS：<input type="password" name="pass" /><br>
			<input type="submit" value="送信" />
		</form>
		<%-- errorLoginがnullでなければ(なにか値が入っていれば)エラー内容を表示 --%>
		<% if(errorMsg != null) { %>
			<p><%= errorMsg %></p>
		<% } %>
	</body>
</html>