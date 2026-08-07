import java.util.ArrayList;
import java.util.List;

// Structure to hold post-implementation monitoring data
class EvaluationMetrics {
    double tempReductionCelsius;
    int socialEquityScore; // Scale 1-10 (10 being highly equitable)
    boolean costWithinBudget;

    public EvaluationMetrics(double tempReductionCelsius, int socialEquityScore, boolean costWithinBudget) {
        this.tempReductionCelsius = tempReductionCelsius;
        this.socialEquityScore = socialEquityScore;
        this.costWithinBudget = costWithinBudget;
    }
}

// Structure to represent an Urban Zone
class UrbanZone {
    String name;
    int revisionCount = 0;

    public UrbanZone(String name) {
        this.name = name;
    }
}

public class UrbanCoolingLifecycle {

    // ==========================================
    // STEP 4: PROJECT IMPLEMENTATION
    // ==========================================
    public static void implementProject(UrbanZone zone) {
        System.out.println("\n--- STEP 4: PROJECT IMPLEMENTATION ---");
        System.out.println("Rolling out Pilot Projects & Scaling for zone: " + zone.name);
        System.out.println("Status: Active mitigation infrastructure deployed (Revision " + zone.revisionCount + ").");
    }

    // ==========================================
    // STEP 5: MONITOR & EVALUATE
    // ==========================================
    public static EvaluationMetrics monitorAndEvaluate(UrbanZone zone) {
        System.out.println("--- STEP 5: MONITOR & EVALUATE ---");
        System.out.println("Gathering Data Input: 'Temperature Sensors', 'Public Feedback', 'Cost-Benefit Analysis'...");
        
        // Simulating the evaluation data. 
        // In a real app, this would read from IoT sensors and survey databases.
        // As revision count goes up, the metrics improve to simulate a better strategy.
        double tempDrop = 1.0 + (zone.revisionCount * 0.8); 
        int equityScore = 5 + (zone.revisionCount * 2);
        boolean inBudget = true;

        System.out.printf("Results -> Temp Reduction: %.2fC | Equity Score: %d/10 | In Budget: %b%n", 
                          tempDrop, equityScore, inBudget);
        
        return new EvaluationMetrics(tempDrop, equityScore, inBudget);
    }

    // ==========================================
    // STEP 6: METRICS ACHIEVED? (Decision Node)
    // ==========================================
    public static boolean checkMetricsAchieved(EvaluationMetrics metrics) {
        System.out.println("--- STEP 6: METRICS ACHIEVED? ---");
        
        // Define our success thresholds based on the flowchart criteria
        double targetTempReduction = 2.0; 
        int targetEquityScore = 7;

        boolean coolingMet = metrics.tempReductionCelsius >= targetTempReduction;
        boolean equityMet = metrics.socialEquityScore >= targetEquityScore;
        boolean costMet = metrics.costWithinBudget;

        if (coolingMet && equityMet && costMet) {
            System.out.println("-> DECISION (YES): All Cooling, Social Equity, and Cost metrics achieved!");
            return true;
        } else {
            System.out.println("-> DECISION (NO): Metrics fall short. Triggering 'REVISE & OPTIMIZE PLAN'.");
            return false;
        }
    }

    // ==========================================
    // STEP 7: CONTINUOUS IMPROVEMENT & END
    // ==========================================
    public static void continuousImprovementAndEnd(UrbanZone zone) {
        System.out.println("\n--- STEP 7: CONTINUOUS IMPROVEMENT & ADAPTATION ---");
        System.out.println("Transitioning " + zone.name + " to long-term maintenance and climate adaptation protocols.");
        
        System.out.println("\n================================================");
        System.out.println("   END: SUSTAINABLE COOL CITY ACHIEVED");
        System.out.println("================================================");
    }

    // ==========================================
    // EXECUTION WORKFLOW (The Simulation Loop)
    // ==========================================
    public static void main(String[] args) {
        // Assume Step 1-3 have finished and passed us this zone to implement
        UrbanZone targetZone = new UrbanZone("Downtown Commercial Core");
        
        boolean isCityCoolAndSustainable = false;

        // This while-loop represents the feedback arrow from Step 6 (NO) back to Step 3
        while (!isCityCoolAndSustainable) {
            
            // Execute Step 4
            implementProject(targetZone);
            
            // Execute Step 5
            EvaluationMetrics currentMetrics = monitorAndEvaluate(targetZone);
            
            // Execute Step 6
            isCityCoolAndSustainable = checkMetricsAchieved(currentMetrics);
            
            if (!isCityCoolAndSustainable) {
                // The "NO" branch routes back to Step 3 (Revise Plan)
                System.out.println("\n[System] Returning to Step 3: DEVELOP COMPREHENSIVE STRATEGY...");
                targetZone.revisionCount++; // Increment to simulate an improved plan next time
            }
        }
        
        // The "YES" branch routes to Step 7
        continuousImprovementAndEnd(targetZone);
    }
}