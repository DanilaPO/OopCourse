package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DesktopView implements View {
    private Controller controller;
    private double temperature;
    private JTextField textFieldOutput;

    private final List<JRadioButton> inputTemperatureRadioButtonsList = new ArrayList<>();
    private final List<JRadioButton> outputTemperatureRadioButtonsList = new ArrayList<>();

    private JRadioButton selectedInputTemperatureButton;
    private JRadioButton selectedOutputTemperatureButton;

    @Override
    public void start() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Конвертер температур");
            frame.setSize(500, 200);
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

            GridLayout buttonsPanelLayout = new GridLayout(2, controller.getTemperatureScales().length);
            buttonsPanel.setLayout(buttonsPanelLayout);
            buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            frame.add(buttonsPanel, BorderLayout.CENTER);

            JPanel buttonsLabelsPanel = new JPanel();
            GridLayout buttonsLabelsPanelLayout = new GridLayout(2, 1);
            buttonsLabelsPanel.setLayout(buttonsLabelsPanelLayout);
            buttonsLabelsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            frame.add(buttonsLabelsPanel, BorderLayout.LINE_START);

            for (int i = 0; i < controller.getTemperatureScales().length; i++) {
                JRadioButton inputDataChoiceTemperatureButton = new JRadioButton(controller.getTemperatureScales()[i]);
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
                        temperature = Double.parseDouble(inputTextField.getText());

                        if (inputTextField.getText() != null) {
                            controller.setTemperatureForConversion(temperature, inputDataChoiceTemperatureButton.getText(), selectedOutputTemperatureButton.getText());
                        }
                    } catch (NumberFormatException | IOException e) {
                        textFieldOutput.setText("");
                        JOptionPane.showMessageDialog(frame, "Требуется ввести число", "Ошибка ввода данных!", JOptionPane.ERROR_MESSAGE);
                    }
                });

                JRadioButton outputDataChoiceTemperatureButton = new JRadioButton(controller.getTemperatureScales()[i]);
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

            JLabel inputDataTemperatureChoiceLabel = new JLabel("Конвертируемая температура: ");
            buttonsLabelsPanel.add(inputDataTemperatureChoiceLabel);

            for (JRadioButton button : inputTemperatureRadioButtonsList) {
                buttonsPanel.add(button);
            }

            JLabel outputDataTemperatureChoiceLabel = new JLabel("Конвертировать температуру в: ");
            buttonsLabelsPanel.add(outputDataTemperatureChoiceLabel);

            for (JRadioButton button : outputTemperatureRadioButtonsList) {
                buttonsPanel.add(button);
            }

            inputTextField.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                }

                @Override
                public void keyPressed(KeyEvent e) {
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    selectedInputTemperatureButton.doClick();
                }
            });
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