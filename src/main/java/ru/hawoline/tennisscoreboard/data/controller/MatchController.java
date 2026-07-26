package ru.hawoline.tennisscoreboard.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.hawoline.tennisscoreboard.data.entity.MatchMapper;
import ru.hawoline.tennisscoreboard.domain.exception.DuplicateMatchException;
import ru.hawoline.tennisscoreboard.domain.exception.MatchNotFoundException;
import ru.hawoline.tennisscoreboard.domain.exception.PlayerNotFoundException;
import ru.hawoline.tennisscoreboard.domain.model.AddPointRequest;
import ru.hawoline.tennisscoreboard.domain.model.Match;
import ru.hawoline.tennisscoreboard.domain.model.CurrentMatchResponse;
import ru.hawoline.tennisscoreboard.domain.model.Opponents;
import ru.hawoline.tennisscoreboard.domain.MatchService;

import java.util.List;

@RestController
@RequestMapping("/matches")
public class MatchController {
    private MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping(consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String newMatch(@RequestBody Opponents opponents) {
        if (opponents.getFirstPlayerName().equals(opponents.getSecondPlayerName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Duplicate player");
        }
        if (opponents.getFirstPlayerName().isEmpty() || opponents.getSecondPlayerName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Empty player name");
        }
        try {
            return """
                    {
                        "id": "%s"
                    }""".formatted(matchService.newMatch(opponents));
        } catch (DuplicateMatchException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Match exists");
        }
    }

    @PostMapping(path = "/{uuid}/point", consumes= MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody CurrentMatchResponse addPoint(@PathVariable("uuid") String uuid, @RequestBody AddPointRequest addPointRequest) {
        if (addPointRequest.getName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Empty player name");
        }
        try {
            Match match = matchService.addPoint(uuid, addPointRequest.getName());
            return new MatchMapper().toResponse(match);
        } catch (PlayerNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Player not found");
        } catch (MatchNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Match not found");
        }
    }
}
