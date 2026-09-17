package repository;

//予定や準備物に重複しない唯一のIDを発行するクラス
public class IdGenerator {
	private static int currentId = 0;

	// IDを生成するメソッドGa
	public static int generateId() {
		return ++currentId;
	}
}
