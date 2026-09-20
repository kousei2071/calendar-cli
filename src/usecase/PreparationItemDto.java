package usecase;

//画面から受け取る準備物データを転送するためのクラス
public class PreparationItemDto {
	private String name;
	
	public PreparationItemDto(String name) {
		if (name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("準備物の名前はnullまたは空文字にできません。");
		}
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
}
