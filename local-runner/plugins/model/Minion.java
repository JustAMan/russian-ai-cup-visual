package model;

/**
 * Класс, определяющий приспешника волшебника одной из фракций. Содержит также все свойства живого юнита.
 * <p>
 * Миньоны, оставшиеся по той или иной причине без хозяина, часто объединяются в небольшие группы и селятся в лесах.
 * Они крайне настороженно относятся ко всем другим волшебникам и их миньонам.
 */
public class Minion extends LivingUnit {
    private final MinionType type;
    private final double visionRange;
    private final int damage;
    private final int cooldownTicks;
    private final int remainingActionCooldownTicks;

    public Minion(
            long id, double x, double y, double speedX, double speedY, double angle, Faction faction, double radius,
            int life, int maxLife, Status[] statuses, MinionType type, double visionRange, int damage,
            int cooldownTicks, int remainingActionCooldownTicks) {
        super(id, x, y, speedX, speedY, angle, faction, radius, life, maxLife, statuses);

        this.type = type;
        this.visionRange = visionRange;
        this.damage = damage;
        this.cooldownTicks = cooldownTicks;
        this.remainingActionCooldownTicks = remainingActionCooldownTicks;
    }

    /**
     * @return Возвращает тип миньона.
     */
    public MinionType getType() {
        return type;
    }

    /**
     * @return Возвращает максимальное расстояние (от центра до центра),
     * на котором данный миньон обнаруживает другие объекты.
     */
    public double getVisionRange() {
        return visionRange;
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
