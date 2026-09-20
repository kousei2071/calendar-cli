package usecase;

import java.util.List;

import domain.Schedule;
import repository.ScheduleRepository;

//登録されている予定の一覧を取得する処理を行うクラス
public class ListSchedules {
    private final ScheduleRepository repository;

    // リポジトリを受け取るコンストラクター
    public ListSchedules(ScheduleRepository repository) {
        if (repository == null) {
            throw new IllegalArgumentException("リポジトリはnullにできません。");
        }
        this.repository = repository;
    }

    // 一覧を取得して返すメソッド
    public List<Schedule> execute() {
        return repository.findAll();
    }
}
