package model;

import java.io.IOException;

public interface Model {
    double getTemperature(double temperature, String inputScaleName, String outputScaleName) throws IOException;

    String[] getTemperatureScales();
}