package usecase;

import repository.ScheduleRepository;


//指定されたIDの予定を削除する処理を行うクラス
public class DeleteSchedule {
    private final ScheduleRepository repository;

    public DeleteSchedule(ScheduleRepository repository) {
        if (repository == null) {
            throw new IllegalArgumentException("リポジトリはnullにできません。");
        }
        this.repository = repository;
    }

    // 指定IDの予定を削除するメソッド
    public void execute(int id) {
        // リポジトリのdeleteByIdを呼び出し、削除が成功したかを確認する
        boolean isDeleted = repository.deleteById(id);
        
        if (!isDeleted) {
            throw new IllegalArgumentException("指定されたIDの予定が見つかりません ID: " + id);
        }
    }
}
