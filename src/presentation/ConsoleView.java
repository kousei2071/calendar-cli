package presentation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import domain.Schedule;
import usecase.DeleteSchedule;
import usecase.EditSchedule;
import usecase.FindNextSchedule;
import usecase.ListSchedules;
import usecase.RegisterSchedule;

public class ConsoleView {
    private final Scanner scanner;
    
    // 複数の日時フォーマットに対応
    private static final List<DateTimeFormatter> FORMATTERS = List.of(
        DateTimeFormatter.ofPattern("yyyy-M-d H:m"),  
        DateTimeFormatter.ofPattern("yyyyMMddHHmm")   
    );
    
    // 表示用のフォーマッター（一覧表示時などはキレイにゼロ埋め表示）
    private static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public ConsoleView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void title() {
        System.out.println("\n================================");
        System.out.println("            じかんぷらす            ");
        System.out.println("================================\n");
    }

    public void menu() {
        System.out.println("[1] 予定の登録");
        System.out.println("[2] 予定の一覧表示");
        System.out.println("[3] 直近の予定を検索");
        System.out.println("[4] 予定の編集");
        System.out.println("[5] 予定の削除");
        System.out.println("[0] 終了\n");
    }

    // 複数のフォーマッターを順番に試してパースするヘルパーメソッド
    private LocalDateTime parseDateTime(String input) {
        String trimmedInput = input.trim();
        for (DateTimeFormatter formatter : FORMATTERS) {
            try {
                return LocalDateTime.parse(trimmedInput, formatter);
            } catch (DateTimeParseException e) {
                // 次のフォーマットを試すため、ここでは例外を無視してループを継続
            }
        }
        // すべてのフォーマットに一致しなかった場合は例外を投げる
        throw new DateTimeParseException("日時の形式が正しくありません。", trimmedInput, 0);
    }

    // 予定登録(1)の処理
    public void register(RegisterSchedule uc) {
        System.out.print("予定を入力してください > ");
        String title = scanner.nextLine();

        System.out.print("持ち物を入力してください > ");
        String desc = scanner.nextLine();

        System.out.print("日時を入力してください (例: 2026-9-30 9:30 または 2026930930) > ");
        String input = scanner.nextLine();
        LocalDateTime dt = parseDateTime(input);

        uc.execute(title, desc, dt, new ArrayList<>());
        System.out.println("予定を登録しました！\n");
    }

    // 予定一覧表示(2)の処理
    public void list(ListSchedules uc) {
        List<Schedule> list = uc.execute();
        if (list.isEmpty()) {
            System.out.println("登録されている予定はありません。\n");
            return;
        }
        System.out.println("--- 予定一覧 ---");
        for (Schedule s : list) {
            System.out.println("[" + s.getId() + "] 予定: " + s.getTitle() + " [持ち物: " + s.getDescription() + "] (" + s.getDateTime().format(DISPLAY_FORMATTER) + ")");
        }
        System.out.println();
    }

    // 直近の予定検索(3)の処理
    public void search(FindNextSchedule uc) {
        System.out.print("何件取得しますか？ > ");
        int limit = Integer.parseInt(scanner.nextLine());

        List<Schedule> list = uc.execute(limit);
        if (list.isEmpty()) {
            System.out.println("以降の予定はありません。\n");
            return;
        }
        System.out.println("--- 直近の予定 ---");
        for (Schedule s : list) {
            System.out.println("[" + s.getId() + "] 予定: " + s.getTitle() + " [持ち物: " + s.getDescription() + "] (" + s.getDateTime().format(DISPLAY_FORMATTER) + ")");
        }
        System.out.println();
    }
    
    // 予定編集(4)の処理
    public void edit(ListSchedules listUc, EditSchedule editUc) {
        List<Schedule> list = listUc.execute();
        if (list.isEmpty()) {
            System.out.println("登録されている予定はありません。\n");
            return;
        }

        System.out.println("--- 現在の予定一覧 ---");
        for (Schedule s : list) {
            System.out.println("[" + s.getId() + "] 予定: " + s.getTitle() + " [持ち物: " + s.getDescription() + "] (" + s.getDateTime().format(DISPLAY_FORMATTER) + ")");
        }
        System.out.println();

        System.out.print("編集する予定のIDを入力してください > ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("新しい予定を入力してください > ");
        String title = scanner.nextLine();
        System.out.print("新しい持ち物を入力してください > ");
        String desc = scanner.nextLine();
        
        System.out.print("新しい日時を入力してください (例: 2026-9-30 9:30 または 2026930930) > ");
        String input = scanner.nextLine();
        LocalDateTime dt = parseDateTime(input);

        editUc.execute(id, title, desc, dt);
        System.out.println("予定を更新しました！\n");
    }

    // 予定削除(5)の処理
    public void delete(ListSchedules listUc, DeleteSchedule deleteUc) {
        List<Schedule> list = listUc.execute();
        if (list.isEmpty()) {
            System.out.println("登録されている予定はありません。\n");
            return;
        }

        System.out.println("--- 現在の予定一覧 ---");
        for (Schedule s : list) {
            System.out.println("[" + s.getId() + "] 予定: " + s.getTitle() + " [持ち物: " + s.getDescription() + "] (" + s.getDateTime().format(DISPLAY_FORMATTER) + ")");
        }
        System.out.println();

        System.out.print("削除する予定のIDを入力してください > ");
        int id = Integer.parseInt(scanner.nextLine());

        deleteUc.execute(id);
        System.out.println("予定を削除しました！\n");
    }
}