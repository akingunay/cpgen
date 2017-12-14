package tr.edu.boun.cmpe.mas.akin.cpgen;

import tr.edu.boun.cmpe.mas.akin.cpgen.data.InputData;
import tr.edu.boun.cmpe.mas.akin.cpgen.protocol.Service;
import tr.edu.boun.cmpe.mas.akin.cpgen.protocol.Incentive;
import tr.edu.boun.cmpe.mas.akin.cpgen.protocol.Proposition;
import tr.edu.boun.cmpe.mas.akin.cpgen.protocol.Capability;
import java.util.Arrays;
import java.util.HashSet;

public class TestData {
    
//    private static final InputData unsatisfiableDataSet;
//    private static final InputData runningExampleDataSet;
//    
//    static {
//        unsatisfiableDataSet = compileUnsatisfiableDataSet();
//        runningExampleDataSet = compileRunningExampleDataSet();
//    }
//    
//    public static InputData getUnsatisfiableDataSet() {
//        return unsatisfiableDataSet;
//    }
//    
//    public static InputData getRunningExampleDataSet() {
//        return runningExampleDataSet;
//    }
//    
//    private TestData() {}
//    
//    private static InputData compileUnsatisfiableDataSet() {
//        InputData dataSet = InputData.newInputData("Consumer");
//        String producer = "Producer";
//        String supplier = "Supplier";
//        Proposition p0 = new Proposition("p0");
//        Proposition p1 = new Proposition("p1");
//        Proposition p2 = new Proposition("p2");
//        Proposition p3 = new Proposition("p3");
//        Proposition p4 = new Proposition("p4");
//        Proposition p5 = new Proposition("p5");
//        dataSet.addGoal(p1);
//        dataSet.addService(Service.newService(producer, new HashSet<>(Arrays.asList(p2)), p1));
//        dataSet.addIncentive(Incentive.newIncentive(producer, new HashSet<>(Arrays.asList(p3)), p1));
//        dataSet.addService(Service.newService(supplier, new HashSet<>(Arrays.asList(p4)), p2));
//        dataSet.addIncentive(Incentive.newIncentive(supplier, new HashSet<>(Arrays.asList(p5)), p2));
//        dataSet.addCapability(new Capability(new HashSet<Proposition>(), p0));
//        dataSet.addCapability(new Capability(new HashSet<Proposition>(), p3));
//        dataSet.addCapability(new Capability(new HashSet<Proposition>(), p4));
//        return dataSet;
//    }
//    
//    private static InputData compileRunningExampleDataSet() {
//        InputData dataSet = InputData.newInputData("Customer");
//        String retailer = "Retailer";
//        String merchant = "Merchant";
//        String firstBuilder = "FirstBuilder";
//        String secondBuilder = "SecondBuilder";
//        Proposition haveFurniture = new Proposition("HaveFurniture");
//        Proposition haveMaterials = new Proposition("HaveMaterials");
//        Proposition haveTools = new Proposition("HaveTools");
//        Proposition materialsProvided = new Proposition("MaterialsProvided");
//        Proposition toolsProvided = new Proposition("ToolsProvided");
//        Proposition materialsPaidCash = new Proposition("MaterialsPaidCash");
//        Proposition materialsPaidCard = new Proposition("MaterialsPaidCard");
//        Proposition toolsPaidCash = new Proposition("ToolsPaidCash");
//        Proposition toolsPaidCard = new Proposition("ToolsPaidCard");
//        Proposition furniturePaid = new Proposition("FurniturePaid");
//        Proposition firstBuilderPaid = new Proposition("FirstBuilderPaid");
//        Proposition secondBuilderPaid = new Proposition("SecondBuilderPaid");
//        // Goal(HaveFurniture)
//        dataSet.addGoal(haveFurniture);
//        // Service(Retailer, {}, HaveMaterials)
//        dataSet.addService(Service.newService(retailer, new HashSet<Proposition>(), haveMaterials));
//        // Service(Retailer, {}, HaveTools)
//        dataSet.addService(Service.newService(retailer, new HashSet<Proposition>(), haveTools));
//        // Service(Merchant, {}, HaveFurniture)
//        dataSet.addService(Service.newService(merchant, new HashSet<Proposition>(), haveFurniture));
//        // Service(FirstBuilder, {MaterialsProvided}, HaveFurniture)
//        dataSet.addService(Service.newService(firstBuilder, new HashSet<>(Arrays.asList(materialsProvided)), haveFurniture));
//        // Service(SecondBuilder, {MaterialsProvided, ToolsProvided}, HaveFurniture)
//        dataSet.addService(Service.newService(secondBuilder, new HashSet<>(Arrays.asList(materialsProvided, toolsProvided)), haveFurniture));
//        // Incentive(Retailer, {MaterialsPaidCard}, HaveMaterials)
//        dataSet.addIncentive(Incentive.newIncentive(retailer, new HashSet<>(Arrays.asList(materialsPaidCard)), haveMaterials));
//        // Incentive(Retailer, {MaterialsPaidCash}, HaveMaterials)
//        dataSet.addIncentive(Incentive.newIncentive(retailer, new HashSet<>(Arrays.asList(materialsPaidCash)), haveMaterials));
//        // Incentive(Retailer, {ToolsPaidCard}, HaveTools)
//        dataSet.addIncentive(Incentive.newIncentive(retailer, new HashSet<>(Arrays.asList(toolsPaidCard)), haveTools));
//        // Incentive(Retailer, {ToolsPaidCash}, HaveTools)
//        dataSet.addIncentive(Incentive.newIncentive(retailer, new HashSet<>(Arrays.asList(toolsPaidCash)), haveTools));
//        // Incentive(Merchant, {FurniturePaid}, HaveFurniture)
//        dataSet.addIncentive(Incentive.newIncentive(merchant, new HashSet<>(Arrays.asList(furniturePaid)), haveFurniture));
//        // Incentive(FirstBuilder, {FirstBuilderPaid}, HaveFurniture)
//        dataSet.addIncentive(Incentive.newIncentive(firstBuilder, new HashSet<>(Arrays.asList(firstBuilderPaid)), haveFurniture));
//        // Incentive(SecondBuilder, {SecondBuilderPaid}, HaveFurniture)
//        dataSet.addIncentive(Incentive.newIncentive(secondBuilder, new HashSet<>(Arrays.asList(secondBuilderPaid)), haveFurniture));
//        // Capability({HaveTools}, ToolsProvided)
//        dataSet.addCapability(new Capability(new HashSet<>(Arrays.asList(haveTools)), toolsProvided));
//        // Capability({HaveMaterials}, MaterialsProvided)
//        dataSet.addCapability(new Capability(new HashSet<>(Arrays.asList(haveMaterials)), materialsProvided));
//        // Capability({}, ToolsPaidCash)
//        dataSet.addCapability(new Capability(new HashSet<Proposition>(), toolsPaidCash));
//        // Capability({}, ToolsPaidCard)
//        dataSet.addCapability(new Capability(new HashSet<Proposition>(), toolsPaidCard));
//        // Capability({}, MaterialsPaidCash)
//        dataSet.addCapability(new Capability(new HashSet<Proposition>(), materialsPaidCash));
//        // Capability({}, MaterialsPaidCard)
//        dataSet.addCapability(new Capability(new HashSet<Proposition>(), materialsPaidCard));
//        // Capability({}, FurniturePaid)
//        dataSet.addCapability(new Capability(new HashSet<Proposition>(), furniturePaid));
//        // Capability({}, FirstBuilderPaid)
//        dataSet.addCapability(new Capability(new HashSet<Proposition>(), firstBuilderPaid));
//        // Capability({}, SecondBuilderPaid)
//        dataSet.addCapability(new Capability(new HashSet<Proposition>(), secondBuilderPaid));
//        return dataSet;
//    }
//    
}
