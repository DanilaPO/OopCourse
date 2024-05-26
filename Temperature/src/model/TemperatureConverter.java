package model;

import java.util.ArrayList;
import java.util.List;

public class TemperatureConverter implements Model {
    private final List<Scale> scales = new ArrayList<>();

    @Override
    public double getTemperature(double temperature, String inputScaleName, String outputScaleName) {
        double celsiusTemperature = 0;
        Scale outputScale = null;

        for (Scale scale : scales) {
            if (inputScaleName.equals(scale.getTemperatureName())) {
                celsiusTemperature = scale.getCelsius(temperature);
            }

            if (outputScaleName.equals(scale.getTemperatureName())) {
                outputScale = scale;
            }
        }

        return outputScale.getTemperatureFormCelsius(celsiusTemperature);
    }

    public void addScale(Scale scale) {
        scales.add(scale);
    }

    @Override
    public String[] getTemperatureScales() {
        String[] scales = new String[this.scales.size()];

        int i = 0;

        for(Scale scale : this.scales) {
            scales[i] = scale.getTemperatureName();
            ++i;
        }

        return scales;
    }
}