package com.cl.evaluation.register.models;

import lombok.Data;

@Data
public class ResponseModel<T> {
    private T data;
    private ErrorModel error;
}
