package repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
//itateratorはjavasilverのAPIにあるやつです。拡張for文を使うと同時変更例外が起きてしまうらしいです。
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import domain.Schedule;

// 予定データをメモリ上で保持・検索・削除等を行うクラス
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
	//イタレーターについて（拡張for文を使うと同時変更例外が起きてしまうらしいです）
	public boolean deleteById(int id) {
		Iterator<Schedule> iterator = schedules.iterator();
		while (iterator.hasNext()) {
			Schedule schedule = iterator.next();
			if (schedule.getId() == id) {
				iterator.remove();
				return true;
			}
		}
		return false;
	}

	// idで予定を検索するメソッド（
	public Optional<Schedule> findById(int id) {
		for (Schedule schedule : schedules) {
			if (schedule.getId() == id) {
				return Optional.of(schedule);
			}
		}
		return Optional.empty();
	}

	// 期間で予定を検索するメソッド
	public List<Schedule> findBetween(LocalDateTime start, LocalDateTime end) {
		if (start == null || end == null) {
			throw new IllegalArgumentException("検索する日時はnullにできません");
		}
		
		List<Schedule> result = new ArrayList<>();
		for (Schedule schedule : schedules) {
			LocalDateTime dt = schedule.getDateTime();
			if (dt != null && !dt.isBefore(start) && !dt.isAfter(end)) {
				result.add(schedule);
			}
		}
		return result;
	}

	public List<Schedule> findAll() {
		return schedules;
	}
}