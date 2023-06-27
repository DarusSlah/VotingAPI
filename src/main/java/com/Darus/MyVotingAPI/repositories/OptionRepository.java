package com.Darus.MyVotingAPI.repositories;

import com.Darus.MyVotingAPI.model.Option;
import org.springframework.data.repository.CrudRepository;

public interface OptionRepository extends CrudRepository<Option,Long> {
}
