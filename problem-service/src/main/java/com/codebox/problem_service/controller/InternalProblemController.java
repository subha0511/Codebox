package com.codebox.problem_service.controller;

import com.codebox.problem_service.model.Problem;
import com.codebox.problem_service.service.ProblemService;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Hidden
@RestController
@RequestMapping("/api/internal-problem")
public class InternalProblemController {

  @Autowired
  private ProblemService problemService;

  @GetMapping(path = "/{id}")
  public Optional<Problem> getProblemById(@PathVariable("id") int id) {
    return problemService.getProblemById(id);
  }
}
