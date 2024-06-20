package controller;

import model.Model;
import view.View;

public class TemperatureController implements Controller {
    private final Model model;
    private final View view;

    public TemperatureController(Model model, View view) {
        this.view = view;
        this.model = model;

        view.setController(this);
    }

    @Override
    public void setTemperatureForConversion(double temperature, String inputScaleName, String outputScaleName) {
        view.showConversionResult(model.getTemperature(temperature, model.getScaleByName(inputScaleName), model.getScaleByName(outputScaleName)));
    }

    @Override
    public String[] getScalesNames() {
        return model.getScalesNames();
    }
}