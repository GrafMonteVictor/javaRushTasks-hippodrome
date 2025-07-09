import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class HorseTest {

    @Test
    public void constructorHorseTestWhenNullThenException() {
        String name = null;
        double speed = 1, distance = 1;
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));
    }

    @Test
    public void constructorHorseTestWhenNullThenMessageInException() {
        String name = null;
        double speed = 1, distance = 1;
        try {
            new Horse(name, speed, distance);
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be null.", e.getMessage());
        }
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = "    ")
    public void constructorHorseTestWhenNullOrSpaceThenException(String s) {
        double speed = 1, distance = 1;
        assertThrows(IllegalArgumentException.class, () -> new Horse(s, speed, distance));
    }

    @Test
    public void constructorHorseTestWhenSpaceThenMessageInException() {
        String name = " ";
        try {
            new Horse(name, 1, 1);
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be blank.", e.getMessage());
        }
    }

    @Test
    public void constructorHorseTestWhenNegativeSpeedThenException() {
        double speed = - 1;
        assertThrows(IllegalArgumentException.class, () -> new Horse("name", speed, 1));
    }

    @Test
    public void constructorHorseTestWhenNegativeSpeedThenMessageInException() {
        double speed = - 1;
        String result = "Speed cannot be negative.";
        try {
            new Horse("name", speed, 1);
        } catch (IllegalArgumentException e) {
            assertEquals(result, e.getMessage());
        }
    }

    @Test
    public void constructorHorseTestWhenNegativeDistanceThenException() {
        double distance = - 1;
        assertThrows(IllegalArgumentException.class, () -> new Horse("name", 1, distance));
    }

    @Test
    public void constructorHorseTestWhenNegativeDistanceThenMessageInException() {
        double distance = - 1;
        String result = "Distance cannot be negative.";
        try {
            new Horse("name", 1, distance);
        } catch (IllegalArgumentException e) {
            assertEquals(result, e.getMessage());
        }
    }

    @Test
    public void getNameTest() {
        String name = "name";
        String expected = "name";
        Horse horse = new Horse(name, 1, 1);
        assertEquals(expected, horse.getName());
    }

    @Test
    public void getSpeedTest() {
        double expected = 1;
        Horse horse = new Horse("name", 1, 2);
        assertEquals(expected, horse.getSpeed());
    }

    @Test
    public void getDistanceTestWhenPositiveThenOk() {
        double expected = 2;
        Horse horse = new Horse("name", 1, 2);
        assertEquals(expected, horse.getDistance());
    }

    @Test
    public void getDistanceTestWhenWithoutDistanceThenDistanceZero() {
        double expected = 0;
        Horse horse = new Horse("name", 1);
        assertEquals(expected, horse.getDistance());
    }


    @Test
    public void getMoveTestWhenVerifyGetRandomDouble() {
        //создание стат. мок-класса
        try (MockedStatic<Horse> horseMockedStatic =  Mockito.mockStatic(Horse.class)) {
            Horse horseSpy = Mockito.spy(new Horse("name", 1, 1)); //создаем spy-объект
            horseSpy.move(); //вызываем метод
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9)); //проверяем, вызвался стат. метод в методе выше

        }
    }
}
