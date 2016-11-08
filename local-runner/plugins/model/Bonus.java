package model;

/**
 * Класс, определяющий бонус --- неподвижный полезный объект. Содержит также все свойства круглого юнита.
 */
public class Bonus extends CircularUnit {
    private final BonusType type;

    public Bonus(
            long id, double x, double y, double speedX, double speedY, double angle, Faction faction, double radius,
            BonusType type) {
        super(id, x, y, speedX, speedY, angle, faction, radius);

        this.type = type;
    }

    /**
     * @return Возвращает тип бонуса.
     */
    public BonusType getType() {
        return type;
    }
}
