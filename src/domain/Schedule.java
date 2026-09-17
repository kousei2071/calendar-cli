package domain;

import java.time.LocalDateTime;
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
		this.dateTime = dateTime;
	}


	public List<PreparationItem> getItems() {
		return Items;
	}

}
