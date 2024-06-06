import controller.TemperatureController;
import model.*;
import view.DesktopView;
import view.View;

public class Main {
    public static void main(String[] args) {
        Model model = new TemperatureConverter(new CelsiusScale(), new FahrenheitScale(), new KelvinScale());
        View view = new DesktopView();
        new TemperatureController(model, view);

        view.start();
    }
}