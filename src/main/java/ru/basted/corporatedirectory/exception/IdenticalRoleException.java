package ru.basted.corporatedirectory.exception;

public class IdenticalRoleException extends RuntimeException {
    public IdenticalRoleException(String message) {
        super(message);
    }
}
