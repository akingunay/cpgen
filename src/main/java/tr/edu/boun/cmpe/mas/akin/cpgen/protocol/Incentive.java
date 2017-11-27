package tr.edu.boun.cmpe.mas.akin.cpgen.protocol;

import java.util.Set;
import tr.edu.boun.cmpe.mas.akin.cpgen.util.ArgumentValidator;

/**
 * @author Akin Gunay
 */
public class Incentive extends Action {

    private final String agent;
    
    public static Incentive newIncentive(String agent, Set<Proposition> precondition, Proposition postcondition) {
        ArgumentValidator.validateNotNullAndNotEmpty(agent, "agent");
        return new Incentive(agent, precondition, postcondition);
    }
    
    private Incentive(String agent, Set<Proposition> precondition, Proposition postcondition) {
        super(precondition, postcondition);
        this.agent = agent;
    }

    public String getAgent() {
        return agent;
    }
    
    @Override
    public String toString() {
        return "Incentive(" + agent + ", " + super.toString() + ")";
    }
}
