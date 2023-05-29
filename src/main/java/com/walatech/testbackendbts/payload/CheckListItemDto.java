package com.walatech.testbackendbts.payload;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CheckListItemDto {

    private Long id;
    @NotEmpty(message = "Name should not be null or empty")
    private String itemName;
}
