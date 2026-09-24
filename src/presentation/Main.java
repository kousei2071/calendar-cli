package presentation;

import java.util.Scanner;

import repository.IdGenerator;
import repository.ScheduleRepository;
import usecase.DeleteSchedule;
import usecase.EditSchedule;
import usecase.FindNextSchedule;
import usecase.ListSchedules;
import usecase.RegisterSchedule;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsoleView view = new ConsoleView(scanner);

        ScheduleRepository repository = new ScheduleRepository();
        IdGenerator idGenerator = new IdGenerator();
        RegisterSchedule registerSchedule = new RegisterSchedule(repository, idGenerator);
        ListSchedules listSchedules = new ListSchedules(repository);
        FindNextSchedule findNextSchedule = new FindNextSchedule(repository);
        EditSchedule editSchedule = new EditSchedule(repository);
        DeleteSchedule deleteSchedule = new DeleteSchedule(repository);

        view.title();

        boolean running = true;

        while (running) {
            view.menu();
            
            System.out.print("番号を入力してください > ");
            int select = Integer.parseInt(scanner.nextLine());

            try {
                switch (select) {
                    case 1:
                    	// 予定登録
                        view.register(registerSchedule);
                        break;
                    case 2:
                    	// 予定一覧表示
                        view.list(listSchedules);
                        break;
                        //　予定検索
                    case 3:
                        view.search(findNextSchedule);
                        break;
                        // 予定編集
                    case 4:
                        view.edit(listSchedules, editSchedule);
                        break;
                        // 予定削除
                    case 5:
                        view.delete(listSchedules, deleteSchedule);
                        break;
                        // アプリ終了
                    case 0:
                        System.out.println("アプリを終了します。お疲れ様でした！");
                        running = false;
                        break;
                    default:
                    	// 例外
                        System.out.println("無効な選択肢です。0〜5の間で選択してください。\n");
                        break;
                }
            } catch (Exception e) {
                System.out.println("エラーが発生しました: " + e.getMessage() + "\n");
            }
        }
        scanner.close();
    }
}