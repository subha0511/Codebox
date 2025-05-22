package com.codebox.problem_service.mapper.impl;

import com.codebox.problem_service.domain.dto.ProblemDTO;
import com.codebox.problem_service.mapper.Mapper;
import com.codebox.problem_service.domain.model.Problem;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProblemDetailMapper implements Mapper<Problem, ProblemDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProblemDTO mapTo(Problem problem) {
        return modelMapper.map(problem, ProblemDTO.class);
    }

    @Override
    public Problem mapFrom(ProblemDTO problemDTO) {
        return modelMapper.map(problemDTO, Problem.class);
    }
}
