package model;

/**
 * Класс, определяющий строение. Фракционные строения самостоятельно атакуют противников в определённом радиусе.
 * <p>
 * Строения не могут быть заморожены ({@code FROZEN}).
 */
public class Building extends LivingUnit {
    private final BuildingType type;
    private final double visionRange;
    private final double attackRange;
    private final int damage;
    private final int cooldownTicks;
    private final int remainingActionCooldownTicks;

    public Building(
            long id, double x, double y, double speedX, double speedY, double angle, Faction faction, double radius,
            int life, int maxLife, Status[] statuses, BuildingType type, double visionRange, double attackRange,
            int damage, int cooldownTicks, int remainingActionCooldownTicks) {
        super(id, x, y, speedX, speedY, angle, faction, radius, life, maxLife, statuses);

        this.type = type;
        this.visionRange = visionRange;
        this.attackRange = attackRange;
        this.damage = damage;
        this.cooldownTicks = cooldownTicks;
        this.remainingActionCooldownTicks = remainingActionCooldownTicks;
    }

    /**
     * @return Возвращает тип строения.
     */
    public BuildingType getType() {
        return type;
    }

    /**
     * @return Возвращает максимальное расстояние (от центра до центра),
     * на котором данное строение обнаруживает другие объекты.
     */
    public double getVisionRange() {
        return visionRange;
    }

    /**
     * @return Возвращает максимальное расстояние (от центра до центра),
     * на котором данное строение может атаковать другие объекты.
     */
    public double getAttackRange() {
        return attackRange;
    }

    /**
     * @return Возвращает урон одной атаки.
     */
    public int getDamage() {
        return damage;
    }

    /**
     * @return Возвращает интервал между атаками.
     */
    public int getCooldownTicks() {
        return cooldownTicks;
    }

    /**
     * @return Возвращает количество тиков, оставшееся до следующей атаки.
     */
    public int getRemainingActionCooldownTicks() {
        return remainingActionCooldownTicks;
    }
}
