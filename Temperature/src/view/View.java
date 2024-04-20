package view;

import controller.Controller;

public interface View {
    void start();

    void show(double temperature);

    void setController(Controller controller);
}