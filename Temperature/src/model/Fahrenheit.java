package model;

public class Fahrenheit implements Scale {
    private final String TemperatureName = "Фаренгейта";

    @Override
    public double getCelsius(double temperature) {
        return (temperature - 32.0) * 5.0 / 9.0;
    }

    @Override
    public double getTemperatureFormCelsius(double celsiusTemperature) {
        return celsiusTemperature * 9.0 / 5.0 + 32.0;
    }

    @Override
    public String getTemperatureName() {
        return TemperatureName;
    }
}
