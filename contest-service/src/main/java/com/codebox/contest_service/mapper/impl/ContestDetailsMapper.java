package com.codebox.contest_service.mapper.impl;

import com.codebox.contest_service.dto.ContestDetailsDTO;
import com.codebox.contest_service.mapper.Mapper;
import com.codebox.contest_service.model.Contest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContestDetailsMapper implements Mapper<Contest, ContestDetailsDTO> {

  @Autowired
  private ModelMapper modelMapper;

  @Override
  public ContestDetailsDTO mapTo(Contest contest) {
    return modelMapper.map(contest, ContestDetailsDTO.class);
  }

  @Override
  public Contest mapFrom(ContestDetailsDTO contestDetailsDTO) {
    return modelMapper.map(contestDetailsDTO, Contest.class);
  }
}
