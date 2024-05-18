package model;

import java.io.IOException;

public interface Model {
    double getTemperature(double enteringTemperatureData, String enteringScaleName, String outputScaleName) throws IOException;

    String[] getTemperatureScales();
}