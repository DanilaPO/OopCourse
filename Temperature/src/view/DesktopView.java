package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DesktopView implements View {
    private Controller controller;
    private JTextField temperatureTextOutputField;

    private final List<JRadioButton> inputTemperatureRadioButtonsList = new ArrayList<>();
    private final List<JRadioButton> outputTemperatureRadioButtonsList = new ArrayList<>();

    private JRadioButton selectedInputTemperatureButton;
    private JRadioButton selectedOutputTemperatureButton;

    private String[] scalesNames;

    @Override
    public void start() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Конвертер температур");
            frame.setSize(530, 200);
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            GridLayout panelsGridLayout = new GridLayout(1, 2);
            panelsGridLayout.setHgap(-20);

            JPanel panelInput = new JPanel();
            panelInput.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            panelInput.setLayout(panelsGridLayout);
            frame.add(panelInput, BorderLayout.PAGE_START);

            JLabel labelInput = new JLabel("Введите температуру: ");
            panelInput.add(labelInput);

            JTextField temperatureTextInputField = new JTextField(20);
            panelInput.add(temperatureTextInputField);

            JPanel panelOutput = new JPanel();
            panelOutput.setLayout(panelsGridLayout);
            panelOutput.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            frame.add(panelOutput, BorderLayout.PAGE_END);

            JLabel labelOutput = new JLabel("Результат конвертации температуры: ");
            panelOutput.add(labelOutput);

            temperatureTextOutputField = new JTextField(20);
            temperatureTextOutputField.setEnabled(false);
            temperatureTextOutputField.setDisabledTextColor(Color.BLACK);
            panelOutput.add(temperatureTextOutputField);

            JPanel buttonsPanel = new JPanel();

            GridLayout buttonsPanelLayout = new GridLayout(2, controller.getScalesNames().length);
            buttonsPanel.setLayout(buttonsPanelLayout);
            buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            frame.add(buttonsPanel, BorderLayout.CENTER);

            JPanel buttonsLabelsPanel = new JPanel();
            GridLayout buttonsLabelsPanelLayout = new GridLayout(2, 1);
            buttonsLabelsPanel.setLayout(buttonsLabelsPanelLayout);
            buttonsLabelsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            frame.add(buttonsLabelsPanel, BorderLayout.LINE_START);

            scalesNames = controller.getScalesNames();

            for (int i = 0; i < controller.getScalesNames().length; i++) {
                String scaleName = scalesNames[i];

                JRadioButton inputDataChoiceTemperatureButton = new JRadioButton(scaleName);
                inputTemperatureRadioButtonsList.add(inputDataChoiceTemperatureButton);

                inputTemperatureRadioButtonsList.get(0).setSelected(true);
                selectedInputTemperatureButton = inputTemperatureRadioButtonsList.get(0);

                inputDataChoiceTemperatureButton.addActionListener(actionEvent -> {
                    try {
                        for (JRadioButton button : inputTemperatureRadioButtonsList) {
                            button.setSelected(false);
                            selectedInputTemperatureButton = null;
                        }

                        inputDataChoiceTemperatureButton.setSelected(true);
                        selectedInputTemperatureButton = inputDataChoiceTemperatureButton;
                        double temperature = Double.parseDouble(temperatureTextInputField.getText());

                        controller.setTemperatureForConversion(temperature, inputDataChoiceTemperatureButton.getText(), selectedOutputTemperatureButton.getText());
                    } catch (NumberFormatException | IOException e) {
                        temperatureTextOutputField.setText("");
                        JOptionPane.showMessageDialog(frame, "Требуется ввести число", "Ошибка ввода данных!", JOptionPane.ERROR_MESSAGE);
                    }
                });

                JRadioButton outputDataChoiceTemperatureButton = new JRadioButton(scaleName);
                outputTemperatureRadioButtonsList.add(outputDataChoiceTemperatureButton);

                outputTemperatureRadioButtonsList.get(0).setSelected(true);
                selectedOutputTemperatureButton = outputTemperatureRadioButtonsList.get(0);

                outputDataChoiceTemperatureButton.addActionListener(actionEvent -> {
                    for (JRadioButton button : outputTemperatureRadioButtonsList) {
                        button.setSelected(false);
                        selectedOutputTemperatureButton = null;
                    }

                    outputDataChoiceTemperatureButton.setSelected(true);
                    selectedOutputTemperatureButton = outputDataChoiceTemperatureButton;
                    selectedInputTemperatureButton.doClick();
                });
            }

            JLabel inputTemperatureLabel = new JLabel("Конвертируемая температура: ");
            buttonsLabelsPanel.add(inputTemperatureLabel);

            for (JRadioButton button : inputTemperatureRadioButtonsList) {
                buttonsPanel.add(button);
            }

            JLabel outputTemperatureLabel = new JLabel("Конвертировать температуру в: ");
            buttonsLabelsPanel.add(outputTemperatureLabel);

            for (JRadioButton button : outputTemperatureRadioButtonsList) {
                buttonsPanel.add(button);
            }

            temperatureTextInputField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    selectedInputTemperatureButton.doClick();
                }
            });
        });
    }

    @Override
    public void showConversionResult(double temperature) {
        temperatureTextOutputField.setText(String.valueOf(temperature));
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }
}