package com.codebox.contest_service.repository;

import com.codebox.contest_service.model.Contest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContestRepository extends CrudRepository<Contest, Long>,
    PagingAndSortingRepository<Contest, Long> {

  @Query("SELECT c FROM Contest c WHERE c.endTime < CURRENT_TIMESTAMP")
  Page<Contest> findPastContests(Pageable pageable);

  @Query("SELECT c FROM Contest c WHERE c.startTime > CURRENT_TIMESTAMP")
  Page<Contest> findUpcomingContests(Pageable pageable);

  @Query("SELECT c FROM Contest c WHERE c.startTime < CURRENT_TIMESTAMP and c.endTime > CURRENT_TIMESTAMP")
  Page<Contest> findOngoingContests(Pageable pageable);

}
