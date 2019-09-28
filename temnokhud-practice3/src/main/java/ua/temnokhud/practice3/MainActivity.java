package ua.temnokhud.practice3;

import android.app.AlertDialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;

import ua.temnokhud.basecomponets.BaseActivity;
import ua.temnokhud.practice3.calculator.Calculator;
import ua.temnokhud.practice3.calculator.CalculatorListener;
import ua.temnokhud.practice3.calculator.CalculatorOperation;

public class MainActivity extends BaseActivity implements CalculatorListener {

    private static final String STATE_CALCULATOR = "STATE_CALCULATOR";

    private LinearLayoutCompat lltRoot;

    private TextView btnStatement;
    private TextView btnRemove;
    private TextView btnClear;
    private TextView btnCalculate;

    private TextView btnSum;
    private TextView btnSubtract;
    private TextView btnMultiply;
    private TextView btnDivide;

    private TextView btnNumber0;
    private TextView btnNumber1;
    private TextView btnNumber2;
    private TextView btnNumber3;
    private TextView btnNumber4;
    private TextView btnNumber5;
    private TextView btnNumber6;
    private TextView btnNumber7;
    private TextView btnNumber8;
    private TextView btnNumber9;

    private Calculator calculator;

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected int getActionBarTitleStringRes() {
        return R.string.text_practice_3;
    }

    //TODO
    @Override
    protected void onRestoreState(@NonNull Bundle savedInstanceState) {
        calculator = savedInstanceState.getParcelable(STATE_CALCULATOR);
        if (calculator == null) {
            calculator = new Calculator(this);
        } else {
            calculator.setCalculatorListener(this);
        }
    }

    @Override
    protected void onSaveState(@NonNull Bundle outState) {
        outState.putParcelable(STATE_CALCULATOR, calculator);
    }

    @Override
    protected void onCreateViews(Bundle savedInstanceState) {
        lltRoot = findViewById(R.id.activity_main_llt_root);

        btnStatement = findViewById(R.id.activity_main_txv_statement);
        btnRemove = findViewById(R.id.activity_main_txv_remove);
        btnClear = findViewById(R.id.activity_main_txv_clear);
        btnCalculate = findViewById(R.id.activity_main_txv_calculate);

        btnSum = findViewById(R.id.activity_main_txv_sum);
        btnSubtract = findViewById(R.id.activity_main_txv_subtract);
        btnMultiply = findViewById(R.id.activity_main_txv_multiply);
        btnDivide = findViewById(R.id.activity_main_txv_divide);

        btnNumber0 = findViewById(R.id.activity_main_txv_number_0);
        btnNumber1 = findViewById(R.id.activity_main_txv_number_1);
        btnNumber2 = findViewById(R.id.activity_main_txv_number_2);
        btnNumber3 = findViewById(R.id.activity_main_txv_number_3);
        btnNumber4 = findViewById(R.id.activity_main_txv_number_4);
        btnNumber5 = findViewById(R.id.activity_main_txv_number_5);
        btnNumber6 = findViewById(R.id.activity_main_txv_number_6);
        btnNumber7 = findViewById(R.id.activity_main_txv_number_7);
        btnNumber8 = findViewById(R.id.activity_main_txv_number_8);
        btnNumber9 = findViewById(R.id.activity_main_txv_number_9);

        calculator = new Calculator(this);
    }

    @Override
    protected void onCreateListeners() {
        btnRemove.setOnClickListener(v -> remove());
        btnClear.setOnClickListener(v -> clear());
        btnCalculate.setOnClickListener(v -> calculate());

        btnSum.setOnClickListener(v -> applyOperation(CalculatorOperation.PLUS));
        btnSubtract.setOnClickListener(v -> applyOperation(CalculatorOperation.MINUS));
        btnMultiply.setOnClickListener(v -> applyOperation(CalculatorOperation.MULTIPLY));
        btnDivide.setOnClickListener(v -> applyOperation(CalculatorOperation.DIVIDE));

        btnNumber0.setOnClickListener(v -> applyValue(0));
        btnNumber1.setOnClickListener(v -> applyValue(1));
        btnNumber2.setOnClickListener(v -> applyValue(2));
        btnNumber3.setOnClickListener(v -> applyValue(3));
        btnNumber4.setOnClickListener(v -> applyValue(4));
        btnNumber5.setOnClickListener(v -> applyValue(5));
        btnNumber6.setOnClickListener(v -> applyValue(6));
        btnNumber7.setOnClickListener(v -> applyValue(7));
        btnNumber8.setOnClickListener(v -> applyValue(8));
        btnNumber9.setOnClickListener(v -> applyValue(9));
    }

    private void remove() {
        calculator.remove();
    }

    private void clear() {
        calculator.clear();
    }

    private void applyOperation(CalculatorOperation calculatorOperation) {
        calculator.addOperation(calculatorOperation);
    }

    private void applyValue(long value) {
        calculator.addValue(value);
    }

    private void calculate() {
        calculator.calculate();
    }

    @Override
    public void onCalculatorStateChanged(Calculator calculator, String statement) {
        btnStatement.setText(statement);
        btnSum.setEnabled(calculator.canPerformOperation());
        btnSubtract.setEnabled(calculator.canPerformOperation());
        btnDivide.setEnabled(calculator.canPerformOperation());
        btnMultiply.setEnabled(calculator.canPerformOperation());
    }

    @Override
    public void onCalculationFinished(Calculator calculator, double result) {
        new AlertDialog.Builder(this)
                .setMessage(String.valueOf(result))
                .setTitle(R.string.text_calculation_result)
                .setNeutralButton(R.string.text_ok, (dialog, which) -> dialog.dismiss())
                .show();
    }

    @Override
    public void onCalculationFailed(Calculator calculator, String error) {
        new AlertDialog.Builder(this)
                .setMessage(error)
                .setTitle(R.string.text_calculation_failed)
                .setNeutralButton(R.string.text_ok, (dialog, which) -> dialog.dismiss())
                .show();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        lltRoot.setOrientation(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE
                ? LinearLayoutCompat.HORIZONTAL
                : LinearLayoutCompat.VERTICAL);
    }

}
