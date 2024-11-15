
package com.cloudthat.bankingapp.exceptions;

public class MissingAuthorizationHeaderException extends Throwable {
    public MissingAuthorizationHeaderException(String message) {
        super(message);
    }
}
