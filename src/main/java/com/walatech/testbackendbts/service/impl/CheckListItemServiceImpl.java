package com.walatech.testbackendbts.service.impl;

import com.walatech.testbackendbts.entity.CheckList;
import com.walatech.testbackendbts.entity.CheckListItem;
import com.walatech.testbackendbts.exception.BlogAPIException;
import com.walatech.testbackendbts.exception.ResourceNotFoundException;
import com.walatech.testbackendbts.payload.CheckListItemDto;
import com.walatech.testbackendbts.repository.CheckListItemRepository;
import com.walatech.testbackendbts.repository.CheckListRepository;
import com.walatech.testbackendbts.service.CheckListItemService;
import com.walatech.testbackendbts.utils.AppConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CheckListItemServiceImpl implements CheckListItemService {

    @Autowired
    private CheckListItemRepository checkListItemRepository;
    @Autowired
    private CheckListRepository checkListRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public CheckListItemDto createCheckListItem(long checkListId, CheckListItemDto checkListItemDto) {

        CheckListItem checkListItem = mapToEntity(checkListItemDto);

        CheckList checkList = checkListRepository.findById(checkListId).orElseThrow(
                () -> new ResourceNotFoundException("CheckList", "id", checkListId));

        checkListItem.setChecklist(checkList);

        // comment entity to DB
        CheckListItem newCheckListItem =  checkListItemRepository.save(checkListItem);

        return mapToDTO(newCheckListItem);
    }

    @Override
    public List<CheckListItemDto> getCheckListItemByCheckListId(long checkListId) {
        List<CheckListItem> checkListItems = checkListItemRepository.findByChecklistId(checkListId);

        return checkListItems.stream().map(checkListItem -> mapToDTO(checkListItem)).collect(Collectors.toList());
    }

    @Override
    public List<CheckListItemDto> getCheckListItemByChecklistIdAndId(long checkListId, Long id) {
        List<CheckListItem> checkListItems = checkListItemRepository.findByChecklistIdAndId(checkListId, id);

        return checkListItems.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public CheckListItemDto updateStatusByChecklistIdAndId(long checkListId, Long id) {
        CheckList checkList = checkListRepository.findById(checkListId).orElseThrow(
                () -> new ResourceNotFoundException("CheckList", "id", checkListId));

        CheckListItem checkListItem = checkListItemRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("CheckListItem", "id", id));

        if(!checkListItem.getChecklist().getId().equals(checkList.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "CheckListItem does not belongs to CheckList");
        }

        checkListItem.setStatus(true);

        CheckListItem updatedCheckListItem = checkListItemRepository.save(checkListItem);
        return mapToDTO(updatedCheckListItem);
    }

    @Override
    public void deleteByChecklistIdAndId(Long checklistId, Long id) {
        CheckList checkList = checkListRepository.findById(checklistId).orElseThrow(
                () -> new ResourceNotFoundException("Checklist", "id", checklistId));

        CheckListItem checkListItem = checkListItemRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("ChecklistItem", "id", id));

        if(!checkListItem.getChecklist().getId().equals(checkList.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "ChecklistItem does not belongs to checklist");
        }

        checkListItemRepository.delete(checkListItem);

    }

    @Override
    public CheckListItemDto renameItemByChecklistIdAndId(Long checklistId, Long id, CheckListItemDto checkListItemDto) {
        CheckList checkList = checkListRepository.findById(checklistId).orElseThrow(
                () -> new ResourceNotFoundException("CheckList", "id", checklistId));

        CheckListItem checkListItem = checkListItemRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("CheckListItem", "id", id));

        if(!checkListItem.getChecklist().getId().equals(checkList.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "CheckListItem does not belongs to CheckList");
        }

        checkListItem.setItemName(checkListItemDto.getItemName());

        CheckListItem updatedCheckListItem = checkListItemRepository.save(checkListItem);
        return mapToDTO(updatedCheckListItem);
    }

    private CheckListItemDto mapToDTO(CheckListItem checkListItem){
        CheckListItemDto checkListItemDto = mapper.map(checkListItem, CheckListItemDto.class);
        return  checkListItemDto;
    }

    private CheckListItem mapToEntity(CheckListItemDto checkListItemDto){
        CheckListItem checkListItem = mapper.map(checkListItemDto, CheckListItem.class);
        return  checkListItem;
    }
}
