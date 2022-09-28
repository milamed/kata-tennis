package com.tennis.repository;

import com.tennis.entity.Referee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefereeRepository extends MongoRepository<Referee, String> {
}
