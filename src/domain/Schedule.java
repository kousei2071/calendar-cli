package domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


//予定の情報（タイトル・日時・準備物等）を保持するクラス
public class Schedule extends AbstractEntity{
	private String title; //予定のタイトル
	private String description; //予定の詳細
	private LocalDateTime dateTime; //予定の日時
	private List<PreparationItem> Items; //予定に紐づく準備物
	
	
	public Schedule(String title, LocalDateTime dateTime, List<PreparationItem> Items) {
		super();
		this.title = title;
		this.dateTime = dateTime;
		this.Items = Items;
		
		
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		// タイトルがnull,空文字の場合はスロー
		if (title == null || title.isEmpty()) {
			throw new IllegalArgumentException("予定のタイトルは空にできません");
		}
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public LocalDateTime getDateTime() {
		return dateTime;
	}


	public void setDateTime(LocalDateTime dateTime) {
		// 日時がnullの場合はスロー
		if (dateTime == null) {
			throw new IllegalArgumentException("予定の日時はnullにできません");
		}
		this.dateTime = dateTime;
	}


	public List<PreparationItem> getItems() {
		return Items;
	}

	// 予定を追加するメソッド
	public void addItems(List<PreparationItem> Items) {
		// 準備物のリストがnullの場合はスロー
		if (Items == null) {
			throw new IllegalArgumentException("準備物のリストはnullにできません");
		}
		
		// 万が一リストがnullの場合の安全策
	    if (this.Items == null) {
	        this.Items = new ArrayList<>();
	    }
	    this.Items.add(Items);
		this.Items = Items;
	}
	
	// 予定を削除するメソッド
	public void removeItem(PreparationItem item) {
		if (item == null) {
			throw new IllegalArgumentException("削除する準備物はnullにできません");
		}
		this.Items.remove(item);
	}
	
	// 表示用の文字に変換するメソッド
	public String toDisplayString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		return "予定 [title=" + title + ", description=" + description + ", dateTime=" + dateTime + ", Items=" + Items + "]";
	}
}
