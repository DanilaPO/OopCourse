package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TemperatureConverter implements Model {
    private final List<Scale> scales = new ArrayList<>();

    public TemperatureConverter(Scale... scale) {
        Collections.addAll(scales, scale);
    }

    @Override
    public double getTemperature(double temperature, Scale inputScale, Scale outputScale) {
        double celsiusTemperature = inputScale.convertToCelsius(temperature);

        return outputScale.convertFormCelsius(celsiusTemperature);
    }

    @Override
    public Scale getScaleByName(String ScaleName) {
        for (Scale scale : scales) {
            if (ScaleName.equals(scale.getName())) {
                return scale;
            }
        }

        return null;
    }

    @Override
    public String[] getScalesNames() {
        String[] scales = new String[this.scales.size()];

        int i = 0;

        for (Scale scale : this.scales) {
            scales[i] = scale.getName();
            ++i;
        }

        return scales;
    }
}