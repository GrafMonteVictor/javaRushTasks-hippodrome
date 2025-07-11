import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class HippodromeTest {
    @Test
    public void constructorHippodromeTestWhenNullThenException() {
        assertThrows(IllegalArgumentException.class, () -> Mockito.spy(new Hippodrome(null)));
    }

    @Test
    public void constructorHippodromeTestWhenNullThenMessageInException() {
        String expected = "Horses cannot be null.";
        try {

            Mockito.spy(new Hippodrome(null));
        } catch (IllegalArgumentException e) {
            assertEquals(expected, e.getMessage());
        }
    }

    @Test
    public void constructorHippodromeTestWhenEmptyThenException() {
        assertThrows(IllegalArgumentException.class, () -> Mockito.spy(new Hippodrome(new ArrayList<>())));
    }

    @Test
    public void constructorHippodromeTestWhenEmptyThenMessageInException() {
        String expected = "Horses cannot be empty.";
        try {
            Mockito.spy(new Hippodrome(new ArrayList<>()));
        } catch (IllegalArgumentException e) {
            assertEquals(expected, e.getMessage());
        }
    }

    @Test
    public void getHorsesTestWhenInputListThenSameList() {
        //иниц список
        //передаем в конструтор
        //вызываем метод getHorses, сравниваем с исх. списком
        List<Horse> horseListExpected = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            String name = "Horse_" + i;
            double speed = Horse.getRandomDouble(0.2, 0.9);
            horseListExpected.add(new Horse(name, speed));
        }
        Hippodrome hippodromeSpy = Mockito.spy(new Hippodrome(horseListExpected));
        assertEquals(horseListExpected, hippodromeSpy.getHorses());
    }

    @Test
    public void getMoveTestWhenCallMoveThenCallMoveOfHorses() {
        List<Horse> horseListSpy = Mockito.spy(new ArrayList<>());
        for (int i = 0; i < 50; i++) {
            Horse horseMock = Mockito.mock(Horse.class);
            horseListSpy.add(horseMock);
        }
        Hippodrome hippodromeMock = Mockito.spy(new Hippodrome(horseListSpy));
        hippodromeMock.move();
        List<Horse> horseListNew = hippodromeMock.getHorses();
        for (Horse horse: horseListNew) {
            Mockito.verify(horse).move();
        }

    }
}
