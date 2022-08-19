package com.cl.evaluation.register.services;

import com.cl.evaluation.register.models.ErrorModel;
import org.springframework.stereotype.Service;

@Service
public class ExceptionHandlerService {

    public ErrorModel getError(Exception e) {
        ErrorModel errorModel = new ErrorModel();
        errorModel.setMessage(e.getLocalizedMessage());
        return errorModel;
    }
}
