package tr.edu.boun.cmpe.mas.akin.cpgen;

import tr.edu.boun.cmpe.mas.akin.cpgen.util.XMLInputDataReader;
import tr.edu.boun.cmpe.mas.akin.cpgen.generator.ProtocolGenerator;
import tr.edu.boun.cmpe.mas.akin.cpgen.generator.GoalBasedProtocolGenerator;
import tr.edu.boun.cmpe.mas.akin.cpgen.generator.ProtocolBasedProtocolGenerator;
import tr.edu.boun.cmpe.mas.akin.cpgen.protocol.Protocol;
import java.io.IOException;
import java.util.Set;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import tr.edu.boun.cmpe.mas.akin.cpgen.data.InputDataGenerator;

/**
 * @author Akin Gunay
 */
public class CPGen {
    
    private static final int ARG_ALGORITHM = 0;
    private static final int ARG_VERBOSE = 1;
    private static final int ARG_INPUT_FILE_PATH = 2;
    private static final int ARG_GAMMA = 2;
    private static final int ARG_LAMBDA = 3;
    private static final int ARG_RHO = 4;
    private static final int ARG_SIGMA = 5;
    private static final int ARG_VAREPS = 6;
    
    private static final String GOAL_BASED = "g";
    private static final String PROTOCOL_BASED = "p";
    private static final String PRINT_VERBOSE = "v";
    private static final String PRINT_SUMMARY = "s";
    
    private static String algorithm;
    private static boolean printVerbose;
    
    public static void main(String[] args) {
        ProtocolGenerator protocolGenerator = createProtocolGeneratorFromArgs(args);
        
        if (protocolGenerator != null) {
            long initialTime = System.currentTimeMillis();
            Set<Protocol> protocols = protocolGenerator.generateProtocols();
            long executionTime = System.currentTimeMillis() - initialTime;
            printResults(protocols, executionTime);
        }
    }
    
    private static ProtocolGenerator createProtocolGeneratorFromArgs(String[] args) {
        if (args.length == 3) {
            return createProtocolGeneratorFromXMLInput(args);
        } else if (args.length == 7) {
            return createProtocolGeneratorFromGeneratedData(args);
        } else {
            printHelperText();
            return null;
        }
    }
    
    private static void printHelperText() {
        StringBuilder str = new StringBuilder("Run cpgen using one of the following configurations:\n");
        str.append("For providing input data in a file: cpgen <algorithm> <output> <path>\n");
        str.append("For generating indput data parametrically: cpgen <algoritm> <output> <gamma> <lambda> <rho> <sigma> <vareps>\n");
        str.append("OPTIONS:\n");
        str.append("<algorithm>:\n");
        str.append("\t\'").append(GOAL_BASED).append("\' for GoalBased Algorithm\n");
        str.append("\t\'").append(PROTOCOL_BASED).append("\' for ProtocolBased Algorithm\n");
        str.append("<output>:\n");
        str.append("\t\'").append(PRINT_VERBOSE).append("\' for verbose output\n");
        str.append("\t\'").append(PRINT_SUMMARY).append("\' for summary output\n");
        str.append("<path>: path to input data file\n");
        str.append("<gamma>: number of initial goals for the generator agent\n");
        str.append("<lambda>: number of services before reaching a base capability for an initial goal\n");
        str.append("<rho>: number of unique propositions in a service precondition\n");
        str.append("<sigma>: number of alternative services for a goal\n");
        str.append("<vareps>: number of alternative incentives for a service\n");
        System.out.println(str);
    }
    
    private static ProtocolGenerator createProtocolGeneratorFromXMLInput(String[] args) {
        try {
            setAlgorithm(args[ARG_ALGORITHM]);
            setVerbose(args[ARG_VERBOSE]);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
        try {
            setAlgorithm(args[ARG_ALGORITHM]);
            setVerbose(args[ARG_VERBOSE]);
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            XMLInputDataReader reader = new XMLInputDataReader();
            parser.parse(CPGen.class.getClassLoader().getResourceAsStream(args[ARG_INPUT_FILE_PATH]), reader);
            if (algorithm.equals(GOAL_BASED)) {
                return new GoalBasedProtocolGenerator(reader.getDataSet());
            } else {
                return new ProtocolBasedProtocolGenerator(reader.getDataSet());
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println("ERROR: Input file does not exist or invalid!");
            return null;
        }
    }
    
    private static ProtocolGenerator createProtocolGeneratorFromGeneratedData(String args[]) {
        try {
            setAlgorithm(args[ARG_ALGORITHM]);
            setVerbose(args[ARG_VERBOSE]);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
        InputDataGenerator.Builder inputDataGeneratorBuilder = new InputDataGenerator.Builder();
        inputDataGeneratorBuilder.setGamma(Integer.parseInt(args[ARG_GAMMA]));
        inputDataGeneratorBuilder.setLambda(Integer.parseInt(args[ARG_LAMBDA]));
        inputDataGeneratorBuilder.setRho(Integer.parseInt(args[ARG_RHO]));
        inputDataGeneratorBuilder.setSigma(Integer.parseInt(args[ARG_SIGMA]));
        inputDataGeneratorBuilder.setVareps(Integer.parseInt(args[ARG_VAREPS]));
        if (algorithm.equals(GOAL_BASED)) {
            return new GoalBasedProtocolGenerator(inputDataGeneratorBuilder.build().generateInputData());
        } else {
            return new ProtocolBasedProtocolGenerator(inputDataGeneratorBuilder.build().generateInputData());
        }
    }

    private static void setAlgorithm(String algorithmOption) {
        if (algorithmOption.equals(GOAL_BASED) || algorithmOption.equals(PROTOCOL_BASED)) {
            CPGen.algorithm = algorithmOption;
        } else {
            throw new IllegalArgumentException("ERROR: Possible options for algorithm are " + GOAL_BASED + " (Goal Based) and " + PROTOCOL_BASED + " (Protocol Based).");
        }
    }
    
    private static void setVerbose(String verboseOption) {
        if (verboseOption.equals(PRINT_VERBOSE)) {
            CPGen.printVerbose = true;
        } else if (verboseOption.equals(PRINT_SUMMARY)) {
            CPGen.printVerbose = false;
        } else {
            throw new IllegalArgumentException("ERROR: Possible options for result printing are " + PRINT_VERBOSE + " (verbose) and " + PRINT_SUMMARY + " (summary).");
        }
    }
    
    private static void printResults(Set<Protocol> protocols, long executionTime) {
        StringBuilder str = new StringBuilder("\nProtocols are generated by ").append(algorithm).
                append(" algorithm using following input data: ").append("data").append("\n").
                append("Number of generated protocols: ").append(protocols.size()).append("\n").
                append("Time spent for generation: ").append(executionTime).append(" miliseconds\n");
        if (printVerbose) {
            str.append("=== Generated Protocols ===\n");
            int cnt = 1;
            for (Protocol protocol : protocols) {
                str.append("Protocol ").append(cnt++).append(":").append(protocol);
            }
        }
        System.out.print(str);
    }
    
}
