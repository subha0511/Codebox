package com.codebox.problem_service.controller;

import com.codebox.problem_service.domain.dto.ProblemDTO;
import com.codebox.problem_service.domain.dto.ProblemSummaryDTO;
import com.codebox.problem_service.mapper.impl.ProblemDetailMapper;
import com.codebox.problem_service.mapper.impl.ProblemMapper;
import com.codebox.problem_service.domain.model.Problem;
import com.codebox.problem_service.service.ProblemService;
import com.codebox.problem_service.utils.MapperUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Problem Controller")
@RestController
@RequestMapping("/api/problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @Autowired
    private ProblemMapper problemMapper;

    @Autowired
    private ProblemDetailMapper problemDetailMapper;

    @Autowired
    private MapperUtils mapperUtils;

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProblemDTO> getProblemById(@PathVariable("id") int id) {
        Optional<Problem> problem = problemService.getProblemById(id);
        return problem.map(value -> ResponseEntity.ok(problemDetailMapper.mapTo(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(path = "")
    public Page<ProblemSummaryDTO> getPaginatedProblems(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size) {
        return mapperUtils.mapEntityPageIntoDtoPage(
                problemService.getPaginatedProblems(page, size), ProblemSummaryDTO.class);
    }

    @PostMapping(path = "/create")
    public ProblemDTO createProblem(@RequestBody ProblemDTO problemDTO) {
        Problem problem = problemDetailMapper.mapFrom(problemDTO);
        Problem savedProblem = problemService.saveProblem(problem);
        return problemDetailMapper.mapTo(savedProblem);
    }
}
