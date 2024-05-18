import controller.TemperatureController;
import model.Model;
import model.TemperatureConverter;
import view.DesktopView;
import view.View;

public class Main {
    public static void main(String[] args) {
        Model model = new TemperatureConverter();
        View view = new DesktopView();
        new TemperatureController(model, view);

        view.start();
    }
}