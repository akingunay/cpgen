package tr.edu.boun.cmpe.mas.akin.cpgen.protocol;

import java.util.Objects;
import tr.edu.boun.cmpe.mas.akin.cpgen.util.ArgumentValidator;

/**
 * @author Akin Gunay
 */
abstract class Action {
    
    private final Conjunction precondition;
    private final Proposition postcondition;

    protected Action(Conjunction precondition, Proposition postcondition) {
        ArgumentValidator.validateNotNull(precondition, "precondition");
        ArgumentValidator.validateNotNull(postcondition, "postcondition");
        this.precondition = precondition;
        this.postcondition = postcondition;
    }
    
    public Conjunction getPrecondition() {
        return precondition;
    }
    
    public Proposition getPostcondition() {
        return postcondition;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.precondition);
        hash = 83 * hash + Objects.hashCode(this.postcondition);
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
        final Action other = (Action) obj;
        if (!Objects.equals(this.precondition, other.precondition)) {
            return false;
        }
        return Objects.equals(this.postcondition, other.postcondition);
    }

    @Override
    public String toString() {
        return new StringBuilder().append(precondition).append(", ").append(postcondition).toString();
    }
}
