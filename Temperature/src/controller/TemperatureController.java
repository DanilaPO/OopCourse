package controller;

import model.Model;
import view.View;

import java.io.IOException;

public class TemperatureController implements Controller {
    private final Model model;
    private final View view;

    public TemperatureController(Model converter, View view) {
        this.view = view;
        this.model = converter;

        view.setController(this);
    }

    @Override
    public void setTemperatureForConversion(double temperature, String inputScaleName, String outputScaleName) {
        try {
            view.showConversionResult(model.getTemperature(temperature, inputScaleName, outputScaleName));
        } catch (IOException e) {
            throw new RuntimeException("Передано значение Null");
        }
    }

    @Override
    public String[] getTemperatureScales() {
        return model.getTemperatureScales();
    }
}