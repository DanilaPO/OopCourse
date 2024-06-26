package model;

public interface Model {
    double getTemperature(double temperature, Scale inputScale, Scale outputScale) ;

    Scale getScaleByName(String ScaleName);

    String[] getScalesNames();
}