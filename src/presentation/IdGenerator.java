package presentation;

//予定や準備物に重複しない唯一のIDを発行するクラス
public class IdGenerator {
	private static int currentId = 0;

	// IDを生成するメソッド
	public static int generateId() {
		return ++currentId;
	}
}
