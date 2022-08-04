package com.dongsamo.jogumanClone.network.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "잘못된 이메일 형식")
public class SendEmailException extends Exception{
    public SendEmailException(String message) {
        super(message);
    }
}
