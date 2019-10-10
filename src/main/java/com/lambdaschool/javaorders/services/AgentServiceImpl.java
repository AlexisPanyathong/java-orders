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
public class AgentServiceImpl implements AgentService {

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
    public Agent save(Agent agents) {
        Agent newAgent = new Agent();

        newAgent.setAgentname(agents.getAgentname());
        newAgent.setWorkingarea(agents.getWorkingarea());
        newAgent.setCommission(agents.getCommission());
        newAgent.setPhone(agents.getPhone());
        newAgent.setCountry(agents.getCountry());

        for (Customers c : agents.getCustomer())
        {
            newAgent.getCustomer().add(new Customers(c.getCustname(),
                    c.getCustcity(), c.getWorkingarea(), c.getCustcountry(),
                    c.getGrade(), c.getOpeningamt(), c.getReceiveamt(), c.getPaymentamt(),
                    c.getOutstandingamt(), c.getPhone(), newAgent));
        }
        return agentrepo.save(newAgent);
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
