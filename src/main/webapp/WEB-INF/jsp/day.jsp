<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="model.DayResult,model.Expense"%>
<%
	DayResult dayResult = (DayResult) session.getAttribute("dayResult");
	String date = (String) session.getAttribute("date");
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
				<form action="/MyKakeiboJSP/MonthServlet" method="get">
					<input type="submit" name="toMonth" value="月">
				</form>
			</li>
			<li>
				<form action="/MyKakeiboJSP/YearServlet" method="get">
					<input type="submit" name="toMonth" value="年">
				</form>
			</li>
			<li>
				<form action="/MyKakeiboJSP/InputServlet" method="get">
					<input type="submit" name="toMonth" value="入力">
				</form>
			</li>
		</ul>
	</header>
	<h2>日画面</h2>
	
	<h2 style="color:gray;">未実装<br>
	・カテゴリーごとに配色する処理<br>
	・「削除」→ゴミ箱アイコン～
	</h2>
	
	<main>
		<form action="/MyKakeiboJSP/DayServlet" method="get" id="form1">
			日付<input type="date" name="date" id="date" value="${ dayResult.date }"> 
		</form>
			合計：<c:out value="${ dayResult.sum }" />円<br>
		<table border="1" style="border-collapse: collapse;">
			<form action="/MyKakeiboJSP/DeleteServlet" method="get" id="form2">
				<c:forEach var="expense" items="${ dayResult.detail }">
					<tr>
						<td>${ expense.category }</td>
						<td>${ expense.money }</td>
						<input type="hidden" name="id" value="${ expense.id }">
						<input type="hidden" name="date" value="${ dayResult.date }">
						<td><input type="submit" value="削除"></td>
					</tr>
				</c:forEach>
			</form>
		</table>
	</main>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script type="text/javascript">
		//カテゴリーごとに色分けする処理
		
		var date = $('#date');
		var form1 = $('#form1');
		date.on('change', function() {
			form1.submit();
		});
	</script>
</body>
</html>