package task;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Counter {

    /**
     * Подсчитать количество всех элементов массива.
     *
     * @param array Массив, элементы которого нужно посчитать.
     * @return Ассоциативный массив, где ключ – элемент списка, значение – количество этих элементов в списке.
     */
    public static Map<Integer, Integer> calculateNumberMatchesOfElements(int[] array) {
        Objects.requireNonNull(array, "Недопустимый аргумент метода: \"Null\"");
        return Arrays.stream(array)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(e -> 1)));
    }
}
