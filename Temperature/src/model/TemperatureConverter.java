package model;

import java.io.IOException;

public class TemperatureConverter implements Model {
    private final String[] scales = {"Цельсия", "Фаренгейты", "Кельвины"};

    @Override
    public double getTemperature(double enteringTemperatureData, String enteringScaleName, String outputScaleName) throws IOException {
        double temperature;

        try {
            if (enteringScaleName.equals(scales[0])) {
                temperature = enteringTemperatureData;
            } else if (enteringScaleName.equals(scales[1])) {
                temperature = (enteringTemperatureData - 32) * 5 / 9;
            } else if (enteringScaleName.equals(scales[2])) {
                temperature = enteringTemperatureData - 273.15;
            } else {
                throw new IOException();
            }

            if (outputScaleName.equals(scales[0])) {
                return temperature;
            } else if (outputScaleName.equals(scales[1])) {
                return temperature * 9 / 5 + 32;
            } else if (outputScaleName.equals(scales[2])) {
                return temperature + 273.15;
            } else {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException | IOException e) {
            throw e;
        }
    }

    @Override
    public String[] getTemperatureScales() {
        return scales;
    }
}