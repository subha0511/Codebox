package com.codebox.submission_service.mapper.impl;

import com.codebox.shared_dtos.domain.problem.ProblemTestcaseDTO;
import com.codebox.submission_service.mapper.Mapper;
import com.codebox.submission_service.pojo.SubmissionTestcase;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProblemTestcaseMapper implements Mapper<ProblemTestcaseDTO, SubmissionTestcase> {

  @Autowired
  private ModelMapper modelMapper;

  @Override
  public SubmissionTestcase mapTo(ProblemTestcaseDTO problemTestcaseDTO) {
    return modelMapper.map(problemTestcaseDTO, SubmissionTestcase.class);
  }

  @Override
  public ProblemTestcaseDTO mapFrom(SubmissionTestcase submissionTestcase) {
    return modelMapper.map(submissionTestcase, ProblemTestcaseDTO.class);
  }
}
