package usecase;

import java.util.Scanner;


//アプリケーションを起動しメインを実行するクラス
public class Main  {
		static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		showTitle();
		showMenu();
		run();
	}

	static void showTitle() {
		System.out.println();
		System.out.println("================================");
		System.out.println("           じかんぷらす           ");
		System.out.println("================================");
		System.out.println();
	}
	
	static void showMenu() {
		System.out.println("[1] 予定の登録");
		System.out.println("[2] 予定の一覧表示");
		System.out.println("[3] 予定の編集");
		System.out.println("[4] 予定の削除");
		System.out.println("[0] 終了");
		System.out.println();
	}
	
	//今日までにここを完成させる。
	static void run() {
		System.out.print("番号を入力してください > ");
	
		int select = scan.nextInt();
		
		switch(select) {
		case 1: {
			
		}
		case 2: {
			
		}
		case 3: {
			
		}
		case 4: {
			
		}
		default: {
			//ここに例外処理を追加します
			System.out.println("");
		}
	}
	}
}


