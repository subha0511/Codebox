package com.codebox.submission_service.mapper.impl;

import com.codebox.submission_service.domain.dto.SubmissionSummaryDTO;
import com.codebox.submission_service.mapper.Mapper;
import com.codebox.submission_service.domain.model.Submission;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubmissionMapper implements Mapper<Submission, SubmissionSummaryDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public SubmissionSummaryDTO mapTo(Submission submission) {
        return modelMapper.map(submission, SubmissionSummaryDTO.class);
    }

    @Override
    public Submission mapFrom(SubmissionSummaryDTO submissionSummaryDTO) {
        return modelMapper.map(submissionSummaryDTO, Submission.class);
    }
}
