package model;

/**
 * Класс, определяющий бонус --- неподвижный полезный объект. Содержит также все свойства прямоугольного юнита.
 */
public class Bonus extends RectangularUnit {
    private final BonusType type;

    public Bonus(
            long id, double mass, double x, double y, double speedX, double speedY, double angle, double angularSpeed,
            double width, double height, BonusType type) {
        super(id, mass, x, y, speedX, speedY, angle, angularSpeed, width, height);

        this.type = type;
    }

    /**
     * @return Возвращает тип бонуса.
     */
    public BonusType getType() {
        return type;
    }
}
