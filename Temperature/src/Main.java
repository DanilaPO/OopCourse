import controller.Controller;
import model.Model;
import model.TemperaturesConverter;
import view.DesktopView;
import view.View;

public class Main {
    public static void main(String[] args) {
        Model model = new TemperaturesConverter();
        View view = new DesktopView();

        Controller controller = new Controller(model, view);
        view.setController(controller);

        view.start();
    }
}