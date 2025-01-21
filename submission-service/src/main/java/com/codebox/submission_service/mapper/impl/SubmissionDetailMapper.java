package com.codebox.submission_service.mapper.impl;

import com.codebox.submission_service.dto.SubmissionDetailDTO;
import com.codebox.submission_service.mapper.Mapper;
import com.codebox.submission_service.model.Submission;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubmissionDetailMapper implements Mapper<Submission, SubmissionDetailDTO> {

  @Autowired
  private ModelMapper modelMapper;

  @Override
  public SubmissionDetailDTO mapTo(Submission submission) {
    return modelMapper.map(submission, SubmissionDetailDTO.class);
  }

  @Override
  public Submission mapFrom(SubmissionDetailDTO submissionDetailsDTO) {
    return modelMapper.map(submissionDetailsDTO, Submission.class);
  }

}
