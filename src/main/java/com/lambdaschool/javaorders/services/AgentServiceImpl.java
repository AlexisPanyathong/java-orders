package com.lambdaschool.javaorders.services;

import com.lambdaschool.javaorders.models.Customers;
import com.lambdaschool.javaorders.models.Agent;
import com.lambdaschool.javaorders.repos.AgentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "agentService")
public class AgentServiceImpl {

    @Autowired
    // This access the agents repository
    private AgentRepo agentrepo;

    // This generates override methods from agents
    // find all
    @Override
    public List<Agent> findAll()
    {
        List<Agent> rtnList = new ArrayList<>();
        agentrepo.findAll().iterator().forEachRemaining(rtnList::add);
        return rtnList;
    }

    // Find by id
    @Override
    public Agent findById(long agentcode)
    {
        return agentrepo.findById(agentcode).orElseThrow(() -> new EntityNotFoundException("Not Found " + agentcode));
    }

    @Transactional
    // Save
    @Override
    public Agent save(Agent agents)
    {
        Agent newAgents = new Agent();

        newAgents.setAgentname(agents.getAgentname());
        newAgents.setWorkingarea(agents.getWorkingarea());
        newAgents.setCommission(agents.getCommission());
        newAgents.setPhone(agents.getPhone());
        newAgents.setCountry(agents.getCountry());

        for (Customers c : agents.getCustomers())
        {
            newAgents.getCustomers().add(new Customers(c.getCustname(),
                    c.getCustcity(), c.getWorkingarea(), c.getCustcountry(),
                    c.getGrade(), c.getOpeningamt(), c.getReceiveamt(), c.getPaymentamt(),
                    c.getOutstandingamt(), c.getPhone(), newAgents));
        }
        return agentrepo.save(newAgents);
    }

    // Update by id
    @Override
    public Agent update(Agent agents, long agentcode)
    {
        return agentrepo.save(agents);
    }

    // Delete
    @Override
    public void delete(long agentcode)
    {
        agentrepo.deleteById(agentcode);
    }

}
