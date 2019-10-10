package com.lambdaschool.javaorders.repos;

import org.springframework.data.repository.CrudRepository;
import com.lambdaschool.javaorders.models.Agent;

public interface AgentRepo extends CrudRepository<Agent, Long> {
}
