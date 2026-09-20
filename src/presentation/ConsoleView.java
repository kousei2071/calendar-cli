package presentation;

import java.util.Scanner;

// CLIの入出力を担当するクラス
public class ConsoleView {
    private final Scanner scanner = new Scanner(System.in);

    // 画面にメッセージを出力する
    public void show(String message) {
        System.out.println(message);
    }

    // 文字列の入力を受け取る（プロンプト表示付き）
    public String readString(String prompt) {
        System.out.print(prompt + " > ");
        return scanner.nextLine();
    }

    // 整数の入力を受け取る（プロンプト表示付き）
    public int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt + " > ");
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("無効な入力です。整数を入力してください。");
            }
        }
    }
}