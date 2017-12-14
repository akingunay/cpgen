package tr.edu.boun.cmpe.mas.akin.cpgen.protocol;

import java.util.Arrays;
import java.util.HashSet;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Akin Gunay
 */
public class CapabilityTest {
    
    @Test (expected=NullPointerException.class)
    public void testNullPreconditionInitialization() {
        new Capability(null, new Proposition("p"));
    }
    
    @Test (expected=NullPointerException.class)
    public void testNullPostconditionInitialization() {
        new Capability(new Conjunction(), null);
    }
    
    @Test (expected=NullPointerException.class)
    public void testNullInitialization1() {
        new Capability(null, null);
    }
    
    @Test
    public void testEquals() {
        Capability firstCapability = new Capability(new Conjunction(), new Proposition("p"));
        Capability secondCapability = new Capability(new Conjunction(), new Proposition("p"));
        assertEquals(firstCapability, secondCapability);
    }
    
    @Test
    public void testNotEqualPreconditions() {
        Capability firstCapability = new Capability(new Conjunction(new HashSet<>(Arrays.asList(new Proposition("p")))), new Proposition("p"));
        Capability secondCapability = new Capability(new Conjunction(), new Proposition("p"));
        assertNotEquals(firstCapability, secondCapability);
    }
    
    @Test
    public void testNotEqualPostconditions() {
        Capability firstCapability = new Capability(new Conjunction(), new Proposition("p1"));
        Capability secondCapability = new Capability(new Conjunction(), new Proposition("p2"));
        assertNotEquals(firstCapability, secondCapability);
    }

    @Test
    public void testEqualsHashCodeCompatability() {
        Capability firstCapability = new Capability(new Conjunction(), new Proposition("p"));
        Capability secondCapability = new Capability(new Conjunction(), new Proposition("p"));
        assertEquals(firstCapability.hashCode(), secondCapability.hashCode());
    }
    
}
