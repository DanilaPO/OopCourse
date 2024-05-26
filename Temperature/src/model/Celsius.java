package model;

public class Celsius implements Scale {
    private final String TemperatureName = "Цельсия";

    @Override
    public double getCelsius(double temperature) {
        return temperature;
    }

    @Override
    public double getTemperatureFormCelsius(double celsiusTemperature) {
        return celsiusTemperature;
    }

    @Override
    public String getTemperatureName() {
        return TemperatureName;
    }
}
