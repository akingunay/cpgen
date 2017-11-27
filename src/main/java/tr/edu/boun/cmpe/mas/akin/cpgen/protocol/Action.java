package tr.edu.boun.cmpe.mas.akin.cpgen.protocol;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import tr.edu.boun.cmpe.mas.akin.cpgen.util.ArgumentValidator;
import tr.edu.boun.cmpe.mas.akin.cpgen.util.SetPrinter;

/**
 * @author Akin Gunay
 */
abstract class Action {
    
    private final Set<Proposition> precondition;
    private final Proposition postcondition;

    public Action(Set<Proposition> precondition, Proposition postcondition) {
        ArgumentValidator.validateNotNull(precondition, "precondition");
        ArgumentValidator.validateNotNull(postcondition, "postcondition");
        this.precondition = new HashSet<>(precondition);
        this.postcondition = postcondition;
        this.precondition.equals(this);
    }
    
    public Set<Proposition> getPreconditions() {
        return Collections.unmodifiableSet(precondition);
    }
    
    public Proposition getPostcondition() {
        return postcondition;
    }
    
    @Override
    public String toString() {
        return new StringBuilder(SetPrinter.setPrinter(precondition)).append(", ").append(postcondition).toString();
    }
}
