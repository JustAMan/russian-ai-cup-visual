package model;

/**
 * Тип умения. Изучение умений может быть доступно не во всех режимах игры (смотрите документацию к
 * {@code game.skillsEnabled}).
 * <p>
 * Умения делятся на три категории: активные, пассивные и ауры.
 * <ul>
 * <li>
 * Активные умения наделяют волшебника способностью использовать определённое действие, недоступное ранее.
 * </li>
 * <li>
 * Пассивные умения действуют постоянно, улучшая одну из характеристик волшебника на некоторое значение. При наличии
 * нескольких пассивных умений, влияющих на одну характеристику, учитывается только то, которое даёт максимальный
 * эффект.
 * </li>
 * <li>
 * Ауры действуют постоянно, улучшая на некоторое значение одну из характеристик самого волшебника, а также всех союзных
 * волшебников на расстоянии, не превышающем {@code game.auraSkillRange}. При наличии нескольких аур, влияющих на одну
 * характеристику, учитывается только та, которая даёт максимальный эффект.
 * </li>
 * </ul>
 */
public enum SkillType {
    /**
     * Пассивное умение. Увеличивает максимально возможную дальность полёта магического снаряда, а также дальность
     * применения магических статусов на {@code game.rangeBonusPerSkillLevel}.
     */
    RANGE_BONUS_PASSIVE_1,

    /**
     * Аура. Увеличивает максимально возможную дальность полёта магического снаряда, а также дальность
     * применения магических статусов на {@code game.rangeBonusPerSkillLevel}.
     * <p>
     * Требуется предварительно изучить умение {@code RANGE_BONUS_PASSIVE_1}.
     */
    RANGE_BONUS_AURA_1,

    /**
     * Пассивное умение. Увеличивает максимально возможную дальность полёта магического снаряда, а также дальность
     * применения магических статусов на {@code 2.0 * game.rangeBonusPerSkillLevel}.
     * <p>
     * Требуется предварительно изучить умение {@code RANGE_BONUS_AURA_1}.
     */
    RANGE_BONUS_PASSIVE_2,

    /**
     * Аура. Увеличивает максимально возможную дальность полёта магического снаряда, а также дальность
     * применения магических статусов на {@code 2.0 * game.rangeBonusPerSkillLevel}.
     * <p>
     * Требуется предварительно изучить умение {@code RANGE_BONUS_PASSIVE_2}.
     */
    RANGE_BONUS_AURA_2,

    /**
     * Пассивное умение. Убирает задержку на примение действия {@code MAGIC_MISSILE}.
     * Общая задержка на действия волшебника {@code game.wizardActionCooldownTicks} всё ещё применяется.
     * <p>
     * Требуется предварительно изучить умение {@code RANGE_BONUS_AURA_2}.
     */
    ADVANCED_MAGIC_MISSILE,

    /**
     * Пассивное умение. Увеличивает урон, наносимый при прямом попадании магического снаряда, на
     * {@code game.magicalDamageBonusPerSkillLevel}.
     */
    MAGICAL_DAMAGE_BONUS_PASSIVE_1,

    /**
     * Аура. Увеличивает урон, наносимый при прямом попадании магического снаряда, на
     * {@code game.magicalDamageBonusPerSkillLevel}.
     * <p>
     * Требуется предварительно изучить умение {@code MAGICAL_DAMAGE_BONUS_PASSIVE_1}.
     */
    MAGICAL_DAMAGE_BONUS_AURA_1,

    /**
     * Пассивное умение. Увеличивает урон, наносимый при прямом попадании магического снаряда, на
     * {@code 2.0 * game.magicalDamageBonusPerSkillLevel}.
     * <p>
     * Требуется предварительно изучить умение {@code MAGICAL_DAMAGE_BONUS_AURA_1}.
     */
    MAGICAL_DAMAGE_BONUS_PASSIVE_2,

    /**
     * Аура. Увеличивает урон, наносимый при прямом попадании магического снаряда, на
     * {@code 2.0 * game.magicalDamageBonusPerSkillLevel}.
     * <p>
     * Требуется предварительно изучить умение {@code MAGICAL_DAMAGE_BONUS_PASSIVE_2}.
     */
    MAGICAL_DAMAGE_BONUS_AURA_2,

    /**
     * Активное умение. Позволяет волшебнику использовать действие {@code FROST_BOLT}.
     * <p>
     * Требуется предварительно изучить умение {@code MAGICAL_DAMAGE_BONUS_AURA_2}.
     */
    FROST_BOLT,

    /**
     * Пассивное умение. Увеличивает урон, наносимый при ударе посохом, на {@code game.staffDamageBonusPerSkillLevel}.
     */
    STAFF_DAMAGE_BONUS_PASSIVE_1,

    /**
     * Аура. Увеличивает урон, наносимый при ударе посохом, на {@code game.staffDamageBonusPerSkillLevel}.
     * <p>
     * Требуется предварительно изучить умение {@code STAFF_DAMAGE_BONUS_PASSIVE_1}.
     */
    STAFF_DAMAGE_BONUS_AURA_1,

