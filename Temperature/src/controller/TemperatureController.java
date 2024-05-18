package controller;

import model.Model;
import view.View;

import java.io.IOException;

public class TemperatureController implements Controller {
    private final Model converter;
    private final View view;

    public TemperatureController(Model converter, View view) {
        this.view = view;
        this.converter = converter;

        view.setController(this);
    }

    @Override
    public void setTemperatureForConversion(double enteringTemperatureData, String enteringScaleName, String outputScaleName) throws IOException {
        view.showConversionResult(converter.getTemperature(enteringTemperatureData, enteringScaleName, outputScaleName));
    }

    @Override
    public String[] getTemperatureScales() {
        return converter.getTemperatureScales();
    }
}
