package ua.temnokhud.app.model;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import ua.temnokhud.R;
import ua.temnokhud.app.App;
import ua.temnokhud.app.util.func.BiFunction;

@Getter
public enum CalculatorOperation {

    @SuppressWarnings("Convert2MethodRef")
    PLUS(R.string.text_sum, 1, (v1, v2) -> v1 + v2),
    MINUS(R.string.text_subtract, 1, (v1, v2) -> v1 - v2),
    MULTIPLY(R.string.text_multiply, 2, (v1, v2) -> v1 * v2),
    DIVIDE(R.string.text_divide, 2, (v1, v2) -> v1 / v2);

    private static Map<String, CalculatorOperation> operationMap;

    static {
        operationMap = new HashMap<>();
        for (CalculatorOperation operation : values()) {
            operationMap.put(operation.getSign(), operation);
        }
    }

    @StringRes
    private int signStringRes;
    private int precedence;
    private BiFunction<Double, Double, Double> applier;

    CalculatorOperation(int signStringRes, int precedence, BiFunction<Double, Double, Double> applier) {
        this.signStringRes = signStringRes;
        this.precedence = precedence;
        this.applier = applier;
    }

    @Nullable
    public static CalculatorOperation getBySign(String sign) {
        return operationMap.get(sign);
    }

    public String getSign() {
        return App.getAppContext().getString(signStringRes);
    }

}
