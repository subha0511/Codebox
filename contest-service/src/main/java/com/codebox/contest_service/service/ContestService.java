package com.codebox.contest_service.service;

import com.codebox.contest_service.model.Contest;
import com.codebox.contest_service.repository.ContestRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContestService {

    @Autowired
    private ContestRepository contestRepository;

    public Page<Contest> getPaginatedContests(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return contestRepository.findAll(pageable);
    }

    public Page<Contest> getPastContests(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return contestRepository.findPastContests(pageable);
    }

    public Page<Contest> getUpcomingContests(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return contestRepository.findUpcomingContests(pageable);
    }

    public Page<Contest> getOngoingContests(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return contestRepository.findOngoingContests(pageable);
    }

    public Contest saveContest(Contest contest) {
        return contestRepository.save(contest);
    }

    public Optional<Contest> getContestById(long id) {
        return contestRepository.findById(id);
    }
}
