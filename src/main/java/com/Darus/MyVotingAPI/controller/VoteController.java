package com.Darus.MyVotingAPI.controller;

import com.Darus.MyVotingAPI.model.Vote;
import com.Darus.MyVotingAPI.repositories.VotingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class VoteController {
    @Autowired
    private VotingRepository votingRepository;

    @RequestMapping(value = "/polls/{pollId}/votes",method = RequestMethod.POST)
    public ResponseEntity<?>createVote(@PathVariable Long pollId, @RequestBody Vote vote){
        vote = votingRepository.save(vote);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(vote.getId()).toUri());
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }
    @RequestMapping(value = "/polls/{pollId}/allvotes", method = RequestMethod.GET)
    public Iterable<Vote> getAllVotes(@PathVariable Long pollId){
        return votingRepository.findByPoll(pollId);
    }
}
