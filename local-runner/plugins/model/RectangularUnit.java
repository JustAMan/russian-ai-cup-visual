package model;

/**
 * Базовый класс для определения прямоугольных объектов. Содержит также все свойства юнита.
 */
public abstract class RectangularUnit extends Unit {
    private final double width;
    private final double height;

    protected RectangularUnit(
            long id, double mass, double x, double y,
            double speedX, double speedY, double angle,
            double angularSpeed, double width, double height) {
        super(id, mass, x, y, speedX, speedY, angle, angularSpeed);

        this.width = width;
        this.height = height;
    }

    /**
     * @return Возвращает ширину объекта.
     */
    public double getWidth() {
        return width;
    }

    /**
     * @return Возвращает высоту объекта.
     */
    public double getHeight() {
        return height;
    }
}
