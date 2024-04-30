package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.JOptionPane.MESSAGE_PROPERTY;

public class DesktopView implements View {
    private Controller controller;
    private double temperature;
    private JTextField textFieldOutput;

    private final List<JButton> inputDataButtonsList = new ArrayList<>();
    private final List<JButton> outputDataButtonsList = new ArrayList<>();

    @Override
    public void start() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Конвертер температур");
                frame.setSize(800, 200);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);

                JPanel panelInput = new JPanel();
                frame.add(panelInput, BorderLayout.PAGE_START);

                JLabel labelInput = new JLabel("Введите конвертируемую величину темературы: ");
                panelInput.add(labelInput);

                JTextField inputTextField = new JTextField(20);
                panelInput.add(inputTextField);

                JPanel panelOutput = new JPanel();
                frame.add(panelOutput, BorderLayout.PAGE_END);

                JLabel labelOutput = new JLabel("Результат конвертации температуры: ");
                panelOutput.add(labelOutput);

                textFieldOutput = new JTextField(20);
                textFieldOutput.setEnabled(false);
                panelOutput.add(textFieldOutput);

                JPanel buttonsPanel = new JPanel();
                GridLayout gridLayout = new GridLayout(2, 4);
                buttonsPanel.setLayout(gridLayout);
                frame.add(buttonsPanel, BorderLayout.CENTER);

                JLabel inputDataTemperatureChoiceLabel = new JLabel("Начальный вид температуры: ");
                buttonsPanel.add(inputDataTemperatureChoiceLabel);

                JButton inputDataChoiceCelsiusButton = new JButton("Цельсии");
                inputDataButtonsList.add(inputDataChoiceCelsiusButton);
                buttonsPanel.add(inputDataChoiceCelsiusButton);

                inputDataChoiceCelsiusButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        try {
                            for (JButton button: inputDataButtonsList) {
                                button.setBackground(null);
                                button.setSelected(false);
                            }

                            inputDataChoiceCelsiusButton.setBackground(Color.red);
                            inputDataChoiceCelsiusButton.setSelected(true);

                            temperature = Double.parseDouble(inputTextField.getText());
                            controller.putCelsiusTemperature(temperature);

                            outputDataButtonsList.stream()
                                    .filter(x -> x.isSelected() && inputTextField.getText() != null)
                                    .forEach(AbstractButton::doClick);
                        } catch (NumberFormatException e) {
                            for (JButton button: inputDataButtonsList) {
                                button.setBackground(null);
                                button.setSelected(false);
                            }

                            for (JButton button: outputDataButtonsList) {
                                button.setBackground(null);
                                button.setSelected(false);
                            }

                            inputTextField.setText(null);

                            JOptionPane.showMessageDialog(frame, "Требуется ввод числовых данных", "Ошибка ввода данных!", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                });

                JButton inputDataChoiceFahrenheitButton = new JButton("Фаренгейты");
                inputDataButtonsList.add(inputDataChoiceFahrenheitButton);
                buttonsPanel.add(inputDataChoiceFahrenheitButton);

                inputDataChoiceFahrenheitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        try {
                            for (JButton button: inputDataButtonsList) {
                                button.setBackground(null);
                                button.setSelected(false);
                            }

                            inputDataChoiceFahrenheitButton.setBackground(Color.red);
                            inputDataChoiceFahrenheitButton.setSelected(true);

                            temperature = Double.parseDouble(inputTextField.getText());
                            controller.putFahrenheitTemperature(temperature);

                            outputDataButtonsList.stream()
                                    .filter(x -> x.isSelected() && inputTextField.getText() != null)
                                    .forEach(AbstractButton::doClick);
                        } catch (NumberFormatException e) {
                            for (JButton button: inputDataButtonsList) {
                                button.setBackground(null);
                                button.setSelected(false);
                            }

                            for (JButton button: outputDataButtonsList) {
                                button.setBackground(null);
                                button.setSelected(false);
                            }

                            inputTextField.setText(null);

                            JOptionPane.showMessageDialog(frame, "Требуется ввод числовых данных", "Ошибка ввода данных!", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                });

                JButton inputDataChoiceKelvinButton = new JButton("Кельвины");
                inputDataButtonsList.add(inputDataChoiceKelvinButton);
                buttonsPanel.add(inputDataChoiceKelvinButton);

                inputDataChoiceKelvinButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        try {
                            for (JButton button: inputDataButtonsList) {
                                button.setBackground(null);
                                button.setSelected(false);
                            }

                            inputDataChoiceKelvinButton.setBackground(Color.red);
                            inputDataChoiceKelvinButton.setSelected(true);

                            temperature = Double.parseDouble(inputTextField.getText());
                            controller.putKelvinTemperature(temperature);

                            outputDataButtonsList.stream()
                                    .filter(x -> x.isSelected() && inputTextField.getText() != null)
                                    .forEach(AbstractButton::doClick);
                        } catch (NumberFormatException e) {
                            for (JButton button: inputDataButtonsList) {
                                button.setBackground(null);
                                button.setSelected(false);
                            }

                            for (JButton button: outputDataButtonsList) {
                                button.setBackground(null);
                                button.setSelected(false);
                            }

                            inputTextField.setText(null);

                            JOptionPane.showMessageDialog(frame, "Требуется ввод числовых данных", "Ошибка ввода данных!", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                });

                JLabel outputDataTemperatureChoiceLabel = new JLabel("Конвертировать температуру в: ");
                buttonsPanel.add(outputDataTemperatureChoiceLabel);

                JButton outputDataChoiceCelsiusButton = new JButton("Цельсии");
                outputDataButtonsList.add(outputDataChoiceCelsiusButton);
                buttonsPanel.add(outputDataChoiceCelsiusButton);

                outputDataChoiceCelsiusButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        try {
                            for (JButton button: outputDataButtonsList) {
                                button.setBackground(null);
                                button.setSelected(false);
                            }

                            outputDataChoiceCelsiusButton.setBackground(Color.blue);
                            outputDataChoiceCelsiusButton.setSelected(true);

                            boolean inputDataButtonsIsSelected = inputDataButtonsList.stream().anyMatch(AbstractButton::isSelected);

                            if(inputDataButtonsIsSelected) {
                                controller.convertToCelsiusTemperature();
                            } else {
                                temperature = Double.parseDouble(inputTextField.getText());
                            }
                        } catch (NumberFormatException e) {
                            for (JButton button: inputDataButtonsList) {
                                button.setBackground(null);
                                button.setSelected(false);
                            }

                            for (JButton button: outputDataButtonsList) {
                                button.setBackground(null);
                                button.setSelected(false);
                            }

                            inputTextField.setText(null);

                            JOptionPane.showMessageDialog(frame, "Требуется ввод числовых данных", "Ошибка ввода данных!", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                });

                JButton outputDataChoiceFahrenheitButton = new JButton("Фаренгейты");
                outputDataButtonsList.add(outputDataChoiceFahrenheitButton);
                buttonsPanel.add(outputDataChoiceFahrenheitButton);

                outputDataChoiceFahrenheitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        try {
                            for (JButton button: outputDataButtonsList) {
                                button.setBackground(null);
                                button.setSelected(false);
                            }

                            outputDataChoiceFahrenheitButton.setBackground(Color.blue);
                            outputDataChoiceFahrenheitButton.setSelected(true);

                            boolean inputDataButtonsIsSelected = inputDataButtonsList.stream().anyMatch(AbstractButton::isSelected);

                            if(inputDataButtonsIsSelected) {
                                controller.convertToFahrenheitTemperature();
                            } else {
                                temperature = Double.parseDouble(inputTextField.getText());
                            }
                        } catch (NumberFormatException e) {
                            for (JButton button: inputDataButtonsList) {
                                button.setBackground(null);
                                button.setSelected(false);
                            }

                            for (JButton button: outputDataButtonsList) {
                                button.setBackground(null);
                                button.setSelected(false);
                            }

                            inputTextField.setText(null);

                            JOptionPane.showMessageDialog(frame, "Требуется ввод числовых данных", "Ошибка ввода данных!", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                });

                JButton outputDataChoiceKelvinButton = new JButton("Кельвины");
                outputDataButtonsList.add(outputDataChoiceKelvinButton);
                buttonsPanel.add(outputDataChoiceKelvinButton);

                outputDataChoiceKelvinButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        try {
                            for (JButton button: outputDataButtonsList) {
                                button.setBackground(null);
                                button.setSelected(false);
                            }

                            outputDataChoiceKelvinButton.setBackground(Color.blue);
                            outputDataChoiceKelvinButton.setSelected(true);

                            boolean inputDataButtonsIsSelected = inputDataButtonsList.stream().anyMatch(AbstractButton::isSelected);

                            if(inputDataButtonsIsSelected) {
                                controller.convertToKelvinTemperature();
                            } else {
                                temperature = Double.parseDouble(inputTextField.getText());
                            }
                        } catch (NumberFormatException e) {
                            for (JButton button: inputDataButtonsList) {
                                button.setBackground(null);
                                button.setSelected(false);
                            }

                            for (JButton button: outputDataButtonsList) {
                                button.setBackground(null);
                                button.setSelected(false);
                            }

                            inputTextField.setText(null);

                            JOptionPane.showMessageDialog(frame, "Требуется ввод числовых данных", "Ошибка ввода данных!", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                });

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