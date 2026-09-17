package repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import domain.Schedule;

//予定データをメモリ上で保持・検索・削除等を行うクラス
public class ScheduleRepository {
	
	// 予定データを保持するリスト
	private List<Schedule> schedules = new ArrayList<>();

	// 予定を追加するメソッド
	public void save(Schedule schedule) {
		if (schedule == null) {
            throw new IllegalArgumentException("保存するスケジュールはnullにできません。");
        }
		deleteById(schedule.getId());
		schedules.add(schedule);
	}

	// 予定を削除するメソッド
	public boolean deleteById(int id) {
	     return schedules.removeIf(schedule -> schedule.getId() == id);
	}

	// idで予定を検索するメソッド
	public Optional<Schedule> findById(int id) {
		// IDが一致する予定を検索
			return schedules.stream()
				.filter(schedule -> schedule.getId() == id)
				.findFirst();
	}

	// 期間で予定を検索するメソッド
	public List<Schedule> findBetween(LocalDateTime start, LocalDateTime end) {
		//例外処理
		if (start == null || end == null) {
			throw new IllegalArgumentException("検索する日付はnullにできません");
		}
		return schedules.stream()
				.filter(schedule -> !schedule.getDateTime().isBefore(start) && !schedule.getDateTime().isAfter(end))
				.collect(Collectors.toList());
	}
}
