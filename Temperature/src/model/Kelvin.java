package model;

public class Kelvin implements Scale {
    private final String TemperatureName = "Кельвин";

    @Override
    public double getCelsius(double temperature) {
        return temperature - 273.15;
    }

    @Override
    public double getTemperatureFormCelsius(double celsiusTemperature) {
        return celsiusTemperature + 273.15;
    }

    @Override
    public String getTemperatureName() {
        return TemperatureName;
    }
}
