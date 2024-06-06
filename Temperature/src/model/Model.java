package model;

import java.io.IOException;

public interface Model {
    double getTemperature(double temperature, Scale inputScale, Scale outputScale) throws IOException;

    Scale getScaleByName(String ScaleName);

    String[] getScalesNames();
}