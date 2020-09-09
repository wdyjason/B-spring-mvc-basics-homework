package com.thoughtworks.capacity.gtb.mvc.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommonError {

    private int code;

    private String message;
}
