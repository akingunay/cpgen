package tr.edu.boun.cmpe.mas.akin.cpgen.protocol;

import java.util.Arrays;
import java.util.HashSet;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Akin Gunay
 */
public class IncentiveTest {
    
    @Test (expected=NullPointerException.class)
    public void testNullAgentInitialization() {
        new Incentive(null, new Conjunction(), new Proposition("p"));
    }

    @Test (expected=IllegalArgumentException.class)
    public void testEmptyAgentInitialization() {
        new Incentive("", new Conjunction(), new Proposition("p"));
    }
    
    @Test (expected=NullPointerException.class)
    public void testNullPreconditionInitialization() {
        new Incentive("a", null, new Proposition("p"));
    }
    
    @Test (expected=NullPointerException.class)
    public void testNullPostconditionInitialization() {
        new Incentive("a", new Conjunction(), null);
    }
    
    @Test (expected=NullPointerException.class)
    public void testNullInitialization1() {
        new Incentive(null, null, null);
    }
    
    @Test
    public void testEquals() {
        Incentive firstIncentive = new Incentive("a", new Conjunction(), new Proposition("p"));
        Incentive secondIncentive = new Incentive("a", new Conjunction(), new Proposition("p"));
        assertEquals(firstIncentive, secondIncentive);
    }
    
    @Test
    public void testNotEqualAgent() {
        Incentive firstIncentive = new Incentive("a1", new Conjunction(), new Proposition("p"));
        Incentive secondIncentive = new Incentive("a2", new Conjunction(), new Proposition("p"));
        assertNotEquals(firstIncentive, secondIncentive);
    }
    
    @Test
    public void testNotEqualPreconditions() {
        Incentive firstIncentive = new Incentive("a", new Conjunction(new HashSet<>(Arrays.asList(new Proposition("p")))), new Proposition("p"));
        Incentive secondIncentive = new Incentive("a", new Conjunction(), new Proposition("p"));
        assertNotEquals(firstIncentive, secondIncentive);
    }
    
    @Test
    public void testNotEqualPostconditions() {
        Incentive firstIncentive = new Incentive("a", new Conjunction(), new Proposition("p1"));
        Incentive secondIncentive = new Incentive("a", new Conjunction(), new Proposition("p2"));
        assertNotEquals(firstIncentive, secondIncentive);
    }

    @Test
    public void testEqualsHashCodeCompatability() {
        Incentive firstIncentive = new Incentive("a", new Conjunction(), new Proposition("p"));
        Incentive secondIncentive = new Incentive("a", new Conjunction(), new Proposition("p"));
        assertEquals(firstIncentive.hashCode(), secondIncentive.hashCode());
        
    }
    
}
