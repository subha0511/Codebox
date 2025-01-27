package com.codebox.problem_service.service;

import com.codebox.problem_service.model.Problem;
import com.codebox.problem_service.repository.ProblemRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProblemService {

    @Autowired
    private ProblemRepository problemRepository;

    public Optional<Problem> getProblemById(long id) {
        return problemRepository.findById(id);
    }

    public Page<Problem> getPaginatedProblems(int page, int size) {
        return problemRepository.findAll(PageRequest.of(page, size));
    }

    public Problem saveProblem(Problem problem) {
        System.out.println(problem.toString());
        return problemRepository.save(problem);
    }
}