    /**
     * Пассивное умение. Увеличивает урон, наносимый при ударе посохом, на
     * {@code 2.0 * game.staffDamageBonusPerSkillLevel}.
     * <p>
     * Требуется предварительно изучить умение {@code STAFF_DAMAGE_BONUS_AURA_1}.
     */
    STAFF_DAMAGE_BONUS_PASSIVE_2,

    /**
     * Аура. Увеличивает урон, наносимый при ударе посохом, на {@code 2.0 * game.staffDamageBonusPerSkillLevel}.
     * <p>
     * Требуется предварительно изучить умение {@code STAFF_DAMAGE_BONUS_PASSIVE_2}.
     */
    STAFF_DAMAGE_BONUS_AURA_2,

    /**
     * Активное умение. Позволяет волшебнику использовать действие {@code FIREBALL}.
     * <p>
     * Требуется предварительно изучить умение {@code STAFF_DAMAGE_BONUS_AURA_2}.
     */
    FIREBALL,

    /**
     * Пассивное умение. Увеличивает скорость перемещения в {@code 1.0 + game.movementBonusFactorPerSkillLevel} раз.
     * <p>
     * Увеличение скорости от изучения пассивных умений и увеличение скорости в результате действия аур аддитивны.
     * Таким образом, умения {@code MOVEMENT_BONUS_FACTOR_PASSIVE_2} и {@code MOVEMENT_BONUS_FACTOR_AURA_2} суммарно
     * увеличат скорость перемещения в {@code 1.0 + 4.0 * game.movementBonusFactorPerSkillLevel} раз.
     */
    MOVEMENT_BONUS_FACTOR_PASSIVE_1,

    /**
     * Аура. Увеличивает скорость перемещения в {@code 1.0 + game.movementBonusFactorPerSkillLevel} раз.
     * <p>
     * Требуется предварительно изучить умение {@code MOVEMENT_BONUS_FACTOR_PASSIVE_1}.
     */
    MOVEMENT_BONUS_FACTOR_AURA_1,

    /**
     * Пассивное умение. Увеличивает скорость перемещения в {@code 1.0 + 2.0 * game.movementBonusFactorPerSkillLevel}
     * раз.
     * <p>
     * Требуется предварительно изучить умение {@code MOVEMENT_BONUS_FACTOR_AURA_1}.
     */
    MOVEMENT_BONUS_FACTOR_PASSIVE_2,

    /**
     * Аура. Увеличивает скорость перемещения в {@code 1.0 + 2.0 * game.movementBonusFactorPerSkillLevel} раз.
     * <p>
     * Требуется предварительно изучить умение {@code MOVEMENT_BONUS_FACTOR_PASSIVE_2}.
     */
    MOVEMENT_BONUS_FACTOR_AURA_2,

    /**
     * Активное умение. Позволяет волшебнику использовать действие {@code HASTE}.
     * <p>
     * Требуется предварительно изучить умение {@code MOVEMENT_BONUS_FACTOR_AURA_2}.
     */
    HASTE,

    /**
     * Пассивное умение. Уменьшает урон, получаемый при прямом попадании магического снаряда, на
     * {@code game.magicalDamageAbsorptionPerSkillLevel}.
     */
    MAGICAL_DAMAGE_ABSORPTION_PASSIVE_1,

    /**
     * Аура. Уменьшает урон, получаемый при прямом попадании магического снаряда, на
     * {@code game.magicalDamageAbsorptionPerSkillLevel}.
     * <p>
     * Требуется предварительно изучить умение {@code MAGICAL_DAMAGE_ABSORPTION_PASSIVE_1}.
     */
    MAGICAL_DAMAGE_ABSORPTION_AURA_1,

    /**
     * Пассивное умение. Уменьшает урон, получаемый при прямом попадании магического снаряда, на
     * {@code 2.0 * game.magicalDamageAbsorptionPerSkillLevel}.
     * <p>
     * Требуется предварительно изучить умение {@code MAGICAL_DAMAGE_ABSORPTION_AURA_1}.
     */
    MAGICAL_DAMAGE_ABSORPTION_PASSIVE_2,

    /**
     * Аура. Уменьшает урон, получаемый при прямом попадании магического снаряда, на
     * {@code 2.0 * game.magicalDamageAbsorptionPerSkillLevel}.
     * <p>
     * Требуется предварительно изучить умение {@code MAGICAL_DAMAGE_ABSORPTION_PASSIVE_2}.
     */
    MAGICAL_DAMAGE_ABSORPTION_AURA_2,

    /**
     * Активное умение. Позволяет волшебнику использовать действие {@code SHIELD}.
     * <p>
     * Требуется предварительно изучить умение {@code MAGICAL_DAMAGE_ABSORPTION_AURA_2}.
     */
    SHIELD
}
