package tr.edu.boun.cmpe.mas.akin.cpgen.data;

import tr.edu.boun.cmpe.mas.akin.cpgen.util.ArgumentValidator;
import tr.edu.boun.cmpe.mas.akin.cpgen.protocol.Proposition;
import tr.edu.boun.cmpe.mas.akin.cpgen.protocol.Service;
import tr.edu.boun.cmpe.mas.akin.cpgen.protocol.Incentive;
import tr.edu.boun.cmpe.mas.akin.cpgen.protocol.Capability;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Stores input data that can be provided to a protocol generator.
 * 
 * @author Akin Gunay
 */
public class InputData {

    private final String generatorAgent;
    private final Set<Proposition> goals;
    private final Map<Proposition, Set<Capability>> capabilities;
    private final Map<Proposition, Set<Service>> services;
    private final Map<Proposition, Set<Incentive>> incentives;
    
    public static InputData newInputData(String agent) {
        ArgumentValidator.validateNotNullAndNotEmptyString(agent, "agent");
        return new InputData(agent);
    }
    
    private InputData(String generatorAgent) {
        this.generatorAgent = generatorAgent;
        this.goals = new HashSet<>();
        this.capabilities = new HashMap<>();
        this.services = new HashMap<>();
        this.incentives = new HashMap<>();
    }

    public void addGoal(Proposition goal) {
        ArgumentValidator.validateNotNull(goal, "goal");
        goals.add(goal);
    }
    
    public void addCapability(Capability capability) {
        ArgumentValidator.validateNotNull(capability, "capability");
        if (!capabilities.containsKey(capability.getPostcondition())) {
            capabilities.put(capability.getPostcondition(), new HashSet<Capability>());
        }
        capabilities.get(capability.getPostcondition()).add(capability);
    }

    public void addService(Service service) {
        ArgumentValidator.validateNotNull(service, "service");
        if (!services.containsKey(service.getPostcondition())) {
            services.put(service.getPostcondition(), new HashSet<Service>());
        }
        services.get(service.getPostcondition()).add(service);
    }
    
    public void addIncentive(Incentive incentive) {
        ArgumentValidator.validateNotNull(incentive, "incentive");
        if (!incentives.containsKey(incentive.getPostcondition())) {
            incentives.put(incentive.getPostcondition(), new HashSet<Incentive>());
        }
        incentives.get(incentive.getPostcondition()).add(incentive);
    }
    
    public String getGeneratorAgent() {
        return generatorAgent;
    }

    public Collection<Proposition> getGoals() {
        return Collections.unmodifiableCollection(goals);
    }

    public Map<Proposition, Set<Capability>> getCapabilities() {
        return Collections.unmodifiableMap(capabilities);
    }

    public Set<Capability> getCapabilitiesForPostcondition(Proposition postcondition) {
        if (capabilities.containsKey(postcondition)) {
            return Collections.unmodifiableSet(capabilities.get(postcondition));
        } else {
            return new HashSet<>();
        }
    }
    
    public Map<Proposition, Set<Service>> getServices() {
        return Collections.unmodifiableMap(services);
    }

    public Set<Service> getServicesForPostcondition(Proposition postcondition) {
        if (services.containsKey(postcondition)) {
            return Collections.unmodifiableSet(services.get(postcondition));
        } else {
            return new HashSet<>();
        }
    }
    
    public Map<Proposition, Set<Incentive>> getIncentives() {
        return Collections.unmodifiableMap(incentives);
    }
    
    public Set<Incentive> getIncentivesForPostcondition(Proposition postcondition) {
        if (incentives.containsKey(postcondition)) {
            return Collections.unmodifiableSet(incentives.get(postcondition));
        } else {
            return new HashSet<>();
        }
    }
    
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Generator Agent: ").append(generatorAgent).append("\n");
        appendGoalsToStringBuilder(str);
        appendCapabilitiesToStringBuilder(str);
        appendServicesToStringBuilder(str);
        appendIncentivesToStringBuilder(str);
        return str.toString();
    }

    private void appendGoalsToStringBuilder(StringBuilder str) {
        str.append("Goals:\n");
        for (Proposition goal : goals) {
            str.append("\tGoal(").append(goal).append(")\n");
        }
    }

    private void appendCapabilitiesToStringBuilder(StringBuilder str) {
        str.append("Capabilities:\n");
        for (Proposition postcondition : capabilities.keySet()) {
            for (Capability capability : capabilities.get(postcondition)) {
                str.append("\t").append(capability).append("\n");
            }
        }
    }
    
    private void appendServicesToStringBuilder(StringBuilder str) {
        str.append("Services:\n");
        for (Proposition postcondition : services.keySet()) {
            for (Service service : services.get(postcondition)) {
                str.append("\t").append(service).append("\n");
            }
        }
    }
    
    private void appendIncentivesToStringBuilder(StringBuilder str) {
        str.append("Incentives:\n");
        for (Proposition postcondition : incentives.keySet()) {
            for (Incentive incentive : incentives.get(postcondition)) {
                str.append("\t").append(incentive).append("\n");
            }
        }
    }
}
