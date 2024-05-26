import controller.TemperatureController;
import model.*;
import view.DesktopView;
import view.View;

public class Main {
    public static void main(String[] args) {
        Model model = new TemperatureConverter();
        View view = new DesktopView();
        new TemperatureController(model, view);

        ((TemperatureConverter) model).addScale(new Celsius());
        ((TemperatureConverter) model).addScale(new Fahrenheit());
        ((TemperatureConverter) model).addScale(new Kelvin());

        view.start();
    }
}