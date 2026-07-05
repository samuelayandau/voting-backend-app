package com.voting.votingapp.service;

import com.voting.votingapp.model.OptionVote;
import com.voting.votingapp.model.Poll;
import com.voting.votingapp.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class PollService {
    @Autowired
    private PollRepository pollRepository;

    public Poll createPoll(Poll poll) {
        return pollRepository.save(poll);
    }


    public List<Poll> getAllPolls() {
        return pollRepository.findAll();
    }

    public Poll getPollById(Long id) {
        return pollRepository.findById(id).get();
    }

    public void vote(Long pollId, int optionIndex) {
        Poll poll = pollRepository.findById(pollId)
                .orElseThrow(() -> new RuntimeException("Poll not found"));
        List<OptionVote> options = poll.getOptions();

        if(optionIndex <0 || optionIndex >= options.size()){
            throw new IllegalArgumentException("Invalid option index");

        }

        OptionVote selectedOption= options.get(optionIndex);

        selectedOption.setVoteCount(selectedOption.getVoteCount()+1);

        pollRepository.save(poll);

    }
}
