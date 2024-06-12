package model;

public class KelvinScale implements Scale {
    @Override
    public double convertToCelsius(double temperature) {
        return temperature - 273.15;
    }

    @Override
    public double convertFormCelsius(double celsiusTemperature) {
        return celsiusTemperature + 273.15;
    }

    @Override
    public String getName() {
        return "Кельвин";
    }
}