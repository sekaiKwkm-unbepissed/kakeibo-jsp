package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.DayResult;
import model.Expense;
import model.MonthResult;
import model.YearResult;

public class ExpensesDAO {
	
	/**
	 * DB接続前に必要なドライバの設定をするコンストラクタ
	 */
	public ExpensesDAO() {
		try {
			Class.forName("org.postgresql.Driver"); //テンプレ
		}catch(ClassNotFoundException e) {
			System.out.println("ClassNotFoundExceptionエラーをスロー");
		}
	}
	
	//DB接続に必要な情報の準備(テキスト通り)
	private final String JDBC_URL = "jdbc:postgresql://localhost:5432/kakeibo"; //SQLの教本参考
	private final String DB_USER = "postgres"; //デフォルトのまま
	private final String DB_PASS = "8XF_post"; //自分で設定したもの
	
	/**
	 * 情報を受け取りexpenseテーブルに追加する
	 * 
	 * @param expense id、日付、金額、カテゴリーの情報を持つ
	 * @return 追加が成功したか否か
	 */
//	テーブルつくっているみたいに勘違いしそう(→insertExpenseとか)
	public boolean create(Expense expense) {
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)) {
			//SQLの実行
			String sql = "INSERT INTO expenses (date, money, category) VALUES(?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, expense.getDate());
			ps.setInt(2, expense.getMoney());
			ps.setString(3, expense.getCategory());
			int createResult = ps.executeUpdate();
			if(createResult == 1) {
				return true;
			}
			return false;
		}catch(SQLException e) {
			return false;
		}
	}
	
	/**
	 * 日データを取得するfindDayメソッド
	 * 
	 * @param day 日付データ
	 * @return DayResult カテゴリー、合計金額、カテゴリーと金額のリスト
	 */
	public DayResult findDay(String day) {
		//DB接続(テンプレ)
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			//SQL文の用意
			String sql = "SELECT id,date,money,category FROM expenses WHERE date = ? ORDER BY id DESC";
			//テンプレ
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//受け取ったdayをsql文に追加
			pStmt.setString(1, day);
			//SQL文の実行
			ResultSet rs = pStmt.executeQuery();
			
			//ループ外で使えるようにするための初期化コーナー
			List<Expense> detail = new ArrayList<>();
			int sum = 0;
			while(rs.next()) {
				//受け取とったid、date、money、categoryの値をそれぞれ取得
				int id = rs.getInt("id");
				String date = rs.getString("date");
				int money = rs.getInt("money");
				String category = rs.getString("category");
				detail.add(new Expense(id,date,money,category));
				sum += money;
			}
			DayResult dayResult = new DayResult(day,sum,detail);
			return dayResult;
		} catch(SQLException e) {
			return null;
		}
	}
	
	
	/**
	 * 年と月情報を受け取って、一か月分のデータを返す
	 * 
	 * @param yearAndMonth 「yyyy-MM」のString型
	 * @return 年と月、一か月の合計金額、カテゴリーとカテゴリーごとの合計金額のマップ
	 */
//	monthは良くない
	public MonthResult findMonthFrom(String yearAndMonth) {
		//DB接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)) {
			//SQL生成(カテゴリーとカテゴリーごとの合計金額を取得)
			String sql = "SELECT category,sum(money) FROM expenses WHERE date like ? GROUP BY category";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, yearAndMonth+"%");
			ResultSet rs = pStmt.executeQuery();
			
			//初期化
			int sum = 0;
			Map<String,Integer> map = new HashMap<>();
			String[] categoriesOfMap = {"食費","日用品","衣類","交通費","医療費","交際費"};
			for(String categoryOfMap : categoriesOfMap) {
				map.put(categoryOfMap,0);
			}
			
			while(rs.next()) {
				String monthCategory = rs.getString("category");
				int monthTotalMoney = rs.getInt("sum");
				map.put(monthCategory, monthTotalMoney);
				sum += monthTotalMoney;
			}
			return new MonthResult(yearAndMonth,sum,map);
		}catch(SQLException e) {
			return null;
		}
	}
	
	/**
	 * 年データを受け取りYearResult型のデータを返す
	 * 
	 * @param year 「yyyy」
	 * @return YearResult 年、合計金額、月ごと合計金額が入ったリスト
	 */
	public YearResult findYearFrom(String year) {
		try (Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)) {
			//SQL実行
			String sql = "SELECT date,money FROM expenses WHERE date like ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,year+"%");
			ResultSet rs = ps.executeQuery();
			
			//初期化
			int sum = 0;
			//１２か月分のデータを初期化
			int[] monthArray = new int[12];
			//要素数数えなきゃいけない、ゼロならわざわざ代入しなくても
			Integer[] defaultMonthMoney = {0,0,0,0,0,0,0,0,0,0,0,0} ;
			List<Integer> monthTotalList = Arrays.asList(defaultMonthMoney);
			
			while(rs.next()) {
				//「yyyy-MM-dd」からMMの月部分を取得
				String monthStr = rs.getString("date").substring(5,7);
				//
				int month = 0;
				//「06」のような一桁の月なら、一桁目を抽出する
				if(monthStr.startsWith("0")) {
					//parseIntってゼロとってくれる？
					month = Integer.parseInt(monthStr.substring(1));
				}else {
					month = Integer.parseInt(monthStr);
				}
				int money = rs.getInt("money");
				//1月の金額をリストに追加する場合、インデックス「０」に代入する必要があるので
				//「１(月) -1」のようにして、setメソッドに値を渡す
				monthTotalList.set(month -1, monthTotalList.get(month -1) + money); 
				sum += money;
			}
			
			return new YearResult(year,sum,monthTotalList);
		} catch(SQLException e) {
			return null;
		}
	}
	
	/**
	 * カテゴリーと金額が一致する行を削除するメソッド
	 * 
	 * @param id テーブルのID情報
	 * @return boolean 成功もしくは失敗
	 */
	public boolean delete(int id) {
		//DB接続(ここもテンプレ)
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)) {
			//SQL文の用意
			String sql = "DELETE FROM expenses WHERE id = ?";
			//テンプレ
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//ID情報をSQL文に追加
			pStmt.setInt(1, id);
			//実行(テンプレ)
			int result = pStmt.executeUpdate();
			
			if (result != 0) {
				return true;
			}
			return false;
		}catch(SQLException e) {
			return false;
		}
	}
}
