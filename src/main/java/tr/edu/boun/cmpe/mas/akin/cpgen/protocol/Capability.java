package tr.edu.boun.cmpe.mas.akin.cpgen.protocol;

import java.util.Set;

/**
 * @author Akin Gunay
 */
public class Capability extends Action {
    
    public static Capability newCapability(Set<Proposition> precondition, Proposition postcondition) {
        return new Capability(precondition, postcondition);
    }
    
    private Capability(Set<Proposition> precondition, Proposition postcondition) {
        super(precondition, postcondition);
    }

    @Override
    public String toString() {
        return "Capability(" + super.toString() + ")";
    }
}
