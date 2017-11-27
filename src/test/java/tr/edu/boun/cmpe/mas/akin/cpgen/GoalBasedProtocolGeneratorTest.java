package tr.edu.boun.cmpe.mas.akin.cpgen;

import java.io.IOException;
import tr.edu.boun.cmpe.mas.akin.cpgen.generator.GoalBasedProtocolGenerator;
import tr.edu.boun.cmpe.mas.akin.cpgen.protocol.Protocol;
import java.util.Set;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;
import org.xml.sax.SAXException;
import tr.edu.boun.cmpe.mas.akin.cpgen.data.InputData;
import tr.edu.boun.cmpe.mas.akin.cpgen.util.XMLInputDataReader;

/**
 *
 * @author akin
 */
public class GoalBasedProtocolGeneratorTest {
    
    private final String SAT_CASE_1 = "TestInputSatisfiableCase1.xml";
    private final String SAT_CASE_2 = "TestInputSatisfiableCase2.xml";
    private final String SAT_CASE_3 = "TestInputSatisfiableCase3.xml";
    private final String UNSAT_CASE = "TestInputUnsatisfiableCase.xml";
    
    @Test
    public void testSatisfiableCase1() {
        testCase(SAT_CASE_1);
    }
    
    @Test
    public void testSatisfiableCase2() {
        testCase(SAT_CASE_2);
    }
    
    @Test
    public void testSatisfiableCase3() {
        testCase(SAT_CASE_3);
    }
    
    @Test
    public void testUnsatisfiableCase() {
        testCase(UNSAT_CASE);
    }
    
    private void testCase(String testCase) {
//        InputData inputData = readInputData(testCase);
//        Assume.assumeNotNull(inputData);
//        GoalBasedProtocolGenerator generator = new GoalBasedProtocolGenerator(inputData);
//        Set<Protocol> generatedProtocols = generator.generateProtocols();
//        Set<Protocol> expectedProtocols = ExpectedProtocolsForTestCases.getSatisfiableCase1();
//        Assert.assertEquals(generatedProtocols, expectedProtocols);
    }
    
    private InputData readInputData(String testCase) {
        try {
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            XMLInputDataReader reader = new XMLInputDataReader();
            parser.parse(getClass().getResourceAsStream(testCase), reader);
            return reader.getDataSet();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println("ERROR: Input file does not exist or invalid!");
            return null;
        }
    }
    
    
    
//    @Test
//    public void testGenerateProtocolsForUnsatisfiableData() {
//        GoalBasedProtocolGenerator generator = new GoalBasedProtocolGenerator(TestData.getUnsatisfiableDataSet());
//        Set<Protocol> protocols = generator.generateProtocols();
//        for (Protocol protocol : protocols) {
//            System.out.println(protocol);
//        }    
//    }
//    
//    @Test
//    public void testGenerateProtocolsForRunningExample() {
//        GoalBasedProtocolGenerator generator = new GoalBasedProtocolGenerator(TestData.getRunningExampleDataSet());
//        Set<Protocol> protocols = generator.generateProtocols();
//        for (Protocol protocol : protocols) {
//            System.out.println(protocol);
//        }
//    }
    
}
