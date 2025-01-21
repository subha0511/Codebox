package com.codebox.contest_service.controller;

import com.codebox.contest_service.dto.ContestDTO;
import com.codebox.contest_service.dto.ContestDetailsDTO;
import com.codebox.contest_service.mapper.impl.ContestDetailsMapper;
import com.codebox.contest_service.mapper.impl.ContestMapper;
import com.codebox.contest_service.model.Contest;
import com.codebox.contest_service.model.Participant;
import com.codebox.contest_service.service.ContestService;
import com.codebox.contest_service.service.ParticipantService;
import com.codebox.contest_service.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/contest")
public class ContestController {

  @Autowired
  private ContestService contestService;

  @Autowired
  private ContestMapper contestMapper;

  @Autowired
  private ContestDetailsMapper contestDetailsMapper;

  @Autowired
  private ParticipantService participantService;

  @Autowired
  private MapperUtils mapperUtils;

  @PostMapping(path = "/create")
  public ResponseEntity<?> createContest(@RequestBody ContestDetailsDTO contestDetailsDTO) {
    if (contestDetailsDTO.getStartTime().isBefore(LocalDateTime.now())) {
      return new ResponseEntity<>(
          Map.of("message", "Start Time cannot be in past"), HttpStatus.BAD_REQUEST);
    }
    if (contestDetailsDTO.getStartTime().isAfter(contestDetailsDTO.getEndTime())) {
      return new ResponseEntity<>(
          Map.of("message", "Start Time must be before End Time"), HttpStatus.BAD_REQUEST);
    }
    Contest contest = contestDetailsMapper.mapFrom(contestDetailsDTO);
    return ResponseEntity.ok(contestService.saveContest(contest));
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<?> getContestById(@PathVariable(value = "id") long id) {
    Optional<Contest> contest = contestService.getContestById(id);
    if (contest.isEmpty()) {
      return new ResponseEntity<>(Map.of("message", "Contest not found"), HttpStatus.NOT_FOUND);
    }
    return ResponseEntity.ok(contestDetailsMapper.mapTo(contest.get()));
  }

  @GetMapping(path = "/")
  public PagedModel<ContestDTO> getPaginatedContests(
      @RequestParam(value = "page", defaultValue = "0") int page,
      @RequestParam(value = "size", defaultValue = "20") int size) {
    Page<Contest> contests = contestService.getPaginatedContests(page, size);
    return new PagedModel<>(mapperUtils.mapEntityPageIntoDtoPage(contests, ContestDTO.class));
  }

  @GetMapping(path = "/past")
  public PagedModel<ContestDTO> getPastContests(
      @RequestParam(value = "page", defaultValue = "0") int page,
      @RequestParam(value = "size", defaultValue = "20") int size) {
    Page<Contest> contests = contestService.getPastContests(page, size);
    return new PagedModel<>(mapperUtils.mapEntityPageIntoDtoPage(contests, ContestDTO.class));
  }

  @GetMapping(path = "/upcoming")
  public PagedModel<ContestDTO> getUpcomingContests(
      @RequestParam(value = "page", defaultValue = "0") int page,
      @RequestParam(value = "size", defaultValue = "20") int size) {
    Page<Contest> contests = contestService.getUpcomingContests(page, size);
    return new PagedModel<>(mapperUtils.mapEntityPageIntoDtoPage(contests, ContestDTO.class));
  }

  @GetMapping(path = "/ongoing")
  public PagedModel<ContestDTO> getOngoingContests(
      @RequestParam(value = "page", defaultValue = "0") int page,
      @RequestParam(value = "size", defaultValue = "20") int size) {
    Page<Contest> contests = contestService.getOngoingContests(page, size);
    return new PagedModel<>(mapperUtils.mapEntityPageIntoDtoPage(contests, ContestDTO.class));
  }

  @GetMapping(path = "/{id}/participants")
  public ResponseEntity<?> getPaginatedParticipants(
      @PathVariable(value = "id") Long contestId,
      @RequestParam(value = "page", defaultValue = "0") int page,
      @RequestParam(value = "size", defaultValue = "100") int size) {
    Optional<Contest> contest = contestService.getContestById(contestId);
    if (contest.isEmpty()) {
      return new ResponseEntity<>(Map.of("message", "Contest not found"), HttpStatus.NOT_FOUND);
    }
    return ResponseEntity.ok(
        new PagedModel<>(participantService.getPaginatedParticipants(contestId, page, size)));
  }

  @PostMapping(path = "/{id}/participate")
  public ResponseEntity<?> participateInContest(@PathVariable(value = "id") Long contestId) {
    Optional<Contest> contest = contestService.getContestById(contestId);
    if (contest.isEmpty()) {
      return new ResponseEntity<>(Map.of("message", "Contest not found"), HttpStatus.NOT_FOUND);
    }
    Participant participant = new Participant();
    participant.setUserId(1L);
    participant.setContest(contest.get());
    return ResponseEntity.ok(participantService.saveParticipant(participant));
  }

}
