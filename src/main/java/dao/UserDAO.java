package dao;

//ここのimport宣言部分は基本的にテキスト通りになるかも
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class UserDAO {
	//DB接続に必要な情報の準備(テキスト通り)
	private final String JDBC_URL = "jdbc:postgresql://localhost:5432/kakeibo"; //SQLの教本参考
	//→PostgreSQLにJDBCを使って、ローカル(この)PCのポート番号5432(？)のkakeiboデータベース
	private final String DB_USER = "postgres"; //デフォルトのまま
	private final String DB_PASS = "8XF_post"; //自分で設定したもの
	
	//ユーザーは一人だけで、かつユーザー情報をViewに反映させる必要はないので返り値型はbooleanで問題ない(？)
	public boolean findUser(User user) { 
		//JDBCドライバ
		try {
			// postgreSQLのJDBCドライバを読み込み
			Class.forName("org.postgresql.Driver");
		} catch(ClassNotFoundException e) { 
			// ドライバが見つからない場合、forNameメソッドはClassNotFoundExceptionをスローする
			//下の意味理解していない
			System.out.println("forName()がClassNotFoundExceptionエラーをスロー");
			//エラー内容を表示させるだけ
			e.printStackTrace();
		}
		
		//DBへ接続(テンプレ)
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)) {
			//SELECT文の準備
			//パラメータとしてあとから値を指定する部分には「？」を記述する
			String sql = "SELECT USERNAME,PASSWORD FROM USERS WHERE USERNAME = ? AND PASSWORD = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql); //テンプレ
			//ひとつめの？には入力されたユーザー名を
			pStmt.setString(1, user.getName());
			//ふたつめの？にはパスワードをそれぞれSQL文にセット
			pStmt.setString(2, user.getPass());
			
			//SELECT文を実行し、ResultSetオブジェクトが返ってくるのでその型で受け取る(テンプレ)
			ResultSet rs = pStmt.executeQuery();
			
			//インデックス-1の次を呼び出すことになるから、インデックス０が呼び出されるイメージ
			//return rs.next();としても可(if文を使うとやや冗長)
			if(rs.next()) {
				//データがDBに見つかったのでtrueを返す
				return true;
			}
			//上記のtrueを返す処理がif文の中にあることで発生するコンパイルエラーを回避するため
			return false;
		}catch(SQLException e) {
			return false;
		}
	}
	
}
