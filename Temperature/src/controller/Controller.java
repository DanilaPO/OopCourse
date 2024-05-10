package controller;

public interface Controller {
    void putTemperatureForConvertToCelsius(double temperature, String temperatureName);

    void convertTemperature(String temperatureName);

    String[] getTemperatureScale();
}
