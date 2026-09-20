package usecase;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import domain.Schedule;
import repository.ScheduleRepository;

//現在日時から直近N件の予定を検索・取得する処理を行うクラス
public class FindNextSchedule {
		private final ScheduleRepository repository;

	public FindNextSchedule(ScheduleRepository repository) {
		if (repository == null) {
			throw new IllegalArgumentException("リポジトリはnullにできません。");
		}
		this.repository = repository;
	}

	// 登録されている予定の一覧を取得するメソッド
	public List<Schedule> execute(int limit) {
        if (limit < 1) {
            throw new IllegalArgumentException("取得件数は1以上である必要があります。");
        }

        // 現在日時を取得
        LocalDateTime now = LocalDateTime.now();
        List<Schedule> allSchedules = repository.findAll();
        List<Schedule> futureSchedules = new ArrayList<>();
        
     //現在日時以降の予定を拡張for文で抽出
        for (Schedule schedule : allSchedules) {
            if (!schedule.getDateTime().isBefore(now)) {
                futureSchedules.add(schedule);
            }
        }
     //日時の昇順でソート（Comparatorを使用します）
        futureSchedules.sort(Comparator.comparing(Schedule::getDateTime));   
        
        
     //指定された件数（limit）までを新しいリストに詰める
        List<Schedule> result = new ArrayList<>();
        for (int i = 0; i < futureSchedules.size() && i < limit; i++) {
            result.add(futureSchedules.get(i));
        }

        return result;
	}
}
