package com.phoenix.Phoenix.rest;

import com.phoenix.Phoenix.dto.utility.ValidationDTO;
import com.phoenix.Phoenix.utility.MapperHelper;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRestController {
    public List<ValidationDTO> getErrors(List<ObjectError> errors){
        var dto = new ArrayList<ValidationDTO>();
        for(var error : errors){
            var fieldName = MapperHelper.getStringField(error.getArguments()[0], "defaultMessage");
            fieldName = (fieldName.equals("")) ? "object" : fieldName;
            var message = error.getDefaultMessage();
            dto.add(new ValidationDTO(fieldName, message));
        }
        return dto;
    }
}
