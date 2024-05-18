package controller;

import java.io.IOException;

public interface Controller {
    void setTemperatureForConversion(double enteringTemperatureData, String enteringTemperatureName, String outputTemperatureName) throws IOException;

    String[] getTemperatureScales();
}
