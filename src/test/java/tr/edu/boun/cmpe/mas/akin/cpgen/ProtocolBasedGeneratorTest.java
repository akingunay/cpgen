package tr.edu.boun.cmpe.mas.akin.cpgen;

import tr.edu.boun.cmpe.mas.akin.cpgen.generator.ProtocolBasedProtocolGenerator;
import tr.edu.boun.cmpe.mas.akin.cpgen.protocol.Protocol;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

/**
 *
 * @author akin
 */
public class ProtocolBasedGeneratorTest {
    
    @Test
    public void testGenerateProtocolsForUnsatisfiableData() {
        ProtocolBasedProtocolGenerator generator = new ProtocolBasedProtocolGenerator(TestData.getUnsatisfiableDataSet());
        Set<Protocol> protocols = generator.generateProtocols();
        for (Protocol protocol : protocols) {
            System.out.println(protocol);
        }    
    }
    
    @Test
    public void testGenerateProtocolsForRunningExample() {
        ProtocolBasedProtocolGenerator generator = new ProtocolBasedProtocolGenerator(TestData.getRunningExampleDataSet());
        Set<Protocol> protocols = generator.generateProtocols();
//        for (Protocol protocol : protocols) {
//            System.out.println(protocol);
//        }
    }
    
}
