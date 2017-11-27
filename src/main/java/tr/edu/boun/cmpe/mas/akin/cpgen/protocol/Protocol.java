package tr.edu.boun.cmpe.mas.akin.cpgen.protocol;

import java.util.HashSet;
import java.util.Set;
import tr.edu.boun.cmpe.mas.akin.cpgen.util.ArgumentValidator;

/**
 * @author Akin Gunay
 */
public class Protocol {

    private final Set<Commitment> commitments;
    
    public Protocol() {
        commitments = new HashSet<>();
    }
    
    public Protocol(Protocol originalProtocol) {
        commitments = new HashSet<>(originalProtocol.commitments);
    }
    
    public void addCommitment(Commitment commitment) {
        ArgumentValidator.validateNotNull(commitment, "commitment");
        commitments.add(commitment);
    }
    
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Protocol {");
        for (Commitment commitment : commitments) {
            str.append("\n\t").append(commitment);
        }
        return str.append("\n").append("}").toString();
    }
    
    public static Protocol union(Protocol firstProtocol, Protocol secondProtocol) {
        Protocol unionProtocol = new Protocol(firstProtocol);
        unionProtocol.commitments.addAll(secondProtocol.commitments);
        return unionProtocol;
    }
}
