package com.codebox.submission_service.config;

import com.codebox.submission_service.pojo.SubmissionTestcase;
import com.codebox.submission_service.pojo.Testcase;
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

    modelMapper.typeMap(Testcase.class, SubmissionTestcase.class).addMappings(mapper -> {
      mapper.map(Testcase::getInput, SubmissionTestcase::setInput);
      mapper.map(Testcase::getOutput, SubmissionTestcase::setExpectedOutput);
      mapper.skip(SubmissionTestcase::setOutput);
    });

    modelMapper.typeMap(SubmissionTestcase.class, Testcase.class).addMappings(mapper -> {
      mapper.map(SubmissionTestcase::getInput, Testcase::setInput);
      mapper.map(SubmissionTestcase::getExpectedOutput, Testcase::setOutput);
    });

    return modelMapper;
  }

}
