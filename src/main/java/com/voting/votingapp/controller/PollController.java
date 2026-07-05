package com.voting.votingapp.controller;


import com.voting.votingapp.model.Poll;
import com.voting.votingapp.request.Vote;
import com.voting.votingapp.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/polls")
public class PollController {
    @Autowired
    private  PollService pollService;
    @PostMapping

    public Poll createPoll(@RequestBody Poll poll){
        return pollService.createPoll(poll);
    }
    @GetMapping
    public List<Poll> getAllPolls() {
        return pollService.getAllPolls();

    }
    @GetMapping("/{id}")
    public Poll getPollById(@PathVariable Long id){
        return pollService.getPollById(id);
    }

    @PostMapping("/vote")
    public void vote(@RequestBody Vote vote){
        pollService.vote(vote.getPollId(),vote.getOptionIndex());
    }
}
