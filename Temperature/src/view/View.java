package view;

import controller.Controller;

public interface View {
    void start();

    void showConversionResult(double temperature);

    void setController(Controller controller);
}