package tr.edu.boun.cmpe.mas.akin.cpgen.protocol;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Akin Gunay
 */
public class PropositionTest {
    
    @Test (expected=NullPointerException.class)
    public void testNullInitialization() {
        new Proposition(null);
    }

    @Test (expected=IllegalArgumentException.class)
    public void testEmptyLabelInitialization() {
        new Proposition("");
    }
    
    @Test
    public void testEquals() {
        assertEquals(new Proposition("p1"), new Proposition("p1"));
    }
    
    @Test
    public void testNotEquals() {
        assertNotEquals(new Proposition("p1"), new Proposition("p2"));
    }

    @Test
    public void testEqualsHashCodeCompatability() {
        assertEquals((new Proposition("p1")).hashCode(), (new Proposition("p1")).hashCode());
    }
}
