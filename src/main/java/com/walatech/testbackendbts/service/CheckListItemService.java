package com.walatech.testbackendbts.service;

import com.walatech.testbackendbts.payload.CheckListItemDto;

import java.util.List;

public interface CheckListItemService {
    CheckListItemDto createCheckListItem(long checkListId, CheckListItemDto checkListItemDto);
    List<CheckListItemDto> getCheckListItemByCheckListId(long checkListId);
    List<CheckListItemDto> getCheckListItemByChecklistIdAndId(long checkListId, Long id);
    CheckListItemDto updateStatusByChecklistIdAndId(long checkListId, Long id);
    void deleteByChecklistIdAndId(Long checklistId,Long id);
    CheckListItemDto renameItemByChecklistIdAndId(Long checklistId,Long id, CheckListItemDto checkListItemDto);
}
