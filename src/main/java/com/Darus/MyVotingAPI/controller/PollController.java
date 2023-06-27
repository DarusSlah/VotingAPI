package com.Darus.MyVotingAPI.controller;

import com.Darus.MyVotingAPI.dto.ResourceNotFoundException;
import com.Darus.MyVotingAPI.model.Poll;
import com.Darus.MyVotingAPI.repositories.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
public class PollController {


    @Autowired
    private PollRepository pollRepository;


    @GetMapping("/polls")
    public Iterable<Poll> getAllPolls() {
        return pollRepository.findAll();
    }

    @RequestMapping(value = "/polls", method = RequestMethod.POST)
    public ResponseEntity<?> createPoll(@Valid @RequestBody Poll poll) {
        pollRepository.save(poll);

        // Set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPollUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(poll.getId()).toUri();
        responseHeaders.setLocation(newPollUri);

        return new ResponseEntity<>(null,responseHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/polls/{pollId}", method = RequestMethod.GET)
    public ResponseEntity<?> getPoll(@PathVariable Long pollId) {
        verifyPoll(pollId);
        Optional<Poll> poll = pollRepository.findById(pollId);
        return new ResponseEntity<>(poll, HttpStatus.OK);
    }

    @PutMapping(value = "/polls/{pollId}")
    public ResponseEntity<Poll> updatePoll(@PathVariable Long pollId, @RequestBody Poll poll) {
        verifyPoll(pollId);
        return ResponseEntity.ok(pollRepository.save(poll));
    }

    @DeleteMapping(value ="/polls/{pollId}")
    public ResponseEntity<?> deletePoll(@PathVariable Long pollId) {
        verifyPoll(pollId);
        pollRepository.deleteById(pollId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    protected void verifyPoll(Long pollId) throws ResourceNotFoundException {
        Optional<Poll> poll = pollRepository.findById(pollId);
        if (poll.isEmpty()) {
            throw new ResourceNotFoundException("Poll with id " + pollId + " not found");
        }

    }
}
