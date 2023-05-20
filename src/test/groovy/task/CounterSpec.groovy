package task

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class CounterSpec extends Specification {

    @Subject
    Counter counter = new Counter()

    @Unroll
    def "Когда массив #array, тогда возвращается карта #expectedMap"() {
        when:
        Map<Integer, Integer> actualMap = counter.calculateNumberMatchesOfElements(array as int[])

        then:
        actualMap == expectedMap

        where:
        array                 || expectedMap
        [7]                   || [7: 1]
        [1, 5, 0, 150]        || [1: 1, 5: 1, 0: 1, 150: 1]
        [0, 0, 0, 0]          || [0: 4]
        [1, 3, 4, 5, 1, 5, 4] || [1: 2, 3: 1, 4: 2, 5: 2]
        [-1, -5, -3, -5, 1]   || [(-1): 1, (-5): 2, (-3): 1, (1): 1]
    }

    @Unroll
    def "Когда массив #array, тогда генерируется исключение NullPointerException"() {
        when:
        counter.calculateNumberMatchesOfElements(array)

        then:
        NullPointerException exception = thrown()
        exception.message.contains(expectedMessage)

        where:
        array || expectedMessage
        null  || "Недопустимый аргумент метода: \"Null\""
    }

    @Unroll
    def "Когда массив в #arraySize элементов, то время выполнения не превышает допустимый предел(#timeLimit мс)"() {
        given:
        int[] array = (0..arraySize)

        when:
        long startTime = System.currentTimeMillis()
        counter.calculateNumberMatchesOfElements(array)
        long endTime = System.currentTimeMillis()

        then:
        (endTime - startTime) < timeLimit

        where:
        arraySize << [1_000_000]
        timeLimit << [500]
    }
}
