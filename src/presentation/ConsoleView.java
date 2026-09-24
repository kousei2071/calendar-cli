package presentation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public ConsoleView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void title() {
        System.out.println("\n================================");
        System.out.println("           じかんぷらす           ");
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

    // 予定登録(1)の処理
    public void register(RegisterSchedule uc) {
        System.out.print("予定を入力してください > ");
        String title = scanner.nextLine();

        System.out.print("持ち物を入力してください > ");
        String desc = scanner.nextLine();

        System.out.print("日時を入力してください (例: 2026-10-01 10:30) > ");
        LocalDateTime dt = LocalDateTime.parse(scanner.nextLine(), FORMATTER);

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
            System.out.println("[" + s.getId() + "] 予定: " + s.getTitle() + " [持ち物: " + s.getDescription() + "] (" + s.getDateTime().format(FORMATTER) + ")");
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
            System.out.println("[" + s.getId() + "] 予定: " + s.getTitle() + " [持ち物: " + s.getDescription() + "] (" + s.getDateTime().format(FORMATTER) + ")");
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
            System.out.println("[" + s.getId() + "] 予定: " + s.getTitle() + " [持ち物: " + s.getDescription() + "] (" + s.getDateTime().format(FORMATTER) + ")");
        }
        System.out.println();

        System.out.print("編集する予定のIDを入力してください > ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("新しい予定を入力してください > ");
        String title = scanner.nextLine();
        System.out.print("新しい持ち物を入力してください > ");
        String desc = scanner.nextLine();
        System.out.print("新しい日時を入力してください (例: 2026-10-01 10:30) > ");
        LocalDateTime dt = LocalDateTime.parse(scanner.nextLine(), FORMATTER);

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
            System.out.println("[" + s.getId() + "] 予定: " + s.getTitle() + " [持ち物: " + s.getDescription() + "] (" + s.getDateTime().format(FORMATTER) + ")");
        }
        System.out.println();

        System.out.print("削除する予定のIDを入力してください > ");
        int id = Integer.parseInt(scanner.nextLine());

        deleteUc.execute(id);
        System.out.println("予定を削除しました！\n");
    }
}