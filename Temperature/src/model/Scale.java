package model;

public interface Scale {
    double convertToCelsius(double temperature);

    double convertFormCelsius(double celsiusTemperature);

    String getName();
}