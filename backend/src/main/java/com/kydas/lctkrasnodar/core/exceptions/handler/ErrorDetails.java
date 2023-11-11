package com.kydas.lctkrasnodar.core.exceptions.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@Accessors(chain = true)
public class ErrorDetails {
    private String code;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, Object> data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String stackTrace;
}
