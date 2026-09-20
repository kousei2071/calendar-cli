package presentation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import domain.Schedule;
import repository.IdGenerator;
import repository.ScheduleRepository;
import usecase.DeleteSchedule;
import usecase.EditSchedule;
import usecase.FindNextSchedule;
import usecase.ListSchedules;
import usecase.PreparationItemDto;
import usecase.RegisterSchedule;

// アプリケーションを起動しメインを実行するクラス
public class Main {
    static Scanner scan = new Scanner(System.in);
    
    // リポジトリ、IDジェネレーター、および各ユースケースのインスタンス化（依存性の注入）
    private static final ScheduleRepository repository = new ScheduleRepository();
    private static final IdGenerator idGenerator = new IdGenerator();
    private static final RegisterSchedule registerSchedule = new RegisterSchedule(repository, idGenerator);
    private static final ListSchedules listSchedules = new ListSchedules(repository);
    private static final FindNextSchedule findNextSchedule = new FindNextSchedule(repository);
    private static final EditSchedule editSchedule = new EditSchedule(repository);
    private static final DeleteSchedule deleteSchedule = new DeleteSchedule(repository);

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void main(String[] args) {
        showTitle();
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
        System.out.println("[3] 直近の予定を検索");
        System.out.println("[4] 予定の編集");
        System.out.println("[5] 予定の削除");
        System.out.println("[0] 終了");
        System.out.println();
    }
    
    static void run() {
        boolean running = true;
        
        while (running) {
            showMenu();
            System.out.print("番号を入力してください > ");
            int select = scan.nextInt();
            scan.nextLine(); // 改行文字を消費
            
            try {
                switch(select) {
                    case 1: {
                        System.out.print("タイトルを入力してください > ");
                        String title = scan.nextLine();
                        System.out.print("詳細を入力してください > ");
                        String description = scan.nextLine();
                        System.out.print("日時を入力してください (例: 2026-10-01 10:30) > ");
                        String dtStr = scan.nextLine();
                        LocalDateTime dateTime = LocalDateTime.parse(dtStr, FORMATTER);
                        // 準備物リスト（今回は空リストで登録）
                        List<PreparationItemDto> itemDtos = new ArrayList<>();

                        registerSchedule.execute(title, description, dateTime, itemDtos);
                        System.out.println("予定を登録しました！\n");
                        break;
                    }
                    case 2: {
                        List<Schedule> schedules = listSchedules.execute();
                        if (schedules.isEmpty()) {
                            System.out.println("登録されている予定はありません。\n");
                        } else {
                            System.out.println("--- 予定一覧 ---");
                            for (Schedule s : schedules) {
                                System.out.println(String.format("[%d] %s (%s) - %s", 
                                    s.getId(), s.getTitle(), s.getDateTime().format(FORMATTER), s.getDescription()));
                            }
                            System.out.println();
                        }
                        break;
                    }
                    case 3: {
                        System.out.print("何件取得しますか？ > ");
                        int limit = scan.nextInt();
                        scan.nextLine();
                        
                        // FindNextScheduleの呼び出し
                        List<Schedule> nextSchedules = findNextSchedule.execute(limit);
                        if (nextSchedules.isEmpty()) {
                            System.out.println("以降の予定はありません。\n");
                        } else {
                            System.out.println("--- 直近の予定 ---");
                            for (Schedule s : nextSchedules) {
                                System.out.println(String.format("[%d] %s (%s)", 
                                    s.getId(), s.getTitle(), s.getDateTime().format(FORMATTER)));
                            }
                            System.out.println();
                        }
                        break;
                    }
                    case 4: {
                        System.out.print("編集する予定のIDを入力してください > ");
                        int id = scan.nextInt();
                        scan.nextLine();
                        System.out.print("新しいタイトルを入力してください > ");
                        String title = scan.nextLine();
                        System.out.print("新しい詳細を入力してください > ");
                        String description = scan.nextLine();
                        System.out.print("新しい日時を入力してください (例: 2026-10-01 10:30) > ");
                        String dtStr = scan.nextLine();
                        LocalDateTime dateTime = LocalDateTime.parse(dtStr, FORMATTER);

                        editSchedule.execute(id, title, description, dateTime);
                        System.out.println("予定を更新しました！\n");
                        break;
                    }
                    case 5: {
                        System.out.print("削除する予定のIDを入力してください > ");
                        int id = scan.nextInt();
                        scan.nextLine();
                        deleteSchedule.execute(id);
                        System.out.println("予定を削除しました！\n");
                        break;
                    }
                    case 0: {
                        System.out.println("アプリを終了します。お疲れ様でした！");
                        running = false;
                        break;
                    }
                    default: {
                        System.out.println("無効な選択肢です。0〜5の間で選択してください。\n");
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("エラーが発生しました: " + e.getMessage() + "\n");
            }
        }
    }
}