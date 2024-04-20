package model;

public class TemperaturesConverter implements Model {
    @Override
    public double getCelsius(double temperature) {
        return (temperature - 32) * 5 / 9;
    }

    @Override
    public double getFahrenheit(double temperature) {
        return temperature * 9 / 5 + 32;
    }
}