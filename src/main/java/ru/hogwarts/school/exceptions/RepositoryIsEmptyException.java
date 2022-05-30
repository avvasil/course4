package ru.hogwarts.school.exceptions;

public class RepositoryIsEmptyException extends RuntimeException {
    public RepositoryIsEmptyException(String message) {
        super(message);
    }
}
