package tr.edu.boun.cmpe.mas.akin.cpgen.protocol;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Akin Gunay
 */
public class ConjunctionTest {
    
    @Test (expected=NullPointerException.class)
    public void testNullInitialization() {
        Conjunction c = new Conjunction(null);
    }
    
    @Test
    public void testEquals() {
        assertEquals(new Conjunction(), new Conjunction());
        Proposition p1a = new Proposition("p1");
        Proposition p2a = new Proposition("p2");
        Set<Proposition> propositionsA = new HashSet<>(Arrays.asList(p1a, p2a));
        Conjunction firstConjunction = new Conjunction(propositionsA);
        Proposition p1b = new Proposition("p1");
        Proposition p2b = new Proposition("p2");
        Set<Proposition> propositionsB = new HashSet<>(Arrays.asList(p1b, p2b));
        Conjunction secondConjunction = new Conjunction(propositionsB);
        assertEquals(firstConjunction, secondConjunction);
        Conjunction thirdConjunction = new Conjunction(firstConjunction);
        assertEquals(firstConjunction, thirdConjunction);
        assertEquals(secondConjunction, thirdConjunction);
    }
    
    @Test
    public void testNotEquals() {
        Proposition p1a = new Proposition("p1");
        Proposition p2a = new Proposition("p2");
        Set<Proposition> propositionsA = new HashSet<>(Arrays.asList(p1a, p2a));
        Conjunction firstConjunction = new Conjunction(propositionsA);
        Proposition p1b = new Proposition("p1");
        Set<Proposition> propositionsB = new HashSet<>(Arrays.asList(p1b));
        Conjunction secondConjunction = new Conjunction(propositionsB);
        assertNotEquals(firstConjunction, secondConjunction);
        Conjunction thirdConjunction = new Conjunction(firstConjunction);
        assertNotEquals(secondConjunction, thirdConjunction);
    }

    @Test
    public void testEqualsHashCodeCompatability() {
        assertEquals((new Conjunction()).hashCode(), (new Conjunction()).hashCode());
        Proposition p1a = new Proposition("p1");
        Proposition p2a = new Proposition("p2");
        Set<Proposition> propositionsA = new HashSet<>(Arrays.asList(p1a, p2a));
        Conjunction firstConjunction = new Conjunction(propositionsA);
        Proposition p1b = new Proposition("p1");
        Proposition p2b = new Proposition("p2");
        Set<Proposition> propositionsB = new HashSet<>(Arrays.asList(p1b, p2b));
        Conjunction secondConjunction = new Conjunction(propositionsB);
        assertEquals(firstConjunction.hashCode(), secondConjunction.hashCode());
        Conjunction thirdConjunction = new Conjunction(firstConjunction);
        assertEquals(firstConjunction.hashCode(), thirdConjunction.hashCode());
        assertEquals(secondConjunction.hashCode(), thirdConjunction.hashCode());
    }
    
}
