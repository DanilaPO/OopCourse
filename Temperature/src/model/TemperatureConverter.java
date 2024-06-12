package model;

import java.util.ArrayList;
import java.util.List;

public class TemperatureConverter implements Model {
    private final List<Scale> scales = new ArrayList<>();

    public TemperatureConverter(Scale[] scales) {
        this.scales.addAll(List.of(scales));
    }

    @Override
    public double getTemperature(double temperature, Scale inputScale, Scale outputScale) {
        double celsiusTemperature = inputScale.convertToCelsius(temperature);

        return outputScale.convertFormCelsius(celsiusTemperature);
    }

    @Override
    public Scale getScaleByName(String scaleName) {
        for (Scale scale : scales) {
            if (scaleName.equals(scale.getName())) {
                return scale;
            }
        }

        return null;
    }

    @Override
    public String[] getScalesNames() {
        String[] scalesNames = new String[scales.size()];

        int i = 0;

        for (Scale scale : scales) {
            scalesNames[i] = scale.getName();
            ++i;
        }

        return scalesNames;
    }
}