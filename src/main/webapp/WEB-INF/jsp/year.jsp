<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.YearResult,java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	YearResult yearResult = (YearResult) session.getAttribute("yearResult");
	List<Integer> list = yearResult.getMonthTotalList();
	int yearAverage = (int) yearResult.getSum() / 12;
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
				<form action="/MyKakeiboJSP/InputServlet" method="get">
					<input type="submit" name="toInput" value="入力">
				</form>
			</li>
		</ul>
	</header>
	<main>
		<h2>年画面</h2>
		<div style="display: flex;">
			<form action="/MyKakeiboJSP/YearServlet" method="get" id="btn">
				<input type="submit" name="action" value="<">
				<input type="hidden" name="year" value="${ yearResult.year }">
			</form>
			<p id="btn" style="margin-top: 0px;">
				<c:out value="${ yearResult.year }" />
			</p>
			<form action="/MyKakeiboJSP/YearServlet" method="get" id="btn">
				<input type="submit" name="action" value=">">
				<input type="hidden" name="year" value="${ yearResult.year }">
			</form>
		</div>
		
		<h2>棒グラフゾーン</h2>
		<canvas id="graph-area" height="300" width="500">
		</canvas>
		
		<table border="1" style="border-collapse: collapse;" rules="none" id="table1">
			<tr>
				<td>当年合計金額</td>
				<td><c:out value="${ yearResult.sum }" />円</td>
			</tr>
			<tr>
				<td>ひと月当たりの平均金額</td>
				<td><%= yearAverage %>円</td>
			</tr>
		</table>
		
		<table border="1" style="border-collapse: collapse;margin-top:10px;" id="table2">
		<% for(int i = 0; i < 12; i++) { %>
			<tr>
				<td><%= i +1 %>月</td>
				<td><%= list.get(i) %>円</td>
			</tr>
		<% } %>
		</table>
	</main>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script type="text/javascript">
	var list2 = ${ yearResult.monthTotalList };
	
	 //中身
	   var barChartData = {
	      labels : ["1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"],
	      datasets : [
	         {
	            fillColor : "rgba(240,128,128,0.6)",    // 塗りつぶし色
	            strokeColor : "rgba(240,128,128,0.9)",  // 枠線の色
	            highlightFill: "rgba(255,64,64,0.75)",  // マウスが載った際の塗りつぶし色
	            highlightStroke: "rgba(255,64,64,1)",   // マウスが載った際の枠線の色
	            data : list2 // 各棒の値(カンマ区切りで指定)
	         }
	      ]
	   }

	   //グラフを描画
	   window.onload = function(){
	      var ctx = document.getElementById("graph-area").getContext("2d");
	      window.myBar = new Chart(ctx).Bar(barChartData);
	   }
	</script>
</body>
</html>