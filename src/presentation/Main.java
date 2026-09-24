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
                        view.register(registerSchedule);
                        break;
                    case 2:
                        view.list(listSchedules);
                        break;
                    case 3:
                        view.search(findNextSchedule);
                        break;
                    case 4:
                        view.edit(listSchedules, editSchedule);
                        break;
                    case 5:
                        view.delete(listSchedules, deleteSchedule);
                        break;
                    case 0:
                        System.out.println("アプリを終了します。お疲れ様でした！");
                        running = false;
                        break;
                    default:
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