package domain;

//各エンティティで共通利用する共通基底クラス
public abstract class AbstractEntity {
	int id;

	public int getId() {
		return id;
	}
}
