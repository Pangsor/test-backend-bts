package com.walatech.testbackendbts.service;

import com.walatech.testbackendbts.payload.CheckListDto;

import java.util.List;

public interface CheckListService {

    CheckListDto createCheckList(CheckListDto checkListDto);
    List<CheckListDto> getAllCheckLists();
    void deleteCheckListById(long id);
}
