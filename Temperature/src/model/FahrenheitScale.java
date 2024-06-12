package model;

public class FahrenheitScale implements Scale {
    @Override
    public double convertToCelsius(double temperature) {
        return (temperature - 32.0) * 5.0 / 9.0;
    }

    @Override
    public double convertFormCelsius(double celsiusTemperature) {
        return celsiusTemperature * 9.0 / 5.0 + 32.0;
    }

    @Override
    public String getName() {
        return "Фаренгейта";
    }
}