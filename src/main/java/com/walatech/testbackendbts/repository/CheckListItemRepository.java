package com.walatech.testbackendbts.repository;

import com.walatech.testbackendbts.entity.CheckListItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CheckListItemRepository extends JpaRepository<CheckListItem, Long> {

    List<CheckListItem> findByChecklistId(long checklistId);
    List<CheckListItem> findByChecklistIdAndId(long checklistId,Long id);
    Boolean existsByChecklistIdAndId(Long checklistId,Long id);
    CheckListItem getByChecklistIdAndId(long checklistId,Long id);
}
