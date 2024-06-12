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
        try {
            view.showConversionResult(model.getTemperature(temperature, model.getScaleByName(inputScaleName), model.getScaleByName(outputScaleName)));
        } catch (Exception ignore) {
        }
    }

    @Override
    public String[] getScalesNames() {
        return model.getScalesNames();
    }
}