package model;

public class CelsiusScale implements Scale {
    @Override
    public double convertToCelsius(double temperature) {
        return temperature;
    }

    @Override
    public double convertFormCelsius(double celsiusTemperature) {
        return celsiusTemperature;
    }

    @Override
    public String getName() {
        return "Цельсия";
    }
}
