package tr.edu.boun.cmpe.mas.akin.cpgen.protocol;

import java.util.Set;
import tr.edu.boun.cmpe.mas.akin.cpgen.util.ArgumentValidator;

/**
 * @author Akin Gunay
 */
public class Service extends Action {
    
    private final String agent;
    
    public static Service newService(String agent, Set<Proposition> precondition, Proposition postcondition) {
        ArgumentValidator.validateNotNullAndNotEmpty(agent, "agent");
        return new Service(agent, precondition, postcondition);
    }
    
    private Service(String agent, Set<Proposition> precondition, Proposition postcondition) {
        super(precondition, postcondition);
        this.agent = agent;
    }

    public String getAgent() {
        return agent;
    }
    
    @Override
    public String toString() {
        return "Service(" + agent + ", " + super.toString() + ")";
    }
}
