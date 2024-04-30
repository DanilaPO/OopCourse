package controller;

import model.Model;
import view.View;

public class TemperatureController implements Controller {
    private Model converter;
    private View view;

    private double celsiusTemperature;
    private double convertedTemperature;

    public TemperatureController(Model converter, View view) {
        this.view = view;
        this.converter = converter;

        view.setController(this);
    }

    @Override
    public void putCelsiusTemperature(double temperature) {
        celsiusTemperature = temperature;
    }

    @Override
    public void putFahrenheitTemperature(double temperature) {
        celsiusTemperature = converter.getCelsiusFromFahrenheit(temperature);
    }

    @Override
    public void putKelvinTemperature(double temperature) {
        celsiusTemperature = converter.getCelsiusFromKelvin(temperature);
    }

    @Override
    public void convertToCelsiusTemperature() {
        convertedTemperature = celsiusTemperature;
        view.showConvertationResult(convertedTemperature);
    }

    @Override
    public void convertToFahrenheitTemperature() {
        convertedTemperature = converter.getFahrenheitFormCelsius(celsiusTemperature);
        view.showConvertationResult(convertedTemperature);
    }

    @Override
    public void convertToKelvinTemperature() {
        convertedTemperature = converter.getKelvinFormCelsius(celsiusTemperature);
        view.showConvertationResult(convertedTemperature);
    }
}
