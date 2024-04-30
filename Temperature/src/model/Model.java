package model;

public interface Model {
    public double getCelsiusFromFahrenheit(double temperature);

    public double getCelsiusFromKelvin(double temperature);

    public double getFahrenheitFormCelsius(double temperature);

    public double getKelvinFormCelsius(double temperature);
}