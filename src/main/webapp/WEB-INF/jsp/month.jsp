<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.MonthResult,java.util.Map" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	MonthResult monthResult = (MonthResult) session.getAttribute("monthResult");
	//Object型で返ってくるからキャスト必要
	Map<String,Integer> map =  monthResult.getCategoryTotalMap();
	//何の型が返ってくるのかもっと意識したほうが
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
	}
	#btn {
		padding:10px;
	}
</style>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/1.0.2/Chart.min.js" type="text/javascript"></script>
</head>
<body>
	<header>
		<h1>My家計簿</h1>
		<ul>
			<li id="btn">
				<form action="/MyKakeiboJSP/DayServlet" method="post">
					<input type="submit" name="toDay" value="日">
				</form>
			</li>
			<li id="btn">
				<form action="/MyKakeiboJSP/YearServlet" method="get">
					<input type="submit" name="toYear" value="年">
				</form>
			</li>
			<li id="btn">
				<form action="/MyKakeiboJSP/InputServlet" method="get">
					<input type="submit" name="toInput" value="入力">
				</form>
			</li>
		</ul>
	</header>
	<main>
		<h2>月画面</h2>
		<div style="display:flex;">
			<form action="/MyKakeiboJSP/MonthServlet" method="get" id="btn">
				<input type="submit" name="action" value="<">
				<input type="hidden" name="month" value="${ monthResult.month }">
			</form>
			<p id="btn" style="margin-top:0px;"><c:out value="${ monthResult.month }" /></p>
			<form action="/MyKakeiboJSP/MonthServlet" method="get" id="btn">
				<input type="submit" name="action" value=">">
				<input type="hidden" name="month" value="${ monthResult.month }">
			</form>
		</div>
		
		<canvas id="graph-area" height="240" width="320">
		</canvas>
		
		<p>当月の合計金額：${ monthResult.sum }円</p>
		<table border="1" style="border-collapse: collapse;" id="table">
		<% for(Map.Entry<String,Integer> entry : map.entrySet()) { %>
			<tr>
				<td><%= entry.getKey() %></td>
				<td><%= entry.getValue() %>円</td>
			</tr>
		<% } %>
		</table>


		<h2 style="color: gray;">
			未実装：カテゴリーごとの色分け処理<br>
		</h2>
		
		
	</main>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script type="text/javascript">
		//テーブル実装どないしよか...
		
		   var pieData = [
		      {
		         value: ${ monthResult.categoryTotalMap.食費 },
		         color: '#FF0000',
		         label: "食費"
		      },
		      {
		         value: ${ monthResult.categoryTotalMap.日用品 },
		         color: '#008000',
		         label: "日用品"
		      },
		      {
		         value: ${ monthResult.categoryTotalMap.衣類 },
		         color: '#FF1493',
		         label: "衣服"
		      },
		      {
		         value: ${ monthResult.categoryTotalMap.交通費 },
		         color: '#00FF00',
		         label: "交通費"
		      },
		      {
		         value: ${ monthResult.categoryTotalMap.医療費 },
		         color: '#FFA500',
		         label: "医療費"
		      },
		      {
		         value: ${ monthResult.categoryTotalMap.交際費 },
		         color: '#0000FF',
		         label: "交際費"
			  }

		   ];

		   window.onload = function(){
		      var ctx = document.getElementById("graph-area").getContext("2d");
		      window.myPie = new Chart(ctx).Pie(pieData);
		   };
		   
	</script>
</body>
</html>