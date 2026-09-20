package usecase;


import java.time.LocalDateTime;
import java.util.Optional;

import domain.Schedule;
import repository.ScheduleRepository;

//指定されたIDの予定内容を更新する処理を行うクラス
public class EditSchedule {
    private final ScheduleRepository repository;

    public EditSchedule(ScheduleRepository repository) {
        if (repository == null) {
            throw new IllegalArgumentException("リポジトリはnullにできません。");
        }
        this.repository = repository;
    }

    // 指定IDの予定を検索し、タイトルや日時などを変更して更新する
    public void execute(int id, String title, String description, LocalDateTime dateTime) {
    	
        //リポジトリから対象のスケジュールをIDで検索する
    	Optional<Schedule> optionalSchedule = repository.findById(id);

    	// 2中身が空（存在しない）かどうかをチェックする
    	if (optionalSchedule.isEmpty()) {
    	    throw new IllegalArgumentException("指定されたIDの予定が見つかりません ID: " + id);
    	}

    	//中身を取り出してエンティティの値を変更する
    	Schedule schedule = optionalSchedule.get();
    	schedule.setTitle(title);
    	schedule.setDescription(description);
    	schedule.setDateTime(dateTime);

    	//リポジトリに保存する
    	repository.save(schedule);
    }
}

