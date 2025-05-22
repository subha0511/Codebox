package com.codebox.problem_service.mapper.impl;

import com.codebox.problem_service.domain.dto.ProblemSummaryDTO;
import com.codebox.problem_service.mapper.Mapper;
import com.codebox.problem_service.domain.model.Problem;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProblemMapper implements Mapper<Problem, ProblemSummaryDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProblemSummaryDTO mapTo(Problem problem) {
        return modelMapper.map(problem, ProblemSummaryDTO.class);
    }

    @Override
    public Problem mapFrom(ProblemSummaryDTO ProblemSummaryDTO) {
        return modelMapper.map(ProblemSummaryDTO, Problem.class);
    }
}
