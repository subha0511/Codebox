package com.codebox.submission_service.mapper.impl;

import com.codebox.submission_service.dto.SubmissionDTO;
import com.codebox.submission_service.mapper.Mapper;
import com.codebox.submission_service.model.Submission;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubmissionMapper implements Mapper<Submission, SubmissionDTO> {

  @Autowired
  private ModelMapper modelMapper;

  @Override
  public SubmissionDTO mapTo(Submission submission) {
    return modelMapper.map(submission, SubmissionDTO.class);
  }

  @Override
  public Submission mapFrom(SubmissionDTO submissionDTO) {
    return modelMapper.map(submissionDTO, Submission.class);
  }
}
