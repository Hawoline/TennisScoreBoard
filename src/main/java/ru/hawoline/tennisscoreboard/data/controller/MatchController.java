package ru.hawoline.tennisscoreboard.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.hawoline.tennisscoreboard.domain.model.CurrentMatchResponse;
import ru.hawoline.tennisscoreboard.domain.model.Match;
import ru.hawoline.tennisscoreboard.data.service.MatchService;

@RestController
@RequestMapping("/matches")
public class MatchController {
    private MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping(consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String newMatch(@RequestBody Match match) {
        if (match.getFirstPlayerName().equals(match.getSecondPlayerName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Duplicate player");
        }
        if (match.getFirstPlayerName().isEmpty() || match.getSecondPlayerName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Empty player name");
        }
        return """
                {
                    "id": "%s"
                }""".formatted(matchService.newMatch(match));
    }

//    @PostMapping(name = "/{uuid}/point", consumes= MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public CurrentMatchResponse addPoint(@PathVariable String uuid, @RequestBody String name) {
//
//    }
}
