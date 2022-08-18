package com.cl.evaluacion.register.services;

import com.cl.evaluacion.register.models.ErrorModel;
import org.springframework.stereotype.Service;

@Service
public class ExceptionHandlerService {

    public ErrorModel getError(Exception e) {
        ErrorModel errorModel = new ErrorModel();
        errorModel.setMessage(e.getLocalizedMessage());
        return errorModel;
    }
}
