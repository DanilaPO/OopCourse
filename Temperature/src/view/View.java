package view;

import controller.Controller;

public interface View {
    void start();

    void showConvertationResult(double temperature);

    void setController(Controller controller);
}