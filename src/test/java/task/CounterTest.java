package task;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тесты объекта Counter")
public class CounterTest {
    @Test
    @DisplayName("Когда массив равен null, тогда генерируется исключение")
    public void whenArrayIsNullThenExceptionIsThrownNullPointerException() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            Counter.calculateNumberMatchesOfElements(null);
        });
        String expectedMessage = "Недопустимый аргумент метода: \"Null\"";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Когда массив состоит из одного элемента, тогда возвращается карта с одной парой со значением 1")
    public void whenArrayConsistsOneElementThenSinglePairMapWithValueOf1IsReturned() {
        Map<Integer, Integer> actual = Counter.calculateNumberMatchesOfElements(new int[]{7});
        assertEquals(1, actual.get(7).intValue());
        assertEquals(1, actual.size());
    }

    @Test
    @DisplayName("Когда массив без повторных элементов, тогда значения ключей равны одному")
    public void whenArrayIsWithoutRepeatedElementsThenKeyValuesAreZero() {
        Map<Integer, Integer> actual = Counter.calculateNumberMatchesOfElements(new int[]{1, 5, 0, 150});
        assertTrue(actual.values().stream().allMatch(integer -> integer == 1));
        assertEquals(4, actual.size());
    }

    @Test
    @DisplayName("Когда массив состоит из одинаковых элементов, тогда возвращается один ключ со значением размера массива")
    public void whenArrayConsistsSameElementsThenReturnMapWithOneKeyAndAnArraySizeValueIsReturned() {
        Map<Integer, Integer> actual = Counter.calculateNumberMatchesOfElements(new int[]{0, 0, 0, 0});
        assertTrue(actual.keySet().stream().allMatch(integer -> integer == 0));
        assertEquals(1, actual.size());
    }

    @Test
    @DisplayName("Когда массив состоит из произвольно повторяющихся элементов, тогда возвращаются карта со значениями повторений")
    public void whenArrayConsistsArbitrarilyRepeatingElementsThenMapWithRepetitionValuesIsReturned() {
        Map<Integer, Integer> actual = Counter.calculateNumberMatchesOfElements(new int[]{1, 3, 4, 5, 1, 5, 4});
        Map<Integer, Integer> expected = new HashMap<>(Map.ofEntries(
                Map.entry(1, 2),
                Map.entry(3, 1),
                Map.entry(4, 2),
                Map.entry(5, 2)
        ));
        assertThat(actual, Matchers.equalTo(expected));
    }
}
