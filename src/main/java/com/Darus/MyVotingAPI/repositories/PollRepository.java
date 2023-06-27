package com.Darus.MyVotingAPI.repositories;

import com.Darus.MyVotingAPI.model.Poll;
import org.springframework.data.repository.CrudRepository;

public interface PollRepository extends CrudRepository <Poll,Long>{
}
