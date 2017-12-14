package tr.edu.boun.cmpe.mas.akin.cpgen.protocol;

import java.util.Arrays;
import java.util.HashSet;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Akin Gunay
 */
public class ServiceTest {
    
    @Test (expected=NullPointerException.class)
    public void testNullAgentInitialization() {
        new Service(null, new Conjunction(), new Proposition("p"));
    }

    @Test (expected=IllegalArgumentException.class)
    public void testEmptyAgentInitialization() {
        new Service("", new Conjunction(), new Proposition("p"));
    }
    
    @Test (expected=NullPointerException.class)
    public void testNullPreconditionInitialization() {
        new Service("a", null, new Proposition("p"));
    }
    
    @Test (expected=NullPointerException.class)
    public void testNullPostconditionInitialization() {
        new Service("a", new Conjunction(), null);
    }
    
    @Test (expected=NullPointerException.class)
    public void testNullInitialization1() {
        new Service(null, null, null);
    }
    
    @Test
    public void testEquals() {
        Service firstService = new Service("a", new Conjunction(), new Proposition("p"));
        Service secondService = new Service("a", new Conjunction(), new Proposition("p"));
        assertEquals(firstService, secondService);
    }
    
    @Test
    public void testNotEqualAgent() {
        Service firstService = new Service("a1", new Conjunction(), new Proposition("p"));
        Service secondService = new Service("a2", new Conjunction(), new Proposition("p"));
        assertNotEquals(firstService, secondService);
    }
    
    @Test
    public void testNotEqualPreconditions() {
        Service firstService = new Service("a", new Conjunction(new HashSet<>(Arrays.asList(new Proposition("p")))), new Proposition("p"));
        Service secondService = new Service("a", new Conjunction(), new Proposition("p"));
        assertNotEquals(firstService, secondService);
    }
    
    @Test
    public void testNotEqualPostconditions() {
        Service firstService = new Service("a", new Conjunction(), new Proposition("p1"));
        Service secondService = new Service("a", new Conjunction(), new Proposition("p2"));
        assertNotEquals(firstService, secondService);
    }

    @Test
    public void testEqualsHashCodeCompatability() {
        Service firstService = new Service("a", new Conjunction(), new Proposition("p"));
        Service secondService = new Service("a", new Conjunction(), new Proposition("p"));
        assertEquals(firstService.hashCode(), secondService.hashCode());
        
    }
    
}
