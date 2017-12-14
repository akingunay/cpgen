package tr.edu.boun.cmpe.mas.akin.cpgen.protocol;

import java.util.Objects;
import tr.edu.boun.cmpe.mas.akin.cpgen.util.ArgumentValidator;

/**
 * @author Akin Gunay
 */
public class Incentive extends Action {

    private final String agent;
    private final int hash;
    
    public Incentive(String agent, Conjunction precondition, Proposition postcondition) {
        super(precondition, postcondition);
        ArgumentValidator.validateNotNullAndNotEmptyString(agent, "agent");
        this.agent = agent;
        hash = computeHash();
    }

    private int computeHash() {
        int tmpHash = 7;
        tmpHash = 31 * tmpHash + super.hashCode();
        tmpHash = 31 * tmpHash + Objects.hashCode(agent);
        return tmpHash;
    }
    
    public String getAgent() {
        return agent;
    }

    @Override
    public int hashCode() {
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        final Incentive other = (Incentive) obj;
        return Objects.equals(this.agent, other.agent);
    }

    @Override
    public String toString() {
        return "Incentive(" + agent + ", " + super.toString() + ")";
    }
}
