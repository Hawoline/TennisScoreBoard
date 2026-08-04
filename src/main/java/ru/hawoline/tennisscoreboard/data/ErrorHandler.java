package ru.hawoline.tennisscoreboard.data;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.hawoline.tennisscoreboard.data.entity.ErrorResponse;
import ru.hawoline.tennisscoreboard.domain.exception.DuplicateMatchException;
import ru.hawoline.tennisscoreboard.domain.exception.MatchNotFoundException;
import ru.hawoline.tennisscoreboard.domain.exception.PlayerNotFoundException;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(DuplicateMatchException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateMatchException(DuplicateMatchException e) {
        ErrorResponse errorResponse = new ErrorResponse("Duplicate match");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MatchNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateMatchException(MatchNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse("Match not found");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateMatchException(PlayerNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse("Player not found");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateMatchException(NullPointerException e) {
        ErrorResponse errorResponse = new ErrorResponse("Current match not found");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
