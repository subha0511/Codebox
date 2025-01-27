package com.codebox.submission_service.config;

import com.codebox.shared_dtos.domain.problem.ProblemTestcaseDTO;
import com.codebox.submission_service.pojo.SubmissionTestcase;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

        modelMapper.typeMap(ProblemTestcaseDTO.class, SubmissionTestcase.class).addMappings(mapper -> {
            mapper.map(ProblemTestcaseDTO::getInput, SubmissionTestcase::setInput);
            mapper.map(ProblemTestcaseDTO::getOutput, SubmissionTestcase::setExpectedOutput);
            mapper.skip(SubmissionTestcase::setOutput);
        });

        modelMapper.typeMap(SubmissionTestcase.class, ProblemTestcaseDTO.class).addMappings(mapper -> {
            mapper.map(SubmissionTestcase::getInput, ProblemTestcaseDTO::setInput);
            mapper.map(SubmissionTestcase::getExpectedOutput, ProblemTestcaseDTO::setOutput);
        });

        return modelMapper;
    }
}
