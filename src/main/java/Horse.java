import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static java.util.Objects.isNull;

public class Horse {
    private final static Logger logger = LogManager.getLogger(Horse.class);

    private final String name;
    private final double speed;
    private double distance;

    public Horse(String name, double speed, double distance) {
        if (isNull(name)) {
            logger.error("Name is null");
            throw new IllegalArgumentException("Name cannot be null.");
        } else if (name.isBlank()) {
            logger.error("Name is blank");
            throw new IllegalArgumentException("Name cannot be blank.");
        }
        if (speed < 0) {
            logger.error("Speed is negative");
            throw new IllegalArgumentException("Speed cannot be negative.");
        }
        if (distance < 0) {
            logger.error("Distance is negative");
            throw new IllegalArgumentException("Distance cannot be negative.");
        }

        this.name = name;
        this.speed = speed;
        this.distance = distance;

        logger.debug("Создание Horse, имя [{}], скорость [{}]", name, speed);
    }

    public Horse(String name, double speed) {
        this(name, speed, 0);
    }

    public String getName() {
        return name;
    }

    public double getSpeed() {
        return speed;
    }

    public double getDistance() {
        return distance;
    }

    public void move() {
        distance += speed * getRandomDouble(0.2, 0.9);
    }

    public static double getRandomDouble(double min, double max) {
        return (Math.random() * (max - min)) + min;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; //проверка на ссылки
        if (o == null || getClass() != o.getClass()) return false; //проверка на null и принадлежность к классу

        Horse horse = (Horse) o;
        //сравнение каждого поля
        return (name.equals(horse.getName()) &&
                speed == horse.getSpeed() &&
                distance == horse.getSpeed());
    }
}
