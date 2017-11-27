package tr.edu.boun.cmpe.mas.akin.cpgen.protocol;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import tr.edu.boun.cmpe.mas.akin.cpgen.util.ArgumentValidator;
import tr.edu.boun.cmpe.mas.akin.cpgen.util.SetPrinter;

/**
 * @author Akin Gunay
 */
public class Commitment {

    private final String debtor;
    private final String creditor;
    private final Set<Proposition> antecedent;
    private final Set<Proposition> consequent;

    private Commitment(String debtor, String creditor, Set<Proposition> antecedent, Set<Proposition> consequent) {
        this.debtor = debtor;
        this.creditor = creditor;
        this.antecedent = antecedent;
        this.consequent = consequent;
    }

    public String getDebtor() {
        return debtor;
    }

    public String getCreditor() {
        return creditor;
    }

    public Set<Proposition> getAntecedent() {
        return Collections.unmodifiableSet(antecedent);
    }

    public Set<Proposition> getConsequent() {
        return Collections.unmodifiableSet(consequent);
    }

    @Override
    public String toString() {
        return new StringBuilder("Commitment(").
                append(debtor).append(", ").append(creditor).append(", ").
                append(SetPrinter.setPrinter(antecedent)).append(", ").
                append(SetPrinter.setPrinter(consequent)).append(")").
                toString();
    }
    
    public static class Builder {
        
        private String debtor;
        private String creditor;
        private final Set<Proposition> antecedent;
        private final Set<Proposition> consequent;
        
        public Builder() {
            antecedent = new HashSet<>();
            consequent = new HashSet<>();
        }
        
        public void setDebtor(String debtor) {
            ArgumentValidator.validateNotNullAndNotEmpty(debtor, "debtor");
            this.debtor = debtor;
        }
        
        public void setCreditor(String creditor) {
            ArgumentValidator.validateNotNullAndNotEmpty(creditor, "creditor");
            this.creditor = creditor;
        }
        
        public void addPropositionToAntecedent(Proposition proposition) {
            ArgumentValidator.validateNotNull(proposition, "proposition");
            antecedent.add(proposition);
        }
        
        public void addSetOfPropositionsToAntecedent(Set<Proposition> propositions) {
            ArgumentValidator.validateNotNull(propositions, "propositions");
            antecedent.addAll(propositions);
        }
        
        public void addPropositionToConsequent(Proposition proposition) {
            ArgumentValidator.validateNotNull(proposition, "proposition");
            consequent.add(proposition);
        }
        
        public void addSetOfPropositionsToConsequent(Set<Proposition> propositions) {
            ArgumentValidator.validateNotNull(propositions, "propositions");
            consequent.addAll(propositions);
        }
        
        public Commitment build(String debtor, String creditor, Set<Proposition> antecedentsFromService, Set<Proposition> antecedentsFromIncentive, Proposition consequent) {
            setDebtor(debtor);
            setCreditor(creditor);
            addSetOfPropositionsToAntecedent(antecedentsFromService);
            addSetOfPropositionsToAntecedent(antecedentsFromIncentive);
            addPropositionToConsequent(consequent);
            return build();
        }
        
        public Commitment build() {
            if (debtor == null) {
                throw new IllegalStateException("Debtor is not set.");
            }
            if (creditor == null) {
                throw new IllegalStateException("Creditor is not set.");
            }
            if (consequent.isEmpty()) {
                throw new IllegalStateException("Consequent is not set.");
            }
            return new Commitment(debtor, creditor, antecedent, consequent);
        }
    }
    
}
