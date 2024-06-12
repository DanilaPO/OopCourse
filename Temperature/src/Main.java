import controller.TemperatureController;
import model.*;
import view.DesktopView;
import view.View;

public class Main {
    public static void main(String[] args) {
        Scale[] scales = {new CelsiusScale(), new FahrenheitScale(), new KelvinScale()};

        Model model = new TemperatureConverter(scales);
        View view = new DesktopView();
        new TemperatureController(model, view);

        view.start();
    }
}