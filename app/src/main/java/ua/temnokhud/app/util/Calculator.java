package ua.temnokhud.app.util;

import java.util.Stack;

import ua.temnokhud.app.model.CalculatorOperation;
import ua.temnokhud.app.util.listeners.CalculatorListener;

public class Calculator {

    private String statement;
    private CalculatorListener calculatorListener;

    public Calculator(CalculatorListener calculatorListener) {
        this.calculatorListener = calculatorListener;
        clear();
    }

    public void clear() {
        this.statement = "";
        this.calculatorListener.onCalculatorStateChanged(this, statement);
    }

    public void remove() {
        if (statement.isEmpty()) {
            return;
        }
        this.statement = statement.substring(0, statement.length() - 1);
        this.calculatorListener.onCalculatorStateChanged(this, statement);
    }

    public boolean canPerformOperation() {
        if (statement.isEmpty()) {
            return false;
        }

        int lastIndex = statement.length() - 1;
        char lastChar = statement.charAt(lastIndex);
        return Character.isDigit(lastChar);
    }

    public void addOperation(CalculatorOperation calculatorOperation) {
        this.statement += calculatorOperation.getSign();
        this.calculatorListener.onCalculatorStateChanged(this, statement);
    }

    public void addValue(long value) {
        this.statement += value;
        this.calculatorListener.onCalculatorStateChanged(this, statement);
    }

    public void calculate() {
        try {
            double result = calculateResult();
            this.calculatorListener.onCalculationFinished(this, result);

            clear();
            this.statement = Long.toString((long) (result));
            this.calculatorListener.onCalculatorStateChanged(this, statement);
        } catch (Exception exception) {
            clear();
            this.calculatorListener.onCalculationFailed(this, exception.getLocalizedMessage());
        }
    }

    private double calculateResult() {
        Stack<Double> values = new Stack<>();
        Stack<CalculatorOperation> operations = new Stack<>();

        int i = 0;
        while (i < statement.length()) {
            char currentCharacter = statement.charAt(i);

            if (Character.isDigit(currentCharacter)) {
                double currentValue = 0;

                while (i < statement.length() && Character.isDigit(currentCharacter = statement.charAt(i))) {
                    double currentNumber = currentCharacter - '0';
                    currentValue = (currentValue * 10) + currentNumber;
                    i++;
                }

                values.push(currentValue);
            }

            CalculatorOperation currentOperation = CalculatorOperation.getBySign(String.valueOf(currentCharacter));
            if (currentOperation != null) {
                while (!operations.empty() && operations.peek().getPrecedence() >= currentOperation.getPrecedence()) {
                    double secondValue = values.empty() ? 0 : values.pop();
                    double firstValue = values.empty() ? 0 : values.pop();
                    double result = operations.pop().getApplier().apply(firstValue, secondValue);
                    values.push(result);
                }

                operations.push(currentOperation);
                i++;
            }
        }

        while (!operations.empty()) {
            double secondValue = values.empty() ? 0 : values.pop();
            double firstValue = values.empty() ? 0 : values.pop();
            double result = operations.pop().getApplier().apply(firstValue, secondValue);
            values.push(result);
        }

        return values.pop();
    }

}

