package tr.edu.boun.cmpe.mas.akin.cpgen.protocol;

import java.util.Objects;
import tr.edu.boun.cmpe.mas.akin.cpgen.util.ArgumentValidator;

/**
 * @author Akin Gunay
 */
public class Proposition {

    private final String label;
    
    public Proposition(String label) {
        ArgumentValidator.validateNotNullAndNotEmpty(label, "label");
        this.label = label;
    }
    
    public String getLabel() {
        return label;
    }

    @Override
    public int hashCode() {
        return label.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Proposition other = (Proposition) obj;
        return this.label.equals(other.label);
    }

    @Override
    public String toString() {
        return label;
    }
}
