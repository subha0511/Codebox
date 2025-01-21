package com.codebox.submission_service.client;

import com.codebox.submission_service.pojo.Problem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@FeignClient(name = "problem-service", url = "${problem-service.url}")
public interface ProblemClient {

  @RequestMapping(method = RequestMethod.GET, value = "/api/internal-problem/{id}")
  Optional<Problem> getProblemById(@PathVariable long id);

}
