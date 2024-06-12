package controller;

import java.io.IOException;

public interface Controller {
    void setTemperatureForConversion(double temperature, String inputScaleName, String outputScaleName) throws IOException;

    String[] getScalesNames();
}