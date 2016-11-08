package model;

import java.util.Arrays;

/**
 * Класс, определяющий волшебника. Содержит также все свойства живого юнита.
 */
public class Wizard extends LivingUnit {
    private final long ownerPlayerId;
    private final boolean me;
    private final int mana;
    private final int maxMana;
    private final double visionRange;
    private final double castRange;
    private final int xp;
    private final int level;
    private final SkillType[] skills;
    private final int remainingActionCooldownTicks;
    private final int[] remainingCooldownTicksByAction;
    private final boolean master;
    private final Message[] messages;

    public Wizard(
            long id, double x, double y, double speedX, double speedY, double angle, Faction faction, double radius,
            int life, int maxLife, Status[] statuses, long ownerPlayerId, boolean me, int mana, int maxMana,
            double visionRange, double castRange, int xp, int level, SkillType[] skills,
            int remainingActionCooldownTicks, int[] remainingCooldownTicksByAction, boolean master,
            Message[] messages) {
        super(id, x, y, speedX, speedY, angle, faction, radius, life, maxLife, statuses);

        this.ownerPlayerId = ownerPlayerId;
        this.me = me;
        this.mana = mana;
        this.maxMana = maxMana;
        this.visionRange = visionRange;
        this.castRange = castRange;
        this.xp = xp;
        this.level = level;
        this.skills = Arrays.copyOf(skills, skills.length);
        this.remainingActionCooldownTicks = remainingActionCooldownTicks;
        this.remainingCooldownTicksByAction = Arrays.copyOf(remainingCooldownTicksByAction, remainingCooldownTicksByAction.length);
        this.master = master;
        this.messages = Arrays.copyOf(messages, messages.length);
    }

    /**
     * @return Возвращает идентификатор игрока, которому принадлежит волшебник.
     */
    public long getOwnerPlayerId() {
        return ownerPlayerId;
    }

    /**
     * @return Возвращает {@code true} в том и только том случае, если этот волшебник ваш.
     */
    public boolean isMe() {
        return me;
    }

    /**
     * @return Возвращает текущее количество магической энергии волшебника.
     */
    public int getMana() {
        return mana;
    }

    /**
     * @return Возвращает максимальное количество магической энергии волшебника.
     */
    public int getMaxMana() {
        return maxMana;
    }

    /**
     * @return Возвращает максимальное расстояние (от центра до центра),
     * на котором данный волшебник обнаруживает другие объекты.
     */
    public double getVisionRange() {
        return visionRange;
    }

    /**
     * @return Возвращает максимальное расстояние (от центра волшебника),
     * которое может преодолеть выпущенный им магический снаряд.
     * <p>
     * Также является максимально возможной дальностью применения заклинаний, накладывающих на цель магический статус
     * ({@code HASTE} и {@code SHIELD}).
     */
    public double getCastRange() {
        return castRange;
    }

    /**
     * @return Возвращает количество очков опыта, полученное волшебником в процессе игры.
     */
    public int getXp() {
        return xp;
    }

    /**
     * @return Возвращает текущий уровень волшебника.
     * <p>
     * Начальный уровень каждого волшебника равен {@code 0}, а максимальный --- {@code game.levelUpXpValues.length}.
     * <p>
     * В некоторых режимах игры рост уровня волшебника может быть заблокирован.
     */
    public int getLevel() {
        return level;
    }

    /**
     * @return Возвращает умения, изученные волшебником.
     */
    public SkillType[] getSkills() {
        return Arrays.copyOf(skills, skills.length);
    }

    /**
     * @return Возвращает количество тиков, оставшееся до любого следующего действия.
     * <p>
     * Для совершения произвольного действия {@code actionType} необходимо, чтобы оба значения
     * {@code remainingActionCooldownTicks} и {@code remainingCooldownTicksByAction[actionType.ordinal()]} были равны
     * нулю.
     */
    public int getRemainingActionCooldownTicks() {
        return remainingActionCooldownTicks;
    }

    /**
     * @return Возвращает массив целых неотрицательных чисел. Каждая ячейка массива содержит значение количества тиков,
     * оставшегося до совершения следующего действия с соответствующим индексом.
     * <p>
     * Например, {@code remainingCooldownTicksByAction[0]} соответствует действию {@code NONE} и всегда равно нулю.
     * {@code remainingCooldownTicksByAction[1]} соответствует действию {@code STAFF} и равно количеству тиков,
     * оставшемуся до совершения данного действия. {@code remainingCooldownTicksByAction[2]} соответствует действию
     * {@code MAGIC_MISSILE} и так далее.
     * <p>
     * Для совершения произвольного действия {@code actionType} необходимо, чтобы оба значения
     * {@code remainingActionCooldownTicks} и {@code remainingCooldownTicksByAction[actionType.ordinal()]} были равны
     * нулю.
     */
    public int[] getRemainingCooldownTicksByAction() {
        return Arrays.copyOf(remainingCooldownTicksByAction, remainingCooldownTicksByAction.length);
    }

    /**
     * @return Возвращает {@code true} в том и только том случае, если этот волшебник является верховным.
     * <p>
     * Количество верховных волшебников в каждой фракции строго равно одному.
     */
    public boolean isMaster() {
        return master;
    }

    /**
     * @return Возвращает сообщения, предназначенные данному волшебнику, если есть право на их просмотр.
     * <p>
     * Стратегия может просматривать только сообщения, адресатом которых является управляемый ею волшебник.
     */
    public Message[] getMessages() {
        return Arrays.copyOf(messages, messages.length);
    }
}
