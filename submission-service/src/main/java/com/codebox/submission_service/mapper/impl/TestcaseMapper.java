package com.codebox.submission_service.mapper.impl;

import com.codebox.submission_service.mapper.Mapper;
import com.codebox.submission_service.pojo.SubmissionTestcase;
import com.codebox.submission_service.pojo.Testcase;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestcaseMapper implements Mapper<Testcase, SubmissionTestcase> {

  @Autowired
  private ModelMapper modelMapper;

  @Override
  public SubmissionTestcase mapTo(Testcase testcase) {
    return modelMapper.map(testcase, SubmissionTestcase.class);
  }

  @Override
  public Testcase mapFrom(SubmissionTestcase submissionTestcase) {
    return modelMapper.map(submissionTestcase, Testcase.class);
  }
}
