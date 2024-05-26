package model;

public interface Scale {
    double getCelsius(double temperature);
    double getTemperatureFormCelsius(double celsiusTemperature);
    String getTemperatureName();
}
