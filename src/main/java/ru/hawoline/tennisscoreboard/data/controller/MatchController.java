package ru.hawoline.tennisscoreboard.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.hawoline.tennisscoreboard.data.entity.MatchMapper;
import ru.hawoline.tennisscoreboard.domain.MatchService;
import ru.hawoline.tennisscoreboard.domain.exception.DuplicateMatchException;
import ru.hawoline.tennisscoreboard.domain.exception.MatchNotFoundException;
import ru.hawoline.tennisscoreboard.domain.exception.PlayerNotFoundException;
import ru.hawoline.tennisscoreboard.domain.model.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/matches")
public class MatchController {
    private MatchService matchService;
    private MatchMapper matchMapper = new MatchMapper();

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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

    @PostMapping(path = "/{uuid}/point", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody CurrentMatchResponse addPoint(@PathVariable("uuid") String uuid, @RequestBody AddPointRequest addPointRequest) {
        if (addPointRequest.getName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Empty player name");
        }
        try {
            Match match = matchService.addPoint(uuid, addPointRequest.getName());
            return matchMapper.toResponse(match);
        } catch (PlayerNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Player not found");
        } catch (MatchNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Match not found");
        }
    }

    @GetMapping(path = "/{uuid}")
    public @ResponseBody CurrentMatchResponse getCurrentMatchBy(@PathVariable("uuid") String uuid) {
        if (uuid.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "empty uuid");
        }
        Match match = matchService.getMatchBy(uuid);
        return matchMapper.toResponse(match);
    }

    @GetMapping()
    public @ResponseBody CompletedMatchesResponse getCompletedMatches(
            @RequestParam(name = "page", defaultValue = "-1") int page,
            @RequestParam(name = "player_name", required = false) String playerName
    ) {
        if (playerName == null) {
            List<Match> completed = matchService.getCompletedMatches();
            List<CompletedMatchResponse> completedMatchResponses = matchMapper.toCompletedMatchResponses(completed);
            if (page == -1) {
                return new CompletedMatchesResponse(completedMatchResponses, 0, 1);
            }

            CompletedMatchesResponse completedMatchesPage = getFromPage(completedMatchResponses, page);
            return completedMatchesPage;
        }

        List<Match> completedByPlayer = matchService.findByPlayerName(playerName);
        List<CompletedMatchResponse> completedMatchResponses = matchMapper.toCompletedMatchResponses(completedByPlayer);
        if (page == -1) {
            return new CompletedMatchesResponse(completedMatchResponses, 0, 1);
        }

        CompletedMatchesResponse completedMatchesPage = getFromPage(completedMatchResponses, page);
        return completedMatchesPage;
    }

    private CompletedMatchesResponse getFromPage(List<CompletedMatchResponse> completedMatchResponses, int page) {
        int countInPage = 10;
        int size = completedMatchResponses.size();
        if (size <= countInPage) {
            return new CompletedMatchesResponse(completedMatchResponses, 0, 1);
        }
        List<CompletedMatchResponse> result = new ArrayList<>();
        int begin = countInPage * page;
        if (begin > size) {
            for (int i = 0; i < countInPage; i++) {
                result.add(completedMatchResponses.get(size - i - 1));
            }
        } else {
            for (int i = 0; i < countInPage; i++) {
                result.add(completedMatchResponses.get(begin + i));
            }
        }

        return new CompletedMatchesResponse(result, page, size / countInPage);
    }
}
