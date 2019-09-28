package ua.temnokhud.basecomponets.util.func;

@FunctionalInterface
public interface BiFunction<T, U, R> {

    R apply(T firstValue, U secondValue);

}
