package com.codebox.contest_service.service;

import com.codebox.contest_service.model.Participant;
import com.codebox.contest_service.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParticipantService {

  @Autowired
  private ParticipantRepository participantRepository;

  public Page<Participant> getPaginatedParticipants(Long contestId, int page, int size) {
    return participantRepository.findAllByContestId(contestId, PageRequest.of(page, size));
  }

  public Participant saveParticipant(Participant participant) {
    Optional<Participant> existingParticipant = participantRepository.findByContestIdAndUserId(
        participant.getContest()
            .getId(), participant.getUserId());
    return existingParticipant.orElseGet(() -> participantRepository.save(participant));
  }

  public Long getParticipantCount(Long contestId) {
    return participantRepository.countByContestId(contestId);
  }

  public boolean checkParticipant(Long contestId, Long userId) {
    return participantRepository.existsByContestIdAndUserId(contestId, userId);
  }

}
