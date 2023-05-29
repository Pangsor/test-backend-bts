package com.walatech.testbackendbts.controller;

import com.walatech.testbackendbts.payload.CheckListDto;
import com.walatech.testbackendbts.service.CheckListService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/checklist")
public class CheckListController {

    @Autowired
    private CheckListService checkListService;

    @PostMapping
    public ResponseEntity<CheckListDto> createCheckList(@Valid @RequestBody CheckListDto checkListDto){
        return new ResponseEntity<>(checkListService.createCheckList(checkListDto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<CheckListDto> getAllCheckLists(){
        return checkListService.getAllCheckLists();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCheckList(@PathVariable(name = "id") long id){

        checkListService.deleteCheckListById(id);

        return new ResponseEntity<>("CheckList deleted successfully.", HttpStatus.OK);
    }
}
