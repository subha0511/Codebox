package com.codebox.submission_service.repository;

import com.codebox.submission_service.domain.model.Submission;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepository extends MongoRepository<Submission, String> {

//  @Query("{ '_id' : ?0 }")
//  @Update("{ $set: { 'status' : ?1 } }")
}
