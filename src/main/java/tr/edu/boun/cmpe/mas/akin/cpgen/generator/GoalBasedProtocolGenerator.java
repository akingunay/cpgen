package tr.edu.boun.cmpe.mas.akin.cpgen.generator;

import tr.edu.boun.cmpe.mas.akin.cpgen.protocol.Protocol;
import tr.edu.boun.cmpe.mas.akin.cpgen.protocol.Proposition;
import tr.edu.boun.cmpe.mas.akin.cpgen.protocol.Service;
import tr.edu.boun.cmpe.mas.akin.cpgen.protocol.Incentive;
import tr.edu.boun.cmpe.mas.akin.cpgen.protocol.Commitment;
import tr.edu.boun.cmpe.mas.akin.cpgen.protocol.Capability;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import tr.edu.boun.cmpe.mas.akin.cpgen.util.ArgumentValidator;
import tr.edu.boun.cmpe.mas.akin.cpgen.data.InputData;
import tr.edu.boun.cmpe.mas.akin.cpgen.protocol.Conjunction;

/**
 * Implementation of the GoalBased algorithm in "Akın Günay, Michael Winikoff, 
 * and Pınar Yolum. Dynamically Generated Commitment Protocols in Open Systems. 
 * Autonomous Agents and Multi-agent Systems, 29(2), 192-229, 2015".
 * 
 * @author Akin Gunay
 */
public class GoalBasedProtocolGenerator implements ProtocolGenerator {

    private final InputData inputData;
    private Map<Proposition, Set<Protocol>> memory;
    
    public GoalBasedProtocolGenerator(InputData inputData) {
        ArgumentValidator.validateNotNull(inputData, "inputData");
        this.inputData = inputData;
    }
    
    @Override
    public Set<Protocol> generateProtocols() {
        memory = new HashMap<>();
        return findSupport(new ArrayDeque<>(inputData.getGoals()));
    }
    
    private Set<Protocol> findSupport(Queue<Proposition> goals) {
        if (goals.isEmpty()) {
            return new HashSet<>(Arrays.asList(new Protocol()));
        } else if (goals.size() == 1) {
            Proposition currentGoal = goals.poll();
            if (memory.containsKey(currentGoal)) {
                return memory.get(currentGoal);
            } else {
                return findSupportForSingleGoal(currentGoal);
            }
        } else {
            Queue<Proposition> head = new ArrayDeque<>();
            head.offer(goals.poll());
            return cartesian(findSupport(head), findSupport(goals));
        }
    }
    
    private Set<Protocol> findSupportForSingleGoal(Proposition goal) {
        Set<Protocol> protocols = new HashSet<>();
        for (Capability capability : inputData.getCapabilitiesForPostcondition(goal)) {
            protocols.addAll(findSupport(new ArrayDeque<>(capability.getPrecondition())));
        }
        for (Service service : inputData.getServicesForPostcondition(goal)) {
            for (Incentive incentive : inputData.getIncentivesForPostcondition(goal)) {
                if (incentive.getAgent().equals(service.getAgent())) {
                    Queue<Proposition> newGoals = new ArrayDeque<>();
                    newGoals.addAll(service.getPrecondition());
                    newGoals.addAll(incentive.getPrecondition());
                    Set<Protocol> newProtocols = new HashSet<>(findSupport(newGoals));
                    Commitment newCommitment = new Commitment(service.getAgent(), inputData.getGeneratorAgent(), 
                            Conjunction.compose(service.getPrecondition(), incentive.getPrecondition()), goal);
                    for (Protocol protocol : newProtocols) {
                        protocol.addCommitment(newCommitment);
                    }
                    protocols.addAll(newProtocols);
                }
            }
        }
        memory.put(goal, protocols);
        return protocols;
    }
    
    private Set<Protocol> cartesian(Set<Protocol> firstSet, Set<Protocol> secondSet) {
        ArgumentValidator.validateNotNull(firstSet, "firstSet");
        ArgumentValidator.validateNotNull(secondSet, "secondSet");
        Set<Protocol> product = new HashSet<>();
        if (!firstSet.isEmpty() && !secondSet.isEmpty()) {
            for (Protocol firstProtocol : firstSet) {
                for (Protocol secondProtocol : secondSet) {
                    product.add(Protocol.union(firstProtocol, secondProtocol));
                }
            }
        }
        return product;
    }
}
