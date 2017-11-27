package tr.edu.boun.cmpe.mas.akin.cpgen;

import tr.edu.boun.cmpe.mas.akin.cpgen.protocol.Proposition;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author akin
 */
public class PropositionTest {

    @Test
    public void testEquals() {
        Assert.assertEquals(new Proposition("p1"), new Proposition("p1"));
        Assert.assertNotSame(new Proposition("p1"), new Proposition("p2"));
    }
}
