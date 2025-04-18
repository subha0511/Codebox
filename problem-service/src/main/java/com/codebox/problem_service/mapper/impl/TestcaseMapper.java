package com.codebox.problem_service.mapper.impl;

import com.codebox.problem_service.dto.TestcaseDTO;
import com.codebox.problem_service.mapper.Mapper;
import com.codebox.problem_service.pojo.Testcase;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestcaseMapper implements Mapper<Testcase, TestcaseDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TestcaseDTO mapTo(Testcase problemTestcase) {
        return modelMapper.map(problemTestcase, TestcaseDTO.class);
    }

    @Override
    public Testcase mapFrom(TestcaseDTO ProblemTestcaseDTO) {
        return modelMapper.map(ProblemTestcaseDTO, Testcase.class);
    }
}
