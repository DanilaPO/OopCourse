package model;

public class TemperatureConverter implements Model {
    private double temperature;
    private final String[] array = {"Celsius", "Fahrenheit", "Kelvin"};

    @Override
    public void setTemperatureForConvertToCelsius(double temperature, String temperatureName) {
        if (temperatureName.equals(array[0])) {
            this.temperature = temperature;
        }

        if (temperatureName.equals(array[1])) {
            this.temperature = (temperature - 32) * 5 / 9;
        }

        if (temperatureName.equals(array[2])) {
            this.temperature = temperature - 273.15;
        }
    }

    @Override
    public double getConvertedTemperature(String temperatureName) {
        if (temperatureName.equals(array[0])) {
            return temperature;
        }

        if (temperatureName.equals(array[1])) {
            return temperature * 9 / 5 + 32;
        }

        if (temperatureName.equals(array[2])) {
            return temperature + 273.15;
        }

        return temperature;
    }

    @Override
    public String[] getTemperatureScale() {
        return array;
    }
}