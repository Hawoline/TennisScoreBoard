package ru.hawoline.tennisscoreboard.domain;

public interface Validator<T> {
    boolean validate(T object);
}