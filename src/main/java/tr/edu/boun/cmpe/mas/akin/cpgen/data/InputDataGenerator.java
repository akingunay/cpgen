package tr.edu.boun.cmpe.mas.akin.cpgen.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import tr.edu.boun.cmpe.mas.akin.cpgen.protocol.Capability;
import tr.edu.boun.cmpe.mas.akin.cpgen.protocol.Conjunction;
import tr.edu.boun.cmpe.mas.akin.cpgen.protocol.Incentive;
import tr.edu.boun.cmpe.mas.akin.cpgen.protocol.Proposition;
import tr.edu.boun.cmpe.mas.akin.cpgen.protocol.Service;
import tr.edu.boun.cmpe.mas.akin.cpgen.util.ArgumentValidator;

/**
 * Systematically generates input data for a protocol generator.
 * 
 * The generation process and meanings of its parameters is defined in "Akın
 * Günay, Michael Winikoff, and Pınar Yolum. Dynamically Generated Commitment
 * Protocols in Open Systems. Autonomous Agents and Multi-agent Systems, 29(2),
 * 192-229, 2015".
 * 
 * For each instance of this class, data generation is done only once when 
 * generateInputData() is called for the first time. Any further calls to 
 * generateInputData() returns the same data.
 * 
 * Input data generation cheat sheet:
 * 
 * gamma: number of initial goals for the generator agent
 * lambda: number of services before reaching a base capability for an initial goal
 * rho: number of unique propositions in a service precondition
 * sigma: number of alternative services for a goal
 * vareps: number of alternative incentives for a service
 * 
 * @author Akin Gunay
 */
public final class InputDataGenerator {
    
    private final String providerAgent;
    private final String propositionPrefix;
    private final int gamma;
    private final int lambda;
    private final int rho;
    private final int sigma;
    private final int vareps;
    
    private final InputData inputData;
    private boolean dataGenerationDone;
    private int propositionCount;

    private InputDataGenerator(String generatorAgent, String providerAgent, String propositionPrefix, int gamma, int lambda, int rho, int sigma, int vareps) {
        this.providerAgent = providerAgent;
        this.propositionPrefix = propositionPrefix;
        this.gamma = gamma;
        this.lambda = lambda;
        this.rho = rho;
        this.sigma = sigma;
        this.vareps = vareps;
        this.propositionCount = 0;
        this.dataGenerationDone = false;
        this.inputData = InputData.newInputData(generatorAgent);
    }

    public InputData generateInputData() {
        if (dataGenerationDone) {
            return inputData;
        } else {
            List<Proposition> currentLevelGoals = createGoalList();
            for (int level = 1 ; level < lambda ; level++) {
                List<Proposition> nextLevelGoals = new ArrayList<>();
                for (Proposition goal : currentLevelGoals) {
                    createServicesAndIncentivesForGoal(goal, nextLevelGoals);
                }
                currentLevelGoals = nextLevelGoals;
            }
            for (Proposition goal : currentLevelGoals) {
                inputData.addCapability(createCapability(goal));
            }
            dataGenerationDone = true;
            return inputData;
        }
    }

    private List<Proposition> createGoalList() {
        List<Proposition> goalList = new ArrayList<>(gamma);
        for (int i = 0 ; i < gamma ; i++) {
            Proposition goal = createProposition();
            inputData.addGoal(goal);
            goalList.add(goal);
        }
        return goalList;
    }

    private void createServicesAndIncentivesForGoal(Proposition goal, List<Proposition> nextLevelGoals) {
        for (int i = 0 ; i < sigma ; i++) {
            inputData.addService(createService(goal, nextLevelGoals));
        }
        for (int i = 0 ; i < vareps ; i++) {
            inputData.addIncentive(createIncentive(goal, nextLevelGoals));
        }
    }
    
    private Service createService(Proposition goal, List<Proposition> nextLevelGoals) {
        return new Service(providerAgent, new Conjunction(nextLevelGoals), goal);
    }
    
    private Incentive createIncentive(Proposition goal, List<Proposition> nextLevelGoals) {
        return new Incentive(providerAgent, new Conjunction(nextLevelGoals), goal);
    }
    
    private Capability createCapability(Proposition goal) {
        return new Capability(new Conjunction(), goal);
    }
    
    private Proposition createProposition() {
        return new Proposition(propositionPrefix + propositionCount++);
    }
    
    public static class Builder {
        
        private static final int DEFAULT_GAMMA = 2;
        private static final int DEFAULT_LAMBDA = 2;
        private static final int DEFAULT_RHO = 2;
        private static final int DEFAULT_SIGMA = 2;
        private static final int DEFAULT_VAREPS = 2;
        
        private String generatorAgent;
        private String providerAgent;
        private String propositionPrefix;
        private int gamma;
        private int lambda;
        private int rho;
        private int sigma;
        private int vareps;
        
        public Builder() {
            generatorAgent = "GeneratorAgent";
            providerAgent = "ProviderAgent";
            propositionPrefix = "PropositionPrefix";
            gamma = DEFAULT_GAMMA;
            lambda = DEFAULT_LAMBDA;
            rho = DEFAULT_RHO;
            sigma = DEFAULT_SIGMA;
            vareps = DEFAULT_VAREPS;
        }
        
        public Builder(InputDataGenerator originalInputDataGenerator) {
            generatorAgent = originalInputDataGenerator.inputData.getGeneratorAgent();
            providerAgent = originalInputDataGenerator.providerAgent;
            propositionPrefix = originalInputDataGenerator.propositionPrefix;
            gamma = originalInputDataGenerator.gamma;
            lambda = originalInputDataGenerator.lambda;
            rho = originalInputDataGenerator.rho;
            sigma = originalInputDataGenerator.sigma;
            vareps = originalInputDataGenerator.vareps;
        } 
        
        public void setAgent(String generatorAgent) {
            ArgumentValidator.validateNotNullAndNotEmptyString(generatorAgent, "generatorAgent");
            this.generatorAgent = generatorAgent;
        }

        public void setProviderAgent(String providerAgent) {
            ArgumentValidator.validateNotNullAndNotEmptyString(providerAgent, "providerAgent");
            this.providerAgent = providerAgent;
        }

        public void setPropositionPrefix(String propositionPrefix) {
            ArgumentValidator.validateNotNullAndNotEmptyString(propositionPrefix, "propositionPrefix");
            this.propositionPrefix = propositionPrefix;
        }

        public void setGamma(int gamma) {
            ArgumentValidator.validateLargerThan(0, gamma, "gamma");
            this.gamma = gamma;
        }
 
        public void setLambda(int lambda) {
            ArgumentValidator.validateLargerThan(0, lambda, "lambda");
            this.lambda = lambda;
        }
        
        public void setRho(int rho) {
            ArgumentValidator.validateLargerThan(0, rho, "rho");
            this.rho = rho;
        }

        public void setSigma(int sigma) {
            ArgumentValidator.validateLargerThan(0, sigma, "sigma");
            this.sigma = sigma;
        }

        public void setVareps(int vareps) {
            ArgumentValidator.validateLargerThan(0, vareps, "vareps");
            this.vareps = vareps;
        }
        
        public InputDataGenerator build() {
            return new InputDataGenerator(generatorAgent, providerAgent, propositionPrefix, gamma, lambda, rho, sigma, vareps);
        }
    }
    
}