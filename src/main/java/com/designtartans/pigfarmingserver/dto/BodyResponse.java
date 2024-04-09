package com.designtartans.pigfarmingserver.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BodyResponse {
    private HttpStatus statusCode;
    private Object result;
    private Boolean processed;
}
