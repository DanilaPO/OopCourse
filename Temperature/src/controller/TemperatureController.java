package controller;

import model.Model;
import view.View;

public class TemperatureController implements Controller {
    private final Model converter;
    private final View view;

    public TemperatureController(Model converter, View view) {
        this.view = view;
        this.converter = converter;

        view.setController(this);
    }

    @Override
    public void putTemperatureForConvertToCelsius(double temperature, String temperatureName) {
        converter.setTemperatureForConvertToCelsius(temperature, temperatureName);
    }

    @Override
    public void convertTemperature(String temperatureName) {
        view.showConvertationResult(converter.getConvertedTemperature(temperatureName));
    }

    @Override
    public String[] getTemperatureScale() {
        return converter.getTemperatureScale();
    }
}
