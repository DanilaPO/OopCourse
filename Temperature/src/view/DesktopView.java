package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DesktopView implements View {
    private Controller controller;
    private double temperature;
    private JTextField textFieldOutput;

    private final List<JButton> inputDataButtonsList = new ArrayList<>();
    private final List<JButton> outputDataButtonsList = new ArrayList<>();

    private JButton selectedInputButton;
    private JButton selectedOutputButton;

    @Override
    public void start() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
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
                GridLayout gridLayout = new GridLayout(2, controller.getTemperatureScale().length + 1);
                buttonsPanel.setLayout(gridLayout);
                buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                frame.add(buttonsPanel, BorderLayout.CENTER);

                for (int i = 0; i < controller.getTemperatureScale().length; i++) {
                    JButton inputDataChoiceTemperatureButton = new JButton(controller.getTemperatureScale()[i]);
                    inputDataButtonsList.add(inputDataChoiceTemperatureButton);

                    inputDataChoiceTemperatureButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            try {
                                for (JButton button : inputDataButtonsList) {
                                    button.setBackground(null);
                                    button.setSelected(false);
                                    selectedInputButton = null;
                                }

                                inputDataChoiceTemperatureButton.setBackground(Color.red);
                                inputDataChoiceTemperatureButton.setSelected(true);
                                selectedInputButton = inputDataChoiceTemperatureButton;

                                temperature = Double.parseDouble(inputTextField.getText());
                                controller.putTemperatureForConvertToCelsius(temperature, inputDataChoiceTemperatureButton.getText());

                                if (selectedOutputButton != null && inputTextField.getText() != null) {
                                    selectedOutputButton.doClick();
                                }
                            } catch (NumberFormatException e) {
                                for (JButton button : inputDataButtonsList) {
                                    button.setBackground(null);
                                    button.setSelected(false);
                                }

                                for (JButton button : outputDataButtonsList) {
                                    button.setBackground(null);
                                    button.setSelected(false);
                                }

                                inputTextField.setText(null);

                                JOptionPane.showMessageDialog(frame, "Требуется ввод числовых данных", "Ошибка ввода данных!", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                    });

                    JButton outputDataChoiceTemperatureButton = new JButton(controller.getTemperatureScale()[i]);
                    outputDataButtonsList.add(outputDataChoiceTemperatureButton);

                    outputDataChoiceTemperatureButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            try {
                                for (JButton button : outputDataButtonsList) {
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
                                    controller.putTemperatureForConvertToCelsius(temperature, inputDataChoiceTemperatureButton.getText());
                                    controller.convertTemperature(outputDataChoiceTemperatureButton.getText());
                                }
                            } catch (NumberFormatException e) {
                                for (JButton button : inputDataButtonsList) {
                                    button.setBackground(null);
                                    button.setSelected(false);
                                }

                                for (JButton button : outputDataButtonsList) {
                                    button.setBackground(null);
                                    button.setSelected(false);
                                }

                                inputTextField.setText(null);

                                JOptionPane.showMessageDialog(frame, "Требуется ввод числовых данных", "Ошибка ввода данных!", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                    });
                }

                JLabel inputDataTemperatureChoiceLabel = new JLabel("Начальный вид температуры: ");
                buttonsPanel.add(inputDataTemperatureChoiceLabel);

                for (JButton button : inputDataButtonsList) {
                    buttonsPanel.add(button);
                }

                JLabel outputDataTemperatureChoiceLabel = new JLabel("Конвертировать температуру в: ");
                buttonsPanel.add(outputDataTemperatureChoiceLabel);

                for (JButton button : outputDataButtonsList) {
                    buttonsPanel.add(button);
                }
            }
        });
    }

    @Override
    public void showConvertationResult(double temperature) {
        textFieldOutput.setText(String.valueOf(temperature));
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }
}