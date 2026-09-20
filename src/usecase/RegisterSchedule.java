package usecase;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import domain.PreparationItem;
import domain.Schedule;
import repository.IdGenerator;
import repository.ScheduleRepository;

// 新しい予定と準備物を登録する処理を行うクラス
public class RegisterSchedule {
    private final ScheduleRepository repository;
    private final IdGenerator idGenerator; // 変数名は小文字のcamelCaseが一般的です
    
    public RegisterSchedule(ScheduleRepository repository, IdGenerator idGenerator) {
        this.repository = repository;
        this.idGenerator = idGenerator;
    }
    
    // パラメータを受け取り予定と準備物を登録する
    public void execute(String title, String description, LocalDateTime dateTime, List<PreparationItemDto> itemDtos) {
        //スケジュール用のIDを自動採番
        int scheduleId = idGenerator.generateId();
        
        //準備物DTOのリストをドメインモデルのリストに変換しつつIDを採番）
        List<PreparationItem> items = new ArrayList<>();
        if (itemDtos != null) {
            for (PreparationItemDto dto : itemDtos) {
                int itemId = idGenerator.generateId();
                // 例として、新規登録時は個数1、未準備(false)で作成する
                items.add(new PreparationItem(itemId, dto.getName(), 1, false));
            }
   }
        

     // 3. スケジュールエンティティの作成
    Schedule schedule = new Schedule(scheduleId, title, description, dateTime, items);

        // 4. リポジトリに保存
        repository.save(schedule);
    }
}