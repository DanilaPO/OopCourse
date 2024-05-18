package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DesktopView implements View {
    private Controller controller;
    private double temperature;
    private JTextField textFieldOutput;

    private final List<JRadioButton> inputDataButtonsList = new ArrayList<>();
    private final List<JRadioButton> outputDataButtonsList = new ArrayList<>();

    private JRadioButton selectedInputButton;
    private JRadioButton selectedOutputButton;

    @Override
    public void start() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Конвертер температур");
            frame.setSize(1200, 200);
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            GridLayout panelsGridLayout = new GridLayout(1, 2);
            panelsGridLayout.setHgap(-582);

            JPanel panelInput = new JPanel();
            panelInput.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            panelInput.setLayout(panelsGridLayout);
            frame.add(panelInput, BorderLayout.PAGE_START);

            JLabel labelInput = new JLabel("Введите конвертируемую величину темературы: ");
            panelInput.add(labelInput);

            JTextField inputTextField = new JTextField(20);
            panelInput.add(inputTextField);

            JPanel panelOutput = new JPanel();
            panelOutput.setLayout(panelsGridLayout);
            panelOutput.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            frame.add(panelOutput, BorderLayout.PAGE_END);

            JLabel labelOutput = new JLabel("Результат конвертации температуры: ");
            panelOutput.add(labelOutput);

            textFieldOutput = new JTextField(20);
            textFieldOutput.setEnabled(false);
            textFieldOutput.setDisabledTextColor(Color.BLACK);
            panelOutput.add(textFieldOutput);

            JPanel buttonsPanel = new JPanel();
            GridLayout gridLayout = new GridLayout(2, controller.getTemperatureScales().length + 1);
            buttonsPanel.setLayout(gridLayout);
            buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            frame.add(buttonsPanel, BorderLayout.CENTER);

            for (int i = 0; i < controller.getTemperatureScales().length; i++) {
                JRadioButton inputDataChoiceTemperatureButton = new JRadioButton(controller.getTemperatureScales()[i]);
                inputDataButtonsList.add(inputDataChoiceTemperatureButton);

                inputDataChoiceTemperatureButton.addActionListener(actionEvent -> {
                    try {
                        for (JRadioButton button : inputDataButtonsList) {
                            button.setBackground(null);
                            button.setSelected(false);
                            selectedInputButton = null;
                        }

                        inputDataChoiceTemperatureButton.setBackground(Color.red);
                        inputDataChoiceTemperatureButton.setSelected(true);
                        selectedInputButton = inputDataChoiceTemperatureButton;

                        temperature = Double.parseDouble(inputTextField.getText());

                        if (selectedOutputButton != null && inputTextField.getText() != null) {
                            controller.setTemperatureForConversion(temperature, inputDataChoiceTemperatureButton.getText(), selectedOutputButton.getText());
                            selectedOutputButton.doClick();
                        }
                    } catch (NumberFormatException | IOException e) {
                        for (JRadioButton button : inputDataButtonsList) {
                            button.setBackground(null);
                            button.setSelected(false);
                        }

                        for (JRadioButton button : outputDataButtonsList) {
                            button.setBackground(null);
                            button.setSelected(false);
                        }

                        JOptionPane.showMessageDialog(frame, "Требуется ввод корректных данных", "Ошибка ввода данных!", JOptionPane.ERROR_MESSAGE);
                    }
                });

                JRadioButton outputDataChoiceTemperatureButton = new JRadioButton(controller.getTemperatureScales()[i]);
                outputDataButtonsList.add(outputDataChoiceTemperatureButton);

                outputDataChoiceTemperatureButton.addActionListener(actionEvent -> {
                    try {
                        for (JRadioButton button : outputDataButtonsList) {
                            button.setBackground(null);
                            button.setSelected(false);
                            selectedOutputButton = null;
                        }

                        outputDataChoiceTemperatureButton.setBackground(Color.yellow);
                        outputDataChoiceTemperatureButton.setSelected(true);
                        selectedOutputButton = outputDataChoiceTemperatureButton;

                        temperature = Double.parseDouble(inputTextField.getText());

                        if (selectedInputButton != null) {
                            temperature = Double.parseDouble(inputTextField.getText());
                            controller.setTemperatureForConversion(temperature, selectedInputButton.getText(), inputDataChoiceTemperatureButton.getText());
                        }
                    } catch (NumberFormatException | IOException e) {
                        for (JRadioButton button : inputDataButtonsList) {
                            button.setBackground(null);
                            button.setSelected(false);
                        }

                        for (JRadioButton button : outputDataButtonsList) {
                            button.setBackground(null);
                            button.setSelected(false);
                        }

                        JOptionPane.showMessageDialog(frame, "Требуется ввод корректных данных", "Ошибка ввода данных!", JOptionPane.ERROR_MESSAGE);
                    }
                });
            }

            JLabel inputDataTemperatureChoiceLabel = new JLabel("Начальный вид температуры: ");
            buttonsPanel.add(inputDataTemperatureChoiceLabel);

            for (JRadioButton button : inputDataButtonsList) {
                buttonsPanel.add(button);
            }

            JLabel outputDataTemperatureChoiceLabel = new JLabel("Конвертировать температуру в: ");
            buttonsPanel.add(outputDataTemperatureChoiceLabel);

            for (JRadioButton button : outputDataButtonsList) {
                buttonsPanel.add(button);
            }
        });
    }

    @Override
    public void showConversionResult(double temperature) {
        textFieldOutput.setText(String.valueOf(temperature));
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }
}