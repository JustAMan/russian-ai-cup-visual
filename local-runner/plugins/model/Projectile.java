package model;

/**
 * Класс, определяющий снаряд. Содержит также все свойства круглого юнита.
 */
public class Projectile extends CircularUnit {
    private final ProjectileType type;
    private final long ownerUnitId;
    private final long ownerPlayerId;

    public Projectile(
            long id, double x, double y, double speedX, double speedY, double angle, Faction faction, double radius,
            ProjectileType type, long ownerUnitId, long ownerPlayerId) {
        super(id, x, y, speedX, speedY, angle, faction, radius);

        this.type = type;
        this.ownerUnitId = ownerUnitId;
        this.ownerPlayerId = ownerPlayerId;
    }

    /**
     * @return Возвращает тип снаряда.
     */
    public ProjectileType getType() {
        return type;
    }

    /**
     * @return Возвращает идентификатор юнита, создавшего данный снаряд.
     */
    public long getOwnerUnitId() {
        return ownerUnitId;
    }

    /**
     * @return Возвращает идентификатор игрока, юнит которого создал данный снаряд или {@code -1}.
     */
    public long getOwnerPlayerId() {
        return ownerPlayerId;
    }
}
