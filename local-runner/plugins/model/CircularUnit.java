package model;

/**
 * Базовый класс для определения круглых объектов. Содержит также все свойства юнита.
 */
@SuppressWarnings("AbstractClassWithoutAbstractMethods")
public abstract class CircularUnit extends Unit {
    private final double radius;

    protected CircularUnit(
            long id, double x, double y, double speedX, double speedY, double angle, Faction faction, double radius) {
        super(id, x, y, speedX, speedY, angle, faction);

        this.radius = radius;
    }

    /**
     * @return Возвращает радиус объекта.
     */
    public double getRadius() {
        return radius;
    }
}
