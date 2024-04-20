package controller;

import model.Model;
import view.View;

public class Controller {
    private Model converter;
    private View view;

    public Controller(Model converter, View view) {
        this.view = view;
        this.converter = converter;
    }

    public void convertToFahrenheit(double celsiusTemperature) {
        view.show(converter.getFahrenheit(celsiusTemperature));
    }

    public void convertToCelsius(double fahrenheitTemperature) {
        view.show(converter.getCelsius(fahrenheitTemperature));
    }
}