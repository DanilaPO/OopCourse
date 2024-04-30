package model;

public class TemperatureConverter implements Model {
    @Override
    public double getCelsiusFromFahrenheit(double temperature) {
        return (temperature - 32) * 5 / 9;
    }

    @Override
    public double getCelsiusFromKelvin(double temperature) {
        return temperature - 273.15;
    }

    @Override
    public double getFahrenheitFormCelsius(double temperature) {
        return temperature * 9 / 5 + 32;
    }

    @Override
    public double getKelvinFormCelsius(double temperature) {
        return temperature + 273.15;
    }
}