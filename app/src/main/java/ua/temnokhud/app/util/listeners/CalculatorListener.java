package ua.temnokhud.app.util.listeners;

import ua.temnokhud.app.util.Calculator;

public interface CalculatorListener {

    void onCalculatorStateChanged(Calculator calculator, String statement);

    void onCalculationFinished(Calculator calculator, double result);

    void onCalculationFailed(Calculator calculator, String error);

}
