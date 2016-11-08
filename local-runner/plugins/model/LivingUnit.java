package model;

import java.util.Arrays;

/**
 * Класс, определяющий живого юнита круглой формы.
 */
@SuppressWarnings("AbstractClassWithoutAbstractMethods")
public abstract class LivingUnit extends CircularUnit {
    private final int life;
    private final int maxLife;
    private final Status[] statuses;

    protected LivingUnit(
            long id, double x, double y, double speedX, double speedY, double angle, Faction faction, double radius,
            int life, int maxLife, Status[] statuses) {
        super(id, x, y, speedX, speedY, angle, faction, radius);

        this.life = life;
        this.maxLife = maxLife;
        this.statuses = Arrays.copyOf(statuses, statuses.length);
    }

    /**
     * @return Возвращает текущее количество жизненной энергии.
     */
    public int getLife() {
        return life;
    }

    /**
     * @return Возвращает максимальное количество жизненной энергии.
     */
    public int getMaxLife() {
        return maxLife;
    }

    /**
     * @return Возвращает магические статусы, влияющие на живого юнита.
     */
    public Status[] getStatuses() {
        return Arrays.copyOf(statuses, statuses.length);
    }
}
