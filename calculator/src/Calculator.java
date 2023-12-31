import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {

    private JFrame frame;
    private JTextField textField;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Calculator window = new Calculator();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Calculator() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Calculator");
        frame.setBounds(100, 100, 300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        textField = new JTextField();
        frame.getContentPane().add(textField, BorderLayout.NORTH);
        textField.setColumns(10);

        JPanel buttonPanel = new JPanel();
        frame.getContentPane().add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));

        JButton btn1 = new JButton("1");
        btn1.addActionListener(new NumberActionListener());
        buttonPanel.add(btn1);

        JButton btn2 = new JButton("2");
        btn2.addActionListener(new NumberActionListener());
        buttonPanel.add(btn2);

        JButton btn3 = new JButton("3");
        btn3.addActionListener(new NumberActionListener());
        buttonPanel.add(btn3);

        JButton btnPlus = new JButton("+");
        btnPlus.addActionListener(new OperatorActionListener());
        buttonPanel.add(btnPlus);

        JButton btn4 = new JButton("4");
        btn4.addActionListener(new NumberActionListener());
        buttonPanel.add(btn4);

        JButton btn5 = new JButton("5");
        btn5.addActionListener(new NumberActionListener());
        buttonPanel.add(btn5);

        JButton btn6 = new JButton("6");
        btn6.addActionListener(new NumberActionListener());
        buttonPanel.add(btn6);

        JButton btnMinus = new JButton("-");
        btnMinus.addActionListener(new OperatorActionListener());
        buttonPanel.add(btnMinus);

        JButton btn7 = new JButton("7");
        btn7.addActionListener(new NumberActionListener());
        buttonPanel.add(btn7);

        JButton btn8 = new JButton("8");
        btn8.addActionListener(new NumberActionListener());
        buttonPanel.add(btn8);

        JButton btn9 = new JButton("9");
        btn9.addActionListener(new NumberActionListener());
        buttonPanel.add(btn9);

        JButton btnMultiply = new JButton("*");
        btnMultiply.addActionListener(new OperatorActionListener());
        buttonPanel.add(btnMultiply);

        JButton btn0 = new JButton("0");
        btn0.addActionListener(new NumberActionListener());
        buttonPanel.add(btn0);

        JButton btnEquals = new JButton("=");
        btnEquals.addActionListener(new EqualsActionListener());
        buttonPanel.add(btnEquals);

        JButton btnClear = new JButton("C");
        btnClear.addActionListener(new ClearActionListener());
        buttonPanel.add(btnClear);

        JButton btnDivide = new JButton("/");
        btnDivide.addActionListener(new OperatorActionListener());
        buttonPanel.add(btnDivide);
    }

    private class NumberActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String text = textField.getText();
            JButton button = (JButton) e.getSource();
            String number = button.getText();
            textField.setText(text + number);
        }
    }

    private class OperatorActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String text = textField.getText();
            JButton button = (JButton) e.getSource();
            String operator = button.getText();
            textField.setText(text + " " + operator + " ");
        }
    }

    private class EqualsActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String expression = textField.getText();
            String[] parts = expression.split(" ");
            if (parts.length != 3) {
                JOptionPane.showMessageDialog(frame, "Invalid expression", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double num1;
            double num2;
            try {
                num1 = Double.parseDouble(parts[0]);
                num2 = Double.parseDouble(parts[2]);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid numbers", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double result;
            switch (parts[1]) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) {
                        JOptionPane.showMessageDialog(frame, "Division by zero", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    result = num1 / num2;
                    break;
                default:
                    JOptionPane.showMessageDialog(frame, "Invalid operator", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
            }

            textField.setText(Double.toString(result));
        }
    }

    private class ClearActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            textField.setText("");
        }
    }
}