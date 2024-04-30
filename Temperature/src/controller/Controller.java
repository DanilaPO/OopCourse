package controller;

public interface Controller {

    void putCelsiusTemperature(double temperature);

    void putFahrenheitTemperature(double temperature);

    void putKelvinTemperature(double temperature);

    void convertToCelsiusTemperature();

    void convertToFahrenheitTemperature();

    void convertToKelvinTemperature();
}
