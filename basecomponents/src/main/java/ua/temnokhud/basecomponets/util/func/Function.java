package ua.temnokhud.basecomponets.util.func;

@FunctionalInterface
public interface Function<T, R> {

    R apply(T value);

}
