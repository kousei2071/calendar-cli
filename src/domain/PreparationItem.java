package domain;

//準備物の情報（名前・個数・準備状態等）を保持するクラス
public class PreparationItem extends AbstractEntity{
	
	private String name; //準備物の名前
	private int quantity; //準備物の個数
	private boolean isPrepared; //準備物の準備状態（true:準備済み、false:未準備）
	
	public PreparationItem(String name, int quantity, boolean isPrepared) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.isPrepared = isPrepared;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean isPrepared() {
		return isPrepared;
	}

	public void setPrepared(boolean isPrepared) {
		this.isPrepared = isPrepared;
	}
	
	
}
