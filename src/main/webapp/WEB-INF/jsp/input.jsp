<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String errorMsg = (String) session.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>My家計簿</title>
<style>
	header {
		display:flex;
	}
	ul {
		display:flex;
	}
	li {
		list-style:none;
		padding:10px;
	}
</style>
</head>
<body>
	<header>
		<h1>My家計簿</h1>
		<ul>
			<li>
				<form action="/MyKakeiboJSP/DayServlet" method="post">
					<input type="submit" name="toDay" value="日">
				</form>
			</li>
			<li>
				<form action="/MyKakeiboJSP/MonthServlet" method="get">
					<input type="submit" name="toMonth" value="月">
				</form>
			</li>
			<li>
				<form action="/MyKakeiboJSP/YearServlet" method="get">
					<input type="submit" name="toYear" value="年">
				</form>
			</li>
		</ul>
	</header>
	<main>
		<h2>入力画面</h2>
		<form action="/MyKakeiboJSP/InputServlet" method="post">
			<p>日付<input type="date" name="date" required></p>
			<p>支出<input type="text" name="money" required>円</p>
			<p>カテゴリ</p>
			<ul style="list-style:none;display:block;">
			<%  
				String[] categoryArray = {"食費","日用品","衣服","交通費","医療費","交際費"};
				for(String category : categoryArray) {
			%>
				<li style="padding-bottom:0px;">
					<input type="radio" name="category" value="<%= category %>" required><%= category %>
				</li>
			<%  } %>
			</ul>
			<input type="submit" value="登録">
		</form>
		<form action="/MyKakeiboJSP/DayServlet" method="post" style="margin-top:10px;">
			<input type="submit" value="戻る">
		</form>
	</main>
	<script></script>
</body>
</html>