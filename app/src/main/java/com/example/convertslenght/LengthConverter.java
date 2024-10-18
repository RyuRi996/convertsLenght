package com.example.convertslenght;

import java.util.HashMap;
import java.util.Map;

public class LengthConverter {
    private static final Map<String, Double> standardMeter = new HashMap<>();

    static {
        standardMeter.put("Millimeter (mm)", 0.001);
        standardMeter.put("Centimeter (cm)", 0.01);
        standardMeter.put("Decimeter (dm)", 0.1);
        standardMeter.put("Meter (m)", 1.0);
        standardMeter.put("Kilometer (km)", 1000.0);
        standardMeter.put("Inch (in)", 0.0254);
        standardMeter.put("Foot (ft)", 0.3048);
        standardMeter.put("Yard (yd)", 0.9144);
        standardMeter.put("Mile (mi)", 1609.344);
    }

    public static double convertLength(double value, String fromUnit, String toUnit){
        double valueInMeters = value * standardMeter.get(fromUnit);
        return valueInMeters / standardMeter.get(toUnit);
    }
}
