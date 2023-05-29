package com.walatech.testbackendbts.service.impl;

import com.walatech.testbackendbts.entity.CheckList;
import com.walatech.testbackendbts.exception.ResourceNotFoundException;
import com.walatech.testbackendbts.payload.CheckListDto;
import com.walatech.testbackendbts.repository.CheckListRepository;
import com.walatech.testbackendbts.service.CheckListService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CheckListServiceImpl implements CheckListService {

    @Autowired
    private CheckListRepository checkListRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public CheckListDto createCheckList(CheckListDto checkListDto) {

        CheckList checkList = mapToEntity(checkListDto);
        CheckList newCheckList = checkListRepository.save(checkList);

        // convert entity to DTO
        CheckListDto checkListResponse = mapToDTO(newCheckList);
        return checkListResponse;
    }

    @Override
    public List<CheckListDto> getAllCheckLists() {
        List<CheckList> checkLists = checkListRepository.findAll();
        return checkLists.stream().map(checkList -> mapToDTO(checkList)).collect(Collectors.toList());
    }

    @Override
    public void deleteCheckListById(long id) {
        CheckList checkList = checkListRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CheckList", "id", id));
        checkListRepository.delete(checkList);
    }

    private CheckListDto mapToDTO(CheckList checkList){
        CheckListDto checkListDto = mapper.map(checkList, CheckListDto.class);
        return checkListDto;
    }

    // convert DTO to entity
    private CheckList mapToEntity(CheckListDto checkListDto){
        CheckList checkList = mapper.map(checkListDto, CheckList.class);
        return checkList;
    }
}
