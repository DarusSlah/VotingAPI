//package com.Darus.MyVotingAPI.service;
//
//import com.Darus.MyVotingAPI.model.Poll;
//import com.Darus.MyVotingAPI.dto.ResourceNotFoundException;
//import com.Darus.MyVotingAPI.repositories.PollRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class PollService {
//    @Autowired
//    private PollRepository pollRepository;
//
//
//
//
//    public Iterable<Poll> getAllPolls(){
//        return pollRepository.findAll();
//    }
//    public void createPoll(Poll poll){
//    pollRepository.save(poll);
//    }
//
//    public Optional<Poll> getPoll(Long pollId){
//        verifyPoll(pollId);
//        return pollRepository.findById(pollId);
//    }
//     public void deletePoll(Long pollId){
//        verifyPoll(pollId);
//        pollRepository.deleteById(pollId);
//     }
//     public Poll editPoll(Poll poll){
//         return pollRepository.save(poll);
//     }
//
//    protected void verifyPoll(Long pollId) throws ResourceNotFoundException {
//        Optional<Poll> poll = pollRepository.findById(pollId);
//        if (poll.isEmpty()) {
//            throw new ResourceNotFoundException("Poll with id " + pollId + " not found");
//        }
//    }
//}
