package ua.temnokhud.practice3.calculator;

public interface CalculatorListener {

    void onCalculatorStateChanged(Calculator calculator, String statement);

    void onCalculationFinished(Calculator calculator, double result);

    void onCalculationFailed(Calculator calculator, String error);

}
