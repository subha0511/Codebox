package com.codebox.problem_service.mapper.impl;

import com.codebox.problem_service.dto.ProblemMetadataDTO;
import com.codebox.problem_service.mapper.Mapper;
import com.codebox.problem_service.model.Problem;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProblemMapper implements Mapper<Problem, ProblemMetadataDTO> {

  @Autowired
  private ModelMapper modelMapper;

  @Override
  public ProblemMetadataDTO mapTo(Problem problem) {
    return modelMapper.map(problem, ProblemMetadataDTO.class);
  }

  @Override
  public Problem mapFrom(ProblemMetadataDTO ProblemMetadataDTO) {
    return modelMapper.map(ProblemMetadataDTO, Problem.class);
  }
}
