package model;

/**
 * Класс, определяющий метательный снаряд. Содержит также все свойства круглого юнита.
 */
public class Projectile extends CircularUnit {
    private final long carId;
    private final long playerId;
    private final ProjectileType type;

    public Projectile(
            long id, double mass, double x, double y,
            double speedX, double speedY, double angle,
            double angularSpeed, double radius,
            long carId, long playerId, ProjectileType type) {
        super(id, mass, x, y, speedX, speedY, angle, angularSpeed, radius);

        this.carId = carId;
        this.playerId = playerId;
        this.type = type;
    }

    /**
     * @return Возвращает идентификатор кодемобиля, выпустившего данный снаряд.
     */
    public long getCarId() {
        return carId;
    }

    /**
     * @return Возвращает идентификатор игрока, кодемобиль которого выпустил данный снаряд.
     */
    public long getPlayerId() {
        return playerId;
    }

    /**
     * @return Возвращает тип метательного снаряда.
     */
    public ProjectileType getType() {
        return type;
    }
}
