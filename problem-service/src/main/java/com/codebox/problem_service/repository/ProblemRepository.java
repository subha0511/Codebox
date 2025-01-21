package com.codebox.problem_service.repository;

import com.codebox.problem_service.model.Problem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemRepository extends CrudRepository<Problem, Long>,
    PagingAndSortingRepository<Problem, Long> {
}
