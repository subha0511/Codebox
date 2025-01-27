package com.codebox.contest_service.mapper.impl;

import com.codebox.contest_service.dto.ContestDTO;
import com.codebox.contest_service.mapper.Mapper;
import com.codebox.contest_service.model.Contest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContestMapper implements Mapper<Contest, ContestDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ContestDTO mapTo(Contest contest) {
        return modelMapper.map(contest, ContestDTO.class);
    }

    @Override
    public Contest mapFrom(ContestDTO contestDTO) {
        return modelMapper.map(contestDTO, Contest.class);
    }
}
