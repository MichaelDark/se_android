package ua.temnokhud.app.util.func;

@FunctionalInterface
public interface Function<T, R> {

    R apply(T value);

}
