package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeToString {
	/**
	 * LocalDateTime型の日時情報を「年-月-日」のString型に変換する
	 * 
	 * @param localDateTime 現在日時
	 * @param format フォーマット(型みたいなもの)
	 * @return String フォーマットの通りに変換された現在日時
	 */
	public static String convertToString(String format) {
		//ofPatternメソッド→引数で受け取ったパターンを用いてフォーマッター(型みたいなもの)を作成するメソッド
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
		//フォーマッターを使用して現在日時を書式設定してString型で返す
		return LocalDateTime.now().format(dateTimeFormatter);
	}
}
