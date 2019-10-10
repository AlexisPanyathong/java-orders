package com.lambdaschool.javaorders.services;

import  com.lambdaschool.javaorders.models.Agent;
import java.util.List;

public interface AgentService
{
    // find all
    List<Agent>findAll();

    // find by id
    Agent findById(long id);

    // save
    Agent save(Agent agents);

    // update by id
    Agent update(Agent agents, long id);

    // delete
    void delete(long id);
}

