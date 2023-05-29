package com.walatech.testbackendbts.payload;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Set;

@Data
public class CheckListDto {

    private Long id;
    @NotEmpty
    private String name;

    private Set<CheckListDto> checklistitems;
}
