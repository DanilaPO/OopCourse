package model;

public interface Model {
    void setTemperatureForConvertToCelsius(double temperature, String temperatureName);

    double getConvertedTemperature(String temperatureName);

    String[] getTemperatureScale();
}