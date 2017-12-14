package tr.edu.boun.cmpe.mas.akin.cpgen.protocol;

import tr.edu.boun.cmpe.mas.akin.cpgen.util.ArgumentValidator;

/**
 * @author Akin Gunay
 */
public class Commitment {

    private final String debtor;
    private final String creditor;
    private final Conjunction antecedent;
    private final Proposition consequent;

    public Commitment(String debtor, String creditor, Conjunction antecedent, Proposition consequent) {
        ArgumentValidator.validateNotNullAndNotEmptyString(debtor, "debtor");
        ArgumentValidator.validateNotNullAndNotEmptyString(creditor, "creditor");
        ArgumentValidator.validateNotNull(antecedent, "antecedent");
        ArgumentValidator.validateNotNull(consequent, "consequent");
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

    public Conjunction getAntecedent() {
        return antecedent;
    }

    public Proposition getConsequent() {
        return consequent;
    }

    @Override
    public String toString() {
        return new StringBuilder("Commitment(").
                append(debtor).append(", ").append(creditor).append(", ").
                append(antecedent).append(", ").
                append(consequent).append(")").
                toString();
    }
    
}
