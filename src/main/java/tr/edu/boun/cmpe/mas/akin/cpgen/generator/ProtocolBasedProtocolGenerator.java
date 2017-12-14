package tr.edu.boun.cmpe.mas.akin.cpgen.generator;

import tr.edu.boun.cmpe.mas.akin.cpgen.protocol.Protocol;
import tr.edu.boun.cmpe.mas.akin.cpgen.protocol.Proposition;
import tr.edu.boun.cmpe.mas.akin.cpgen.protocol.Service;
import tr.edu.boun.cmpe.mas.akin.cpgen.protocol.Incentive;
import tr.edu.boun.cmpe.mas.akin.cpgen.protocol.Commitment;
import tr.edu.boun.cmpe.mas.akin.cpgen.protocol.Capability;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import tr.edu.boun.cmpe.mas.akin.cpgen.util.ArgumentValidator;
import tr.edu.boun.cmpe.mas.akin.cpgen.data.InputData;
import tr.edu.boun.cmpe.mas.akin.cpgen.protocol.Conjunction;

/**
 * Implementation of the ProtocolBased algorithm in "Akın Günay, Michael Winikoff, 
 * and Pınar Yolum. Dynamically Generated Commitment Protocols in Open Systems. 
 * Autonomous Agents and Multi-agent Systems, 29(2), 192-229, 2015".
 * 
 * @author Akin Gunay
 */
public class ProtocolBasedProtocolGenerator implements ProtocolGenerator {
    
    private final InputData inputData;
    
    public ProtocolBasedProtocolGenerator(InputData inputData) {
        ArgumentValidator.validateNotNull(inputData, "dataSet");
        this.inputData = inputData;
    }
    
    @Override
    public Set<Protocol> generateProtocols() {
        return findSupport(new ArrayDeque<>(inputData.getGoals()), new HashSet<Proposition>(), new Protocol());
    }
    
    private Set<Protocol> findSupport(Queue<Proposition> pendingGoals, Set<Proposition> supportedGoals, Protocol generatedProtocol) {
        if (pendingGoals.isEmpty()) {
            return insertProtocolIntoSet(generatedProtocol);
        } else {
            Set<Protocol> protocols = new HashSet<>();
            Proposition currentGoal = pendingGoals.poll();
            protocols.addAll(findSupportFromCapabilities(pendingGoals, supportedGoals, generatedProtocol, currentGoal));
            protocols.addAll(findSupportFromServices(pendingGoals, supportedGoals, generatedProtocol, currentGoal));
            return protocols;
        }
    }
 
    private Set<Protocol> insertProtocolIntoSet(Protocol protocol) {
        Set<Protocol> protocols = new HashSet<>();
        protocols.add(protocol);
        return protocols;
    }
    
    private Set<Protocol> findSupportFromCapabilities(Queue<Proposition> pendingGoals, Set<Proposition> supportedGoals, Protocol generatedProtocol, Proposition currentGoal) {
        HashSet<Protocol> protocols = new HashSet<>();
        if (inputData.getCapabilities().containsKey(currentGoal)) {
            Set<Proposition> updatedSupportedGoals = new HashSet<>(supportedGoals);
            updatedSupportedGoals.add(currentGoal);
            for (Capability capability : inputData.getCapabilitiesForPostcondition(currentGoal)) {
                Queue<Proposition> updatedPendingGoals = new ArrayDeque<>(pendingGoals);
                addUnsupportedGoals(updatedPendingGoals, supportedGoals, capability.getPrecondition());
                protocols.addAll(findSupport(updatedPendingGoals, updatedSupportedGoals, generatedProtocol));
            }
        }
        return protocols;
    }
    
    private Set<Protocol> findSupportFromServices(Queue<Proposition> pendingGoals, Set<Proposition> supportedGoals, Protocol generatedProtocol, Proposition currentGoal) {
        HashSet<Protocol> protocols = new HashSet<>();
        if (inputData.getServices().containsKey(currentGoal)) {
            Set<Proposition> updatedSupportedGoals = new HashSet<>(supportedGoals);
            updatedSupportedGoals.add(currentGoal);
            for(Service service : inputData.getServicesForPostcondition(currentGoal)) {
                for (Incentive incentive : inputData.getIncentivesForPostcondition(currentGoal)) {
                    if (incentive.getAgent().equals(service.getAgent())) {
                        Queue<Proposition> updatedPendingGoals = new ArrayDeque<>(pendingGoals);
                        addUnsupportedGoals(updatedPendingGoals, supportedGoals, service.getPrecondition());
                        addUnsupportedGoals(updatedPendingGoals, supportedGoals, incentive.getPrecondition());
                        Protocol updatedGeneratedProtocol = new Protocol(generatedProtocol);
                        updatedGeneratedProtocol.addCommitment(new Commitment(service.getAgent(), inputData.getGeneratorAgent(), 
                                Conjunction.compose(service.getPrecondition(), incentive.getPrecondition()), currentGoal));
                        protocols.addAll(findSupport(updatedPendingGoals, updatedSupportedGoals, updatedGeneratedProtocol));
                    }
                }
            }
        }
        return protocols;
    }
    
    private void addUnsupportedGoals(Queue<Proposition> pendingGoals, Set<Proposition> supportedGoals, Conjunction newGoals) {
        for (Proposition newGoal : newGoals) {
            if (!supportedGoals.contains(newGoal) && !pendingGoals.contains(newGoal)) {
                pendingGoals.offer(newGoal);
            }
        }
    }
    
}
