#include <iostream>
#include <vector>
#include <string>
#include <numeric>
#include <iomanip>

using namespace std;

// Structure to represent the combined Demographic and Thermal Data
struct UrbanZone {
    string zoneName;
    vector<float> thermalPixels; // Simulates raw temperature data from satellite imagery
    float meanTempC = 0.0f;
    float uhiIntensity = 0.0f;
};

// ==========================================
// STEP 1: DIAGNOSE & ASSESS
// Thermal & Vulnerability Mapping
// ==========================================

void diagnoseAndAssess(vector<UrbanZone>& cityZones) {
    cout << "--- STEP 1: DIAGNOSING & ASSESSING ---" << endl;
    cout << "Loading 'Urban Data', 'Thermal Imagery', and 'Demographics'..." << endl;
    
    // Calculate the mean temperature for each zone based on its simulated thermal pixels
    for (auto& zone : cityZones) {
        if (!zone.thermalPixels.empty()) {
            float sum = accumulate(zone.thermalPixels.begin(), zone.thermalPixels.end(), 0.0f);
            zone.meanTempC = sum / zone.thermalPixels.size();
        }
    }
    cout << "Data loaded and processed successfully for " << cityZones.size() << " urban zones." << endl;
}

// ==========================================
// STEP 2: HEAT ISLAND IDENTIFIED?
// Decision Logic based on Thresholds
// ==========================================

bool identifyHeatIslands(vector<UrbanZone>& cityZones, float baselineRuralTemp, float uhiThreshold) {
    cout << "\n--- STEP 2: HEAT ISLAND IDENTIFICATION ---" << endl;
    
    bool heatIslandsFound = false;

    for (auto& zone : cityZones) {
        // Calculate UHI Intensity (Urban Temp - Rural Temp)
        zone.uhiIntensity = zone.meanTempC - baselineRuralTemp;
        
        cout << "Checking " << zone.zoneName << " | Mean Temp: " 
             << fixed << setprecision(2) << zone.meanTempC << " C | UHI Intensity: " 
             << zone.uhiIntensity << " C" << endl;

        if (zone.uhiIntensity >= uhiThreshold) {
            cout << "  -> [ALERT] Heat Island IDENTIFIED! (Exceeds " << uhiThreshold << " C threshold)" << endl;
            heatIslandsFound = true;
        } else {
            cout << "  -> [SAFE] No critical heat island." << endl;
        }
    }

    // Flowchart Decision Routing
    if (heatIslandsFound) {
        cout << "\n-> DECISION (YES): Heat Islands exist. Proceeding to Step 3: DEVELOP COMPREHENSIVE STRATEGY." << endl;
        return true;
    } else {
        cout << "\n-> DECISION (NO): Heat levels normal. Proceeding to CONTINUE MONITORING." << endl;
        return false;
    }
}

// ==========================================
// EXECUTION WORKFLOW
// ==========================================
int main() {
    // 1. Mock Data Input (Simulating the Parallelogram in the flowchart)
    // In reality, this data would be parsed from GIS files using GDAL/OGR.
    vector<UrbanZone> myCity = {
        {"Downtown Core", {35.2, 36.1, 35.8, 37.0}},      // High concrete, very hot
        {"North Suburbs", {29.5, 30.1, 29.8, 29.2}},      // More trees, cooler
        {"Industrial Park", {38.1, 37.5, 39.0, 38.8}}     // Factories/asphalt, extremely hot
    };

    float RURAL_BASELINE_TEMP = 28.5f; // Baseline temp outside the city
    float UHI_THRESHOLD = 3.0f;        // 3 degrees hotter triggers mitigation

    // Execute Step 1
    diagnoseAndAssess(myCity);

    // Execute Step 2
    bool requiresMitigation = identifyHeatIslands(myCity, RURAL_BASELINE_TEMP, UHI_THRESHOLD);

    // Flowchart Routing Execution
    if (requiresMitigation) {
        // Code would move to Step 3 (Passive/Active Cooling Strategies)
        cout << "\n[System] Initializing Mitigation Streams (Green Infrastructure, Cool Roofs, etc.)..." << endl;
    } else {
        // Code loops back to the start
        cout << "\n[System] Returning to loop: Continue Monitoring..." << endl;
    }

    return 0;
}