package model;

/**
 * Базовый класс для определения круглых объектов. Содержит также все свойства юнита.
 */
public abstract class CircularUnit extends Unit {
    private final double radius;

    protected CircularUnit(
            long id, double mass, double x, double y, double speedX, double speedY, double angle, double angularSpeed,
            double radius) {
        super(id, mass, x, y, speedX, speedY, angle, angularSpeed);

        this.radius = radius;
    }

    /**
     * @return Возвращает радиус объекта.
     */
    public double getRadius() {
        return radius;
    }
}
