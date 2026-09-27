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
    private final IdGenerator idGenerator; 
    public RegisterSchedule(ScheduleRepository repository, IdGenerator idGenerator) {
        this.repository = repository;
        this.idGenerator = idGenerator;
    }
    
    // パラメータを受け取り予定と準備物を登録する
    public void execute(String title, String description, LocalDateTime dateTime, List<PreparationItemDto> itemDtos) {
    
        int scheduleId = idGenerator.generateId();
        
        //準備物DTOのリストをドメインモデルのリストに変換
        List<PreparationItem> items = new ArrayList<>();
        if (itemDtos != null) {
            for (PreparationItemDto dto : itemDtos) {
                int itemId = idGenerator.generateId();
                
                // 例として、新規登録時は個数1、未準備で作成する
                items.add(new PreparationItem(itemId, dto.getName(), 1, false));
            }
   }
        
    Schedule schedule = new Schedule(scheduleId, title, description, dateTime, items);

        repository.save(schedule);
    }
}