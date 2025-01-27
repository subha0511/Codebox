package com.codebox.contest_service.repository;

import com.codebox.contest_service.model.Participant;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository
        extends CrudRepository<Participant, Long>, PagingAndSortingRepository<Participant, Long> {

    Page<Participant> findAllByContestId(Long contestId, Pageable pageable);

    Long countByContestId(Long contestId);

    Optional<Participant> findByContestIdAndUserId(Long contestId, Long userId);

    Boolean existsByContestIdAndUserId(Long contestId, Long userId);
}
