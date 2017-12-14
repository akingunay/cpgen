package tr.edu.boun.cmpe.mas.akin.cpgen.protocol;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import tr.edu.boun.cmpe.mas.akin.cpgen.util.ArgumentValidator;
import tr.edu.boun.cmpe.mas.akin.cpgen.util.SetPrinter;

/**
 * This class represents a conjunction of propositions. This class is immutable.
 * Hence, it does not support any method of the implemented Collection that
 * changes the content of the collection (e.g., add(Proposition e)).
 * 
 * @author Akin Gunay
 */
public class Conjunction implements Collection<Proposition> {
    
    private final Set<Proposition> propositions;

    public Conjunction() {
        propositions = new HashSet<>();
    }
    
    public Conjunction(Conjunction conjunction) {
        ArgumentValidator.validateNotNull(conjunction, "conjunction");
        this.propositions = conjunction.propositions;
    }
    
    public Conjunction(Collection<Proposition> propositions) {
        ArgumentValidator.validateNotNull(propositions, "propositions");
        this.propositions = new HashSet<>(propositions);
    }
    
    public static Conjunction compose(Conjunction firtsConjunction, Conjunction secondConjunction) {
        Set<Proposition> propositions = new HashSet<>(firtsConjunction);
        propositions.addAll(secondConjunction);
        return new Conjunction(propositions);
    }
    
    @Override
    public int size() {
        return propositions.size();
    }

    @Override
    public boolean isEmpty() {
        return propositions.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return propositions.contains(o);
    }
    
    @Override
    public boolean containsAll(Collection<?> clctn) {
        return propositions.containsAll(clctn);
    }
    
    @Override
    public Object[] toArray() {
        return propositions.toArray();
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return propositions.toArray(ts);
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(this.propositions);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Conjunction other = (Conjunction) obj;
        return Objects.equals(this.propositions, other.propositions);
    }

    @Override
    public String toString() {
        return SetPrinter.printSet(propositions);
    }
    
    @Override
    public Iterator<Proposition> iterator() {
        return new PreconditionIterator(propositions);
    }

    private static class PreconditionIterator implements Iterator<Proposition> {
        
        private final Iterator<Proposition> iterator;
        
        public PreconditionIterator(Set<Proposition> propositions) {
            iterator = propositions.iterator();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public Proposition next() {
            return iterator.next();
        }
        
    }

    @Override
    public boolean add(Proposition e) {
        throw new UnsupportedOperationException("Preconditions are final and cannot be altered.");
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Preconditions are final and cannot be altered.");
    }

    @Override
    public boolean addAll(Collection<? extends Proposition> clctn) {
        throw new UnsupportedOperationException("Preconditions are final and cannot be altered.");
    }

    @Override
    public boolean removeAll(Collection<?> clctn) {
        throw new UnsupportedOperationException("Preconditions are final and cannot be altered.");
    }

    @Override
    public boolean retainAll(Collection<?> clctn) {
        throw new UnsupportedOperationException("Preconditions are final and cannot be altered.");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Preconditions are final and cannot be altered.");
    }

}
