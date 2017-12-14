package tr.edu.boun.cmpe.mas.akin.cpgen.protocol;

/**
 * @author Akin Gunay
 */
public class Capability extends Action {
    
    public Capability(Conjunction precondition, Proposition postcondition) {
        super(precondition, postcondition);
    }

    @Override
    public String toString() {
        return "Capability(" + super.toString() + ")";
    }
}
