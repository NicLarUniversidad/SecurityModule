package com.cl.evaluation.register.models;

import lombok.Data;

@Data
public class ResponseModel<T> {
    private T data;
    private ErrorModel error;

    public void setError(ErrorModel error) {
        this.error = error;
    }
    public void setError(String error) {
        var errorModel = new ErrorModel();
        errorModel.setMessage(error);
        setError(errorModel);
    }
}
