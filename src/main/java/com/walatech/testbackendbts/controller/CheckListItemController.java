package com.walatech.testbackendbts.controller;

import com.walatech.testbackendbts.payload.CheckListItemDto;
import com.walatech.testbackendbts.service.CheckListItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CheckListItemController {

    @Autowired
    private CheckListItemService checkListItemService;

    @PostMapping("/checklist/{checkListId}/item")
    public ResponseEntity<CheckListItemDto> createCheckListItem(@PathVariable(value = "checkListId") long checkListId,
                                                                @Valid @RequestBody CheckListItemDto checkListItemDto){
        return new ResponseEntity<>(checkListItemService.createCheckListItem(checkListId, checkListItemDto), HttpStatus.CREATED);
    }

    @GetMapping("/checklist/{checkListId}/item")
    public List<CheckListItemDto> getCheckListItemByCheckListId(@PathVariable(value = "checkListId") Long checkListId){
        return checkListItemService.getCheckListItemByCheckListId(checkListId);
    }

    @GetMapping("/checklist/{checkListId}/item/{checklistItemId}")
    public List<CheckListItemDto> getCheckListItemByChecklistIdAndId(@PathVariable(value = "checkListId") Long checkListId,
                                                                     @PathVariable(value = "checklistItemId") Long id){
        return checkListItemService.getCheckListItemByChecklistIdAndId(checkListId, id);
    }

    @DeleteMapping("/checklist/{checkListId}/item/{checklistItemId}")
    public ResponseEntity<String> deleteCheckListItem(@PathVariable(value = "checkListId") Long checkListId,
                                                @PathVariable(value = "checklistItemId") Long id){
        checkListItemService.deleteByChecklistIdAndId(checkListId, id);
        return new ResponseEntity<>("Check List deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/checklist/{checkListId}/item/{checklistItemId}")
    public ResponseEntity<CheckListItemDto> updateCheckListItem(@PathVariable(value = "checkListId") Long checkListId,
                                                    @PathVariable(value = "checklistItemId") Long id,
                                                    @Valid @RequestBody CheckListItemDto checkListItemDto){
        CheckListItemDto updatedCheckListItem = checkListItemService.renameItemByChecklistIdAndId(checkListId, id, checkListItemDto);
        return new ResponseEntity<>(updatedCheckListItem, HttpStatus.OK);
    }

//    @PutMapping("/checklist/{checkListId}/item/{checklistItemId}")
//    public ResponseEntity<CheckListItemDto> updateStatus(@PathVariable(value = "checkListId") Long checkListId,
//                                                                @PathVariable(value = "checklistItemId") Long id){
//        CheckListItemDto updatedCheckListItem = checkListItemService.updateStatusByChecklistIdAndId(checkListId, id);
//        return new ResponseEntity<>(updatedCheckListItem, HttpStatus.OK);
//    }
}
