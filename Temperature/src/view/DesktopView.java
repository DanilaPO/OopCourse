package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DesktopView implements View {
    private Controller controller;
    private double temperature;
    JTextField textFieldOutput;

    @Override
    public void start() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Temperature converter");
                frame.setSize(600, 200);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);

                JPanel panelInput = new JPanel();
                frame.add(panelInput, BorderLayout.PAGE_START);

                JLabel labelInput = new JLabel("Введите конвертируемую величину темературы");
                panelInput.add(labelInput);

                JTextField textFieldInput = new JTextField(20);
                panelInput.add(textFieldInput);

                JPanel panelOutput = new JPanel();
                frame.add(panelOutput, BorderLayout.PAGE_END);

                JLabel labelOutput = new JLabel("Результат конвертации температуры");
                panelOutput.add(labelOutput);

                textFieldOutput = new JTextField(20);
                panelOutput.add(textFieldOutput);

                JPanel panelButtons = new JPanel();
                panelButtons.setLayout(new GridLayout());
                frame.add(panelButtons, BorderLayout.CENTER);

                JButton buttonCelsiusToFahrenheit = new JButton("Цельсии в Фаренгейты");
                panelButtons.add(buttonCelsiusToFahrenheit);

                buttonCelsiusToFahrenheit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        try {
                            temperature = Double.parseDouble(textFieldInput.getText());
                            controller.convertToFahrenheit(temperature);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(frame, "Введите температуру");
                        }
                    }
                });

                JButton buttonFahrenheitToCelsius = new JButton("Фаренгейты в Цельсии");
                panelButtons.add(buttonFahrenheitToCelsius);

                buttonFahrenheitToCelsius.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        try {
                            temperature = Double.parseDouble(textFieldInput.getText());
                            controller.convertToCelsius(temperature);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(frame, "Введите температуру");
                        }
                    }
                });
            }
        });
    }

    @Override
    public void show(double temperature) {
        textFieldOutput.setText(String.valueOf(temperature));
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }
}