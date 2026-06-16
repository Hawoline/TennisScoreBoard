package ru.hawoline.tennisscoreboard.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.hawoline.tennisscoreboard.domain.Controller;
import ru.hawoline.tennisscoreboard.domain.model.Player;
import ru.hawoline.tennisscoreboard.domain.service.NewMatchService;

@org.springframework.stereotype.Controller
@RequestMapping("/new-match")
public class NewMatchController implements Controller {
    private NewMatchService newMatchService;

    @Autowired
    public NewMatchController(NewMatchService newMatchService) {
        this.newMatchService = newMatchService;
    }

    public void createPlayer(Player player) {
    }

    @PostMapping
    public String newMatch() {
        return "match/new";
    }

}
