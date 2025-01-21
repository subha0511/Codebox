package com.codebox.problem_service.mapper.impl;

import com.codebox.problem_service.dto.ProblemDetailDTO;
import com.codebox.problem_service.mapper.Mapper;
import com.codebox.problem_service.model.Problem;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProblemDetailMapper implements Mapper<Problem, ProblemDetailDTO> {

  @Autowired
  private ModelMapper modelMapper;

  @Override
  public ProblemDetailDTO mapTo(Problem problem) {
    return modelMapper.map(problem, ProblemDetailDTO.class);
  }

  @Override
  public Problem mapFrom(ProblemDetailDTO problemDetailDTO) {
    return modelMapper.map(problemDetailDTO, Problem.class);
  }

}
