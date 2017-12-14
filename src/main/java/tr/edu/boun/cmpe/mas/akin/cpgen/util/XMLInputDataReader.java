package tr.edu.boun.cmpe.mas.akin.cpgen.util;

import tr.edu.boun.cmpe.mas.akin.cpgen.data.InputData;
import tr.edu.boun.cmpe.mas.akin.cpgen.protocol.Proposition;
import tr.edu.boun.cmpe.mas.akin.cpgen.protocol.Service;
import tr.edu.boun.cmpe.mas.akin.cpgen.protocol.Incentive;
import tr.edu.boun.cmpe.mas.akin.cpgen.protocol.Capability;
import java.util.HashSet;
import java.util.Set;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import tr.edu.boun.cmpe.mas.akin.cpgen.protocol.Conjunction;

/**
 * A simple XML parser for creating an InputData object from an input file.
 * 
 * @author Akin Gunay
 */
public class XMLInputDataReader extends DefaultHandler {
    
    private final static String GENERATING_AGENT = "GeneratingAgent";  
    private final static String PROVIDING_AGENT = "ProvidingAgent";  
    private final static String INCENTIVIZED_AGENT = "IncentivizedAgent";
    private final static String GOAL = "Goal";
    private final static String CAPABLITIES = "Capabilities";
    private final static String CAPABLITY = "Capability";
    private final static String SERVICES = "Services";
    private final static String SERVICE = "Service";
    private final static String INCENTIVES = "Incentives";
    private final static String INCENTIVE = "Incentive";
    private final static String PRECONDITION = "Precondition";
    private final static String PROPOSITION = "Proposition";
    
    private InputData inputData;
    
    private String currentValue;
    private String currentAgent;
    private boolean isParsingPrecondition;
    private Set<Proposition> currentPrecondition;
    private Proposition currentPostcondition;
    
    public XMLInputDataReader() {
        inputData = null;
        currentValue = null;
        currentAgent = null;
        isParsingPrecondition = false;
        currentPrecondition = null;
        currentPostcondition = null;
    }
    
    public InputData getDataSet() {
        return inputData;
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        currentValue = "";
        if (qName.equals(CAPABLITIES) | qName.equals(SERVICES) | qName.equals(INCENTIVES)) {
            currentPrecondition = new HashSet<>();
        } else if (qName.equals(PRECONDITION)) {
            isParsingPrecondition = true;
        } else {
            // skip other elements
        }
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equals(GENERATING_AGENT)) {
            inputData = InputData.newInputData(currentValue);
        } else if (qName.equals(PROVIDING_AGENT) || qName.equals(INCENTIVIZED_AGENT)) {
            currentAgent = currentValue;
        } else if (qName.equals(PROPOSITION)) {
            if (isParsingPrecondition) {
                currentPrecondition.add(new Proposition(currentValue));
            } else {
                currentPostcondition = new Proposition(currentValue);
            }
        } else if (qName.equals(PRECONDITION)) {
            isParsingPrecondition = false;
        } else if (qName.equals(GOAL)) {
            inputData.addGoal(currentPostcondition);
        } else if (qName.equals(CAPABLITY)) {
            inputData.addCapability(new Capability(new Conjunction(currentPrecondition), currentPostcondition));
        } else if (qName.equals(SERVICE)) {
            inputData.addService(new Service(currentAgent, new Conjunction(currentPrecondition), currentPostcondition));
        } else if (qName.equals(INCENTIVE)) {
            inputData.addIncentive(new Incentive(currentAgent, new Conjunction(currentPrecondition), currentPostcondition));
        } else {
            // skip other elements
        }
    }
    
    @Override
    public void characters(char[] chars, int start, int length) {
        currentValue = new String(chars,start,length);
    }
}
