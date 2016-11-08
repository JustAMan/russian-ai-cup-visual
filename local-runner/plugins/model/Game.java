package model;

import java.util.Arrays;

/**
 * Предоставляет доступ к различным игровым константам.
 */
@SuppressWarnings("OverlyLongMethod")
public class Game {
    private final long randomSeed;
    private final int tickCount;
    private final double mapSize;
    private final boolean skillsEnabled;
    private final boolean rawMessagesEnabled;
    private final double friendlyFireDamageFactor;
    private final double buildingDamageScoreFactor;
    private final double buildingEliminationScoreFactor;
    private final double minionDamageScoreFactor;
    private final double minionEliminationScoreFactor;
    private final double wizardDamageScoreFactor;
    private final double wizardEliminationScoreFactor;
    private final double teamWorkingScoreFactor;
    private final int victoryScore;
    private final double scoreGainRange;
    private final int rawMessageMaxLength;
    private final double rawMessageTransmissionSpeed;
    private final double wizardRadius;
    private final double wizardCastRange;
    private final double wizardVisionRange;
    private final double wizardForwardSpeed;
    private final double wizardBackwardSpeed;
    private final double wizardStrafeSpeed;
    private final int wizardBaseLife;
    private final int wizardLifeGrowthPerLevel;
    private final int wizardBaseMana;
    private final int wizardManaGrowthPerLevel;
    private final double wizardBaseLifeRegeneration;
    private final double wizardLifeRegenerationGrowthPerLevel;
    private final double wizardBaseManaRegeneration;
    private final double wizardManaRegenerationGrowthPerLevel;
    private final double wizardMaxTurnAngle;
    private final int wizardMaxResurrectionDelayTicks;
    private final int wizardMinResurrectionDelayTicks;
    private final int wizardActionCooldownTicks;
    private final int staffCooldownTicks;
    private final int magicMissileCooldownTicks;
    private final int frostBoltCooldownTicks;
    private final int fireballCooldownTicks;
    private final int hasteCooldownTicks;
    private final int shieldCooldownTicks;
    private final int magicMissileManacost;
    private final int frostBoltManacost;
    private final int fireballManacost;
    private final int hasteManacost;
    private final int shieldManacost;
    private final int staffDamage;
    private final double staffSector;
    private final double staffRange;
    private final int[] levelUpXpValues;
    private final double minionRadius;
    private final double minionVisionRange;
    private final double minionSpeed;
    private final double minionMaxTurnAngle;
    private final int minionLife;
    private final int factionMinionAppearanceIntervalTicks;
    private final int orcWoodcutterActionCooldownTicks;
    private final int orcWoodcutterDamage;
    private final double orcWoodcutterAttackSector;
    private final double orcWoodcutterAttackRange;
    private final int fetishBlowdartActionCooldownTicks;
    private final double fetishBlowdartAttackRange;
    private final double fetishBlowdartAttackSector;
    private final double bonusRadius;
    private final int bonusAppearanceIntervalTicks;
    private final int bonusScoreAmount;
    private final double dartRadius;
    private final double dartSpeed;
    private final int dartDirectDamage;
    private final double magicMissileRadius;
    private final double magicMissileSpeed;
    private final int magicMissileDirectDamage;
    private final double frostBoltRadius;
    private final double frostBoltSpeed;
    private final int frostBoltDirectDamage;
    private final double fireballRadius;
    private final double fireballSpeed;
    private final double fireballExplosionMaxDamageRange;
    private final double fireballExplosionMinDamageRange;
    private final int fireballExplosionMaxDamage;
    private final int fireballExplosionMinDamage;
    private final double guardianTowerRadius;
    private final double guardianTowerVisionRange;
    private final double guardianTowerLife;
    private final double guardianTowerAttackRange;
    private final int guardianTowerDamage;
    private final int guardianTowerCooldownTicks;
    private final double factionBaseRadius;
    private final double factionBaseVisionRange;
    private final double factionBaseLife;
    private final double factionBaseAttackRange;
    private final int factionBaseDamage;
    private final int factionBaseCooldownTicks;
    private final int burningDurationTicks;
    private final int burningSummaryDamage;
    private final int empoweredDurationTicks;
    private final double empoweredDamageFactor;
    private final int frozenDurationTicks;
    private final int hastenedDurationTicks;
    private final double hastenedBonusDurationFactor;
    private final double hastenedMovementBonusFactor;
    private final double hastenedRotationBonusFactor;
    private final int shieldedDurationTicks;
    private final double shieldedBonusDurationFactor;
    private final double shieldedDirectDamageAbsorptionFactor;
    private final double auraSkillRange;
    private final double rangeBonusPerSkillLevel;
    private final int magicalDamageBonusPerSkillLevel;
    private final int staffDamageBonusPerSkillLevel;
    private final double movementBonusFactorPerSkillLevel;
    private final int magicalDamageAbsorptionPerSkillLevel;

    public Game(
            long randomSeed, int tickCount, double mapSize, boolean skillsEnabled, boolean rawMessagesEnabled,
            double friendlyFireDamageFactor, double buildingDamageScoreFactor, double buildingEliminationScoreFactor,
            double minionDamageScoreFactor, double minionEliminationScoreFactor, double wizardDamageScoreFactor,
            double wizardEliminationScoreFactor, double teamWorkingScoreFactor, int victoryScore, double scoreGainRange,
            int rawMessageMaxLength, double rawMessageTransmissionSpeed, double wizardRadius, double wizardCastRange,
            double wizardVisionRange, double wizardForwardSpeed, double wizardBackwardSpeed, double wizardStrafeSpeed,
            int wizardBaseLife, int wizardLifeGrowthPerLevel, int wizardBaseMana, int wizardManaGrowthPerLevel,
            double wizardBaseLifeRegeneration, double wizardLifeRegenerationGrowthPerLevel,
            double wizardBaseManaRegeneration, double wizardManaRegenerationGrowthPerLevel, double wizardMaxTurnAngle,
            int wizardMaxResurrectionDelayTicks, int wizardMinResurrectionDelayTicks, int wizardActionCooldownTicks,
            int staffCooldownTicks, int magicMissileCooldownTicks, int frostBoltCooldownTicks,
            int fireballCooldownTicks, int hasteCooldownTicks, int shieldCooldownTicks, int magicMissileManacost,
            int frostBoltManacost, int fireballManacost, int hasteManacost, int shieldManacost, int staffDamage,
            double staffSector, double staffRange, int[] levelUpXpValues, double minionRadius, double minionVisionRange,
            double minionSpeed, double minionMaxTurnAngle, int minionLife, int factionMinionAppearanceIntervalTicks,
            int orcWoodcutterActionCooldownTicks, int orcWoodcutterDamage, double orcWoodcutterAttackSector,
            double orcWoodcutterAttackRange, int fetishBlowdartActionCooldownTicks, double fetishBlowdartAttackRange,
            double fetishBlowdartAttackSector, double bonusRadius, int bonusAppearanceIntervalTicks,
            int bonusScoreAmount, double dartRadius, double dartSpeed, int dartDirectDamage, double magicMissileRadius,
            double magicMissileSpeed, int magicMissileDirectDamage, double frostBoltRadius, double frostBoltSpeed,
            int frostBoltDirectDamage, double fireballRadius, double fireballSpeed,
            double fireballExplosionMaxDamageRange, double fireballExplosionMinDamageRange,
            int fireballExplosionMaxDamage, int fireballExplosionMinDamage, double guardianTowerRadius,
            double guardianTowerVisionRange, double guardianTowerLife, double guardianTowerAttackRange,
            int guardianTowerDamage, int guardianTowerCooldownTicks, double factionBaseRadius,
            double factionBaseVisionRange, double factionBaseLife, double factionBaseAttackRange, int factionBaseDamage,
            int factionBaseCooldownTicks, int burningDurationTicks, int burningSummaryDamage,
            int empoweredDurationTicks, double empoweredDamageFactor, int frozenDurationTicks,
            int hastenedDurationTicks, double hastenedBonusDurationFactor, double hastenedMovementBonusFactor,
            double hastenedRotationBonusFactor, int shieldedDurationTicks, double shieldedBonusDurationFactor,
            double shieldedDirectDamageAbsorptionFactor, double auraSkillRange, double rangeBonusPerSkillLevel,
            int magicalDamageBonusPerSkillLevel, int staffDamageBonusPerSkillLevel,
            double movementBonusFactorPerSkillLevel, int magicalDamageAbsorptionPerSkillLevel) {
        this.randomSeed = randomSeed;
        this.tickCount = tickCount;
        this.mapSize = mapSize;
        this.skillsEnabled = skillsEnabled;
        this.rawMessagesEnabled = rawMessagesEnabled;
        this.friendlyFireDamageFactor = friendlyFireDamageFactor;
        this.buildingDamageScoreFactor = buildingDamageScoreFactor;
        this.buildingEliminationScoreFactor = buildingEliminationScoreFactor;
        this.minionDamageScoreFactor = minionDamageScoreFactor;
        this.minionEliminationScoreFactor = minionEliminationScoreFactor;
        this.wizardDamageScoreFactor = wizardDamageScoreFactor;
        this.wizardEliminationScoreFactor = wizardEliminationScoreFactor;
        this.teamWorkingScoreFactor = teamWorkingScoreFactor;
        this.victoryScore = victoryScore;
        this.scoreGainRange = scoreGainRange;
        this.rawMessageMaxLength = rawMessageMaxLength;
        this.rawMessageTransmissionSpeed = rawMessageTransmissionSpeed;
        this.wizardRadius = wizardRadius;
        this.wizardCastRange = wizardCastRange;
        this.wizardVisionRange = wizardVisionRange;
        this.wizardForwardSpeed = wizardForwardSpeed;
        this.wizardBackwardSpeed = wizardBackwardSpeed;
        this.wizardStrafeSpeed = wizardStrafeSpeed;
        this.wizardBaseLife = wizardBaseLife;
        this.wizardLifeGrowthPerLevel = wizardLifeGrowthPerLevel;
        this.wizardBaseMana = wizardBaseMana;
        this.wizardManaGrowthPerLevel = wizardManaGrowthPerLevel;
        this.wizardBaseLifeRegeneration = wizardBaseLifeRegeneration;
        this.wizardLifeRegenerationGrowthPerLevel = wizardLifeRegenerationGrowthPerLevel;
        this.wizardBaseManaRegeneration = wizardBaseManaRegeneration;
        this.wizardManaRegenerationGrowthPerLevel = wizardManaRegenerationGrowthPerLevel;
        this.wizardMaxTurnAngle = wizardMaxTurnAngle;
        this.wizardMaxResurrectionDelayTicks = wizardMaxResurrectionDelayTicks;
        this.wizardMinResurrectionDelayTicks = wizardMinResurrectionDelayTicks;
        this.wizardActionCooldownTicks = wizardActionCooldownTicks;
        this.staffCooldownTicks = staffCooldownTicks;
        this.magicMissileCooldownTicks = magicMissileCooldownTicks;
        this.frostBoltCooldownTicks = frostBoltCooldownTicks;
        this.fireballCooldownTicks = fireballCooldownTicks;
        this.hasteCooldownTicks = hasteCooldownTicks;
        this.shieldCooldownTicks = shieldCooldownTicks;
        this.magicMissileManacost = magicMissileManacost;
        this.frostBoltManacost = frostBoltManacost;
        this.fireballManacost = fireballManacost;
        this.hasteManacost = hasteManacost;
        this.shieldManacost = shieldManacost;
        this.staffDamage = staffDamage;
        this.staffSector = staffSector;
        this.staffRange = staffRange;
        this.levelUpXpValues = Arrays.copyOf(levelUpXpValues, levelUpXpValues.length);
        this.minionRadius = minionRadius;
        this.minionVisionRange = minionVisionRange;
        this.minionSpeed = minionSpeed;
        this.minionMaxTurnAngle = minionMaxTurnAngle;
        this.minionLife = minionLife;
        this.factionMinionAppearanceIntervalTicks = factionMinionAppearanceIntervalTicks;
        this.orcWoodcutterActionCooldownTicks = orcWoodcutterActionCooldownTicks;
        this.orcWoodcutterDamage = orcWoodcutterDamage;
        this.orcWoodcutterAttackSector = orcWoodcutterAttackSector;
        this.orcWoodcutterAttackRange = orcWoodcutterAttackRange;
        this.fetishBlowdartActionCooldownTicks = fetishBlowdartActionCooldownTicks;
        this.fetishBlowdartAttackRange = fetishBlowdartAttackRange;
        this.fetishBlowdartAttackSector = fetishBlowdartAttackSector;
        this.bonusRadius = bonusRadius;
        this.bonusAppearanceIntervalTicks = bonusAppearanceIntervalTicks;
        this.bonusScoreAmount = bonusScoreAmount;
        this.dartRadius = dartRadius;
        this.dartSpeed = dartSpeed;
        this.dartDirectDamage = dartDirectDamage;
        this.magicMissileRadius = magicMissileRadius;
        this.magicMissileSpeed = magicMissileSpeed;
        this.magicMissileDirectDamage = magicMissileDirectDamage;
        this.frostBoltRadius = frostBoltRadius;
        this.frostBoltSpeed = frostBoltSpeed;
        this.frostBoltDirectDamage = frostBoltDirectDamage;
        this.fireballRadius = fireballRadius;
        this.fireballSpeed = fireballSpeed;
        this.fireballExplosionMaxDamageRange = fireballExplosionMaxDamageRange;
        this.fireballExplosionMinDamageRange = fireballExplosionMinDamageRange;
        this.fireballExplosionMaxDamage = fireballExplosionMaxDamage;
        this.fireballExplosionMinDamage = fireballExplosionMinDamage;
        this.guardianTowerRadius = guardianTowerRadius;
        this.guardianTowerVisionRange = guardianTowerVisionRange;
        this.guardianTowerLife = guardianTowerLife;
        this.guardianTowerAttackRange = guardianTowerAttackRange;
        this.guardianTowerDamage = guardianTowerDamage;
        this.guardianTowerCooldownTicks = guardianTowerCooldownTicks;
        this.factionBaseRadius = factionBaseRadius;
        this.factionBaseVisionRange = factionBaseVisionRange;
        this.factionBaseLife = factionBaseLife;
        this.factionBaseAttackRange = factionBaseAttackRange;
        this.factionBaseDamage = factionBaseDamage;
        this.factionBaseCooldownTicks = factionBaseCooldownTicks;
        this.burningDurationTicks = burningDurationTicks;
        this.burningSummaryDamage = burningSummaryDamage;
        this.empoweredDurationTicks = empoweredDurationTicks;
        this.empoweredDamageFactor = empoweredDamageFactor;
        this.frozenDurationTicks = frozenDurationTicks;
        this.hastenedDurationTicks = hastenedDurationTicks;
        this.hastenedBonusDurationFactor = hastenedBonusDurationFactor;
        this.hastenedMovementBonusFactor = hastenedMovementBonusFactor;
        this.hastenedRotationBonusFactor = hastenedRotationBonusFactor;
        this.shieldedDurationTicks = shieldedDurationTicks;
        this.shieldedBonusDurationFactor = shieldedBonusDurationFactor;
        this.shieldedDirectDamageAbsorptionFactor = shieldedDirectDamageAbsorptionFactor;
        this.auraSkillRange = auraSkillRange;
        this.rangeBonusPerSkillLevel = rangeBonusPerSkillLevel;
        this.magicalDamageBonusPerSkillLevel = magicalDamageBonusPerSkillLevel;
        this.staffDamageBonusPerSkillLevel = staffDamageBonusPerSkillLevel;
        this.movementBonusFactorPerSkillLevel = movementBonusFactorPerSkillLevel;
        this.magicalDamageAbsorptionPerSkillLevel = magicalDamageAbsorptionPerSkillLevel;
    }

    /**
     * @return Возвращает некоторое число, которое ваша стратегия может использовать для инициализации генератора
     * случайных чисел. Данное значение имеет рекомендательный характер, однако позволит более точно воспроизводить
     * прошедшие игры.
     */
    public long getRandomSeed() {
        return randomSeed;
    }

    /**
     * @return Возвращает базовую длительность игры в тиках. Реальная длительность может отличаться от этого значения в
     * меньшую сторону. Эквивалентно {@code world.tickCount}.
     */
    public int getTickCount() {
        return tickCount;
    }

    /**
     * @return Возвращает размер (ширину и высоту) карты.
     */
    public double getMapSize() {
        return mapSize;
    }

    /**
     * @return Возвращает {@code true}, если и только если в данной игре волшебники могут повышать свой уровень
     * (накапливая опыт) и изучать новые умения.
     */
    public boolean isSkillsEnabled() {
        return skillsEnabled;
    }

    /**
     * @return Возвращает {@code true}, если и только если верховные волшебники в данной игре могут передавать
     * низкоуровневые сообщения другим волшебникам своей фракции.
     */
    public boolean isRawMessagesEnabled() {
        return rawMessagesEnabled;
    }

    /**
     * @return Возвращает коэффициент урона, наносимого волшебниками одной фракции друг другу в результате
     * дружественного огня.
     * <p>
     * Значение зависит от режима игры, но не может выходить за границы интервала от {@code 0.0} до {@code 1.0}.
     * <p>
     * Вне зависимости от режима игры, волшебники не могут наносить урон союзным миньонам и структурам.
     */
    public double getFriendlyFireDamageFactor() {
        return friendlyFireDamageFactor;
    }

    /**
     * @return Возвращает коэффициент опыта, получаемого волшебником при нанесении урона строениям противоположной
     * фракции.
     */
    public double getBuildingDamageScoreFactor() {
        return buildingDamageScoreFactor;
    }

    /**
     * @return Возвращает коэффициент опыта, получаемого волшебником за разрушение строения противоположной фракции.
     * <p>
     * Применяется к максимальному количеству жизненной энергии строения.
     */
    public double getBuildingEliminationScoreFactor() {
        return buildingEliminationScoreFactor;
    }

    /**
     * @return Возвращает коэффициент опыта, получаемого волшебником при нанесении урона миньонам противоположной
     * фракции.
     */
    public double getMinionDamageScoreFactor() {
        return minionDamageScoreFactor;
    }

    /**
     * @return Возвращает коэффициент опыта, получаемого волшебником за уничтожение миньона другой фракции.
     * <p>
     * Применяется к максимальному количеству жизненной энергии миньона.
     */
    public double getMinionEliminationScoreFactor() {
        return minionEliminationScoreFactor;
    }

    /**
     * @return Возвращает коэффициент опыта, получаемого волшебником при нанесении урона волшебникам противоположной
     * фракции.
     */
    public double getWizardDamageScoreFactor() {
        return wizardDamageScoreFactor;
    }

    /**
     * @return Возвращает коэффициент опыта, получаемого волшебником за разрушение телесной оболочки волшебника
     * противоположной фракции.
     * <p>
     * Применяется к максимальному количеству жизненной энергии волшебника.
     */
    public double getWizardEliminationScoreFactor() {
        return wizardEliminationScoreFactor;
    }

    /**
     * @return Возвращает мультипликатор опыта, применяемый в случае уничтожения юнита противника при участии двух или
     * более волшебников.
     * <p>
     * После применения мультипликатора количество опыта округляется вниз до ближайшего целого значения.
     */
    public double getTeamWorkingScoreFactor() {
        return teamWorkingScoreFactor;
    }

    /**
     * @return Возвращает количество баллов, получаемых всеми игроками фракции в случае победы --- разрушения базы
     * противоположной фракции.
     */
    public int getVictoryScore() {
        return victoryScore;
    }

    /**
     * @return Возвращает максимальное расстояние, на котором волшебник получает опыт при уничтожении союзником юнита
     * другой фракции.
     * <p>
     * При уничтожении противника опыт равномерно распределяется между всеми волшебниками, находящимися на расстоянии от
     * цели, на превышающем {@code scoreGainRange}, а также юнитом, нанёсшим урон, если это тоже волшебник.
     * <p>
     * При нанесении противнику урона, не приводящему к уничтожению юнита, данный параметр не применяется, а опыт
     * полностью достаётся атакующему волшебнику. В случае атаки миньона или строения опыт не достаётся никому.
     * <p>
     * Учитывается расстояние между центрами юнитов.
     */
    public double getScoreGainRange() {
        return scoreGainRange;
    }

    /**
     * @return Возвращает максимально возможную длину низкоуровневого сообщения.
     * <p>
     * Сообщения, длина которых превышает указанное значение, будут проигнорированы.
     */
    public int getRawMessageMaxLength() {
        return rawMessageMaxLength;
    }

    /**
     * @return Возвращает скорость отправки сообщения.
     * <p>
     * Если текстовая часть сообщения пуста, то адресат получит его уже в следующий игровой тик. В противном случае,
     * момент получения сообщения будет отложен на {@code ceil(message.rawMessage.length / rawMessageTransmissionSpeed)}
     * игровых тиков.
     */
    public double getRawMessageTransmissionSpeed() {
        return rawMessageTransmissionSpeed;
    }

    /**
     * @return Возвращает радиус волшебника.
     */
    public double getWizardRadius() {
        return wizardRadius;
    }

    /**
     * @return Возвращает базовую дальность заклинаний волшебника.
     * <p>
     * Эффективная дальность ({@code wizard.castRange}) может быть выше в результате действия некоторых аур и/или
     * изучения волшебником некоторых умений.
     */
    public double getWizardCastRange() {
        return wizardCastRange;
    }

    /**
     * @return Возвращает максимальное расстояние (от центра до центра), на котором волшебник обнаруживает другие
     * объекты.
     */
    public double getWizardVisionRange() {
        return wizardVisionRange;
    }

    /**
     * @return Возвращает базовое ограничение скорости волшебника при движении вперёд.
     * <p>
     * Эффективное ограничение может быть выше в результате действия некоторых аур и/или изучения волшебником некоторых
     * умений, а также в результате действия статуса {@code HASTENED}.
     */
    public double getWizardForwardSpeed() {
        return wizardForwardSpeed;
    }

    /**
     * @return Возвращает базовое ограничение скорости волшебника при движении назад.
     * <p>
     * Эффективное ограничение может быть выше в результате действия некоторых аур и/или изучения волшебником некоторых
     * умений, а также в результате действия статуса {@code HASTENED}.
     */
    public double getWizardBackwardSpeed() {
        return wizardBackwardSpeed;
    }

    /**
     * @return Возвращает базовое ограничение скорости волшебника при движении боком.
     * <p>
     * Эффективное ограничение может быть выше в результате действия некоторых аур и/или изучения волшебником некоторых
     * умений, а также в результате действия статуса {@code HASTENED}.
     */
    public double getWizardStrafeSpeed() {
        return wizardStrafeSpeed;
    }

    /**
     * @return Возвращает максимальное значение жизненной энергии волшебника на уровне {@code 0}.
     */
    public int getWizardBaseLife() {
        return wizardBaseLife;
    }

    /**
     * @return Возвращает прирост жизненной энергии волшебника за уровень.
     */
    public int getWizardLifeGrowthPerLevel() {
        return wizardLifeGrowthPerLevel;
    }

    /**
     * @return Возвращает максимальное значение магической энергии волшебника на уровне {@code 0}.
     */
    public int getWizardBaseMana() {
        return wizardBaseMana;
    }

    /**
     * @return Возвращает прирост магической энергии волшебника за уровень.
     */
    public int getWizardManaGrowthPerLevel() {
        return wizardManaGrowthPerLevel;
    }

    /**
     * @return Возвращает количество жизненной энергии, которое волшебник уровня {@code 0} восстанавливает за один тик.
     */
    public double getWizardBaseLifeRegeneration() {
        return wizardBaseLifeRegeneration;
    }

    /**
     * @return Возвращает прирост скорости регенерации жизненной энергии волшебника за один уровень.
     */
    public double getWizardLifeRegenerationGrowthPerLevel() {
        return wizardLifeRegenerationGrowthPerLevel;
    }

    /**
     * @return Возвращает количество магической энергии, которое волшебник уровня {@code 0} восстанавливает за один тик.
     */
    public double getWizardBaseManaRegeneration() {
        return wizardBaseManaRegeneration;
    }

    /**
     * @return Возвращает прирост скорости регенерации магической энергии волшебника за один уровень.
     */
    public double getWizardManaRegenerationGrowthPerLevel() {
        return wizardManaRegenerationGrowthPerLevel;
    }

    /**
     * @return Возвращает базовое ограничение на изменение угла поворота волшебника за один тик.
     * <p>
     * Эффективное ограничение может быть выше в {@code 1.0 + hastenedRotationBonusFactor} раз в результате действия
     * статуса {@code HASTENED}.
     */
    public double getWizardMaxTurnAngle() {
        return wizardMaxTurnAngle;
    }

    /**
     * @return Возвращает максимально возможную задержку возрождения волшебника после смерти его телесной оболочки.
     * <p>
     * Если волшебник погибает сразу после своего возрождения, то он будет автоматически воскрешён на своей начальной
     * позиции (или недалеко от неё, если это невозможно) через {@code wizardMaxResurrectionDelayTicks} тиков. Каждый
     * игровой тик жизни волшебника уменьшшает эту задержку на единицу. Задержка возрождения не может стать меньше, чем
     * {@code wizardMinResurrectionDelayTicks}.
     */
    public int getWizardMaxResurrectionDelayTicks() {
        return wizardMaxResurrectionDelayTicks;
    }

    /**
     * @return Возвращает минимально возможную задержку возрождения волшебника после смерти его телесной оболочки.
     * <p>
     * Если волшебник погибает сразу после своего возрождения, то он будет автоматически воскрешён на своей начальной
     * позиции (или недалеко от неё, если это невозможно) через {@code wizardMaxResurrectionDelayTicks} тиков. Каждый
     * игровой тик жизни волшебника уменьшшает эту задержку на единицу. Задержка возрождения не может стать меньше, чем
     * {@code wizardMinResurrectionDelayTicks}.
     */
    public int getWizardMinResurrectionDelayTicks() {
        return wizardMinResurrectionDelayTicks;
    }

    /**
     * @return Возвращает минимально возможную задержку между любыми двумя последовательными действиями волшебника.
     */
    public int getWizardActionCooldownTicks() {
        return wizardActionCooldownTicks;
    }

    /**
     * @return Возвращает минимально возможную задержку между двумя последовательными ударами посохом.
     */
    public int getStaffCooldownTicks() {
        return staffCooldownTicks;
    }

    /**
     * @return Возвращает минимально возможную задержку между двумя последовательными заклинаниями <<Магическая
     * ракета>>.
     */
    public int getMagicMissileCooldownTicks() {
        return magicMissileCooldownTicks;
    }

    /**
     * @return Возвращает минимально возможную задержку между двумя последовательными заклинаниями <<Ледяная стрела>>.
     */
    public int getFrostBoltCooldownTicks() {
        return frostBoltCooldownTicks;
    }

    /**
     * @return Возвращает минимально возможную задержку между двумя последовательными заклинаниями <<Огненный шар>>.
     */
    public int getFireballCooldownTicks() {
        return fireballCooldownTicks;
    }

    /**
     * @return Возвращает минимально возможную задержку между двумя последовательными заклинаниями <<Ускорение>>.
     */
    public int getHasteCooldownTicks() {
        return hasteCooldownTicks;
    }

    /**
     * @return Возвращает минимально возможную задержку между двумя последовательными заклинаниями <<Щит>>.
     */
    public int getShieldCooldownTicks() {
        return shieldCooldownTicks;
    }

    /**
     * @return Возвращает количество магической энергии, требуемой для заклинания <<Магическая ракета>>.
     */
    public int getMagicMissileManacost() {
        return magicMissileManacost;
    }

    /**
     * @return Возвращает количество магической энергии, требуемой для заклинания <<Ледяная стрела>>.
     */
    public int getFrostBoltManacost() {
        return frostBoltManacost;
    }

    /**
     * @return Возвращает количество магической энергии, требуемой для заклинания <<Огненный шар>>.
     */
    public int getFireballManacost() {
        return fireballManacost;
    }

    /**
     * @return Возвращает количество магической энергии, требуемой для заклинания <<Ускорение>>.
     */
    public int getHasteManacost() {
        return hasteManacost;
    }

    /**
     * @return Возвращает количество магической энергии, требуемой для заклинания <<Щит>>.
     */
    public int getShieldManacost() {
        return shieldManacost;
    }

    /**
     * @return Возвращает базовый урон удара посохом.
     * <p>
     * Эффективный урон может быть выше в результате действия некоторых аур и/или изучения волшебником некоторых
     * умений.
     */
    public int getStaffDamage() {
        return staffDamage;
    }

    /**
     * @return Возвращает сектор действия посоха волшебника.
     * <p>
     * Атака посохом поражает все живые объекты в секторе от {@code -staffSector / 2.0} до {@code staffSector / 2.0}.
     * Этим же интервалом ограничены относительный угол снаряда, а также зона применения магического статуса.
     */
    public double getStaffSector() {
        return staffSector;
    }

    /**
     * @return Возвращает дальность действия посоха волшебника.
     * <p>
     * Атака посохом поражает все живые объекты, для каждого из которых верно, что расстояние от его центра до центра
     * волшебника не превышает значение {@code staffRange + livingUnit.radius}.
     */
    public double getStaffRange() {
        return staffRange;
    }

    /**
     * @return Возвращает последовательность натуральных чисел.
     * <p>
     * Количество чисел равно количеству уровней, которые волшебник может получить в данном режиме игры. Значение с
     * индексом {@code N} определяет количество опыта, которое необходимо набрать волшебнику уровня {@code N} для
     * получения следующего уровня. Таким образом, количество опыта, необходимое волшебнику начального уровня для
     * получения уровня {@code N}, равно сумме первых {@code N} элементов.
     */
    public int[] getLevelUpXpValues() {
        return levelUpXpValues.length == 0 ? levelUpXpValues : Arrays.copyOf(levelUpXpValues, levelUpXpValues.length);
    }

    /**
     * @return Возвращает радиус миньона.
     */
    public double getMinionRadius() {
        return minionRadius;
    }

    /**
     * @return Возвращает максимальное расстояние (от центра до центра), на котором миньон обнаруживает другие
     * объекты.
     */
    public double getMinionVisionRange() {
        return minionVisionRange;
    }

    /**
     * @return Возвращает скорость миньона при движении вперёд.
     * <p>
     * Миньонам недоступно использование других видов движения, а также перемещение со скоростью, отличной от указанной.
     */
    public double getMinionSpeed() {
        return minionSpeed;
    }

    /**
     * @return Возвращает ограничение на изменение угла поворота миньона за один тик.
     */
    public double getMinionMaxTurnAngle() {
        return minionMaxTurnAngle;
    }

    /**
     * @return Возвращает максимальное значение жизненной энергии миньона.
     */
    public int getMinionLife() {
        return minionLife;
    }

    /**
     * @return Возвращает интервал, с которым появляются миньоны двух противостоящих фракций ({@code ACADEMY} и
     * {@code RENEGADES}).
     * <p>
     * Миньоны каждой из этих фракций появляются тремя группами (по одной на дорожку) недалеко от своей базы. Группа
     * состоит и трёх орков и одного фетиша. Сразу после появления миньоны начинают продвижение по своей дорожке в
     * сторону базы противоположной фракции, при этом атакуя всех противников на своём пути.
     */
    public int getFactionMinionAppearanceIntervalTicks() {
        return factionMinionAppearanceIntervalTicks;
    }

    /**
     * @return Возвращает минимально возможную задержку между двумя последовательными атаками орка-дровосека.
     */
    public int getOrcWoodcutterActionCooldownTicks() {
        return orcWoodcutterActionCooldownTicks;
    }

    /**
     * @return Возвращает урон одной атаки орка-дровосека.
     */
    public int getOrcWoodcutterDamage() {
        return orcWoodcutterDamage;
    }

    /**
     * @return Возвращает сектор действия топора орка.
     * <p>
     * Атака топором поражает все живые объекты в секторе от {@code -orcWoodcutterAttackSector / 2.0} до
     * {@code orcWoodcutterAttackSector / 2.0}.
     */
    public double getOrcWoodcutterAttackSector() {
        return orcWoodcutterAttackSector;
    }

    /**
     * @return Возвращает дальность действия топора орка.
     * <p>
     * Атака топором поражает все живые объекты, для каждого из которых верно, что расстояние от его центра до центра
     * орка-дровосека не превышает значение {@code orcWoodcutterAttackRange + livingUnit.radius}.
     */
    public double getOrcWoodcutterAttackRange() {
        return orcWoodcutterAttackRange;
    }

    /**
     * @return Возвращает минимально возможную задержку между двумя последовательными атаками фетиша.
     */
    public int getFetishBlowdartActionCooldownTicks() {
        return fetishBlowdartActionCooldownTicks;
    }

    /**
     * @return Возвращает дальность полёта дротика, выпущенного фетишем.
     */
    public double getFetishBlowdartAttackRange() {
        return fetishBlowdartAttackRange;
    }

    /**
     * @return Возвращает сектор метания дротика фетишем.
     * <p>
     * Угол полёта дротика относительно направления фетиша ограничен интервалом от
     * {@code -fetishBlowdartAttackSector / 2.0} до {@code fetishBlowdartAttackSector / 2.0}.
     */
    public double getFetishBlowdartAttackSector() {
        return fetishBlowdartAttackSector;
    }

    /**
     * @return Возвращает радиус бонуса.
     */
    public double getBonusRadius() {
        return bonusRadius;
    }

    /**
     * @return Возвращает интервал появления бонусов.
     * <p>
     * Каждый раз по прошествии указанного интервала симулятор игры проверяет количество бонусов на карте и создаёт
     * новый бонус, если оно равно нулю. Бонус создаётся в случайно выбранной точке из двух возможных:
     * ({@code mapSize * 0.3}, {@code mapSize * 0.3}) и ({@code mapSize * 0.7}, {@code mapSize * 0.7}). Если любая часть
     * области появления бонуса уже занята волшебником, то симулятор попытается создать бонус в другой точке из списка.
     * В случае неудачи создание бонуса не произойдёт вообще и будет отложено до окончания очередного интервала.
     */
    public int getBonusAppearanceIntervalTicks() {
        return bonusAppearanceIntervalTicks;
    }

    /**
     * @return Возвращает количество баллов, начисляемых игроку, волшебник которого подробрал бонус.
     * <p>
     * Сам волшебник получает такое же количество опыта.
     */
    public int getBonusScoreAmount() {
        return bonusScoreAmount;
    }

    /**
     * @return Возвращает радиус дротика.
     */
    public double getDartRadius() {
        return dartRadius;
    }

    /**
     * @return Возвращает скорость полёта дротика.
     */
    public double getDartSpeed() {
        return dartSpeed;
    }

    /**
     * @return Возвращает урон дротика.
     */
    public int getDartDirectDamage() {
        return dartDirectDamage;
    }

    /**
     * @return Возвращает радиус <<Магической ракеты>>.
     */
    public double getMagicMissileRadius() {
        return magicMissileRadius;
    }

    /**
     * @return Возвращает скорость полёта <<Магической ракеты>>.
     */
    public double getMagicMissileSpeed() {
        return magicMissileSpeed;
    }

    /**
     * @return Возвращает урон <<Магической ракеты>>.
     */
    public int getMagicMissileDirectDamage() {
        return magicMissileDirectDamage;
    }

    /**
     * @return Возвращает радиус <<Ледяной стрелы>>.
     */
    public double getFrostBoltRadius() {
        return frostBoltRadius;
    }

    /**
     * @return Возвращает скорость полёта <<Ледяной стрелы>>.
     */
    public double getFrostBoltSpeed() {
        return frostBoltSpeed;
    }

    /**
     * @return Возвращает урон <<Ледяной стрелы>>.
     */
    public int getFrostBoltDirectDamage() {
        return frostBoltDirectDamage;
    }

    /**
     * @return Возвращает радиус <<Огненного шара>>.
     */
    public double getFireballRadius() {
        return fireballRadius;
    }

    /**
     * @return Возвращает скорость полёта <<Огненного шара>>.
     */
    public double getFireballSpeed() {
        return fireballSpeed;
    }

    /**
     * @return Возвращает радиус области, в которой живые юниты получают максимальный урон от взрыва <<Огненного шара>>.
     * @see #getFireballExplosionMaxDamage()
     */
    public double getFireballExplosionMaxDamageRange() {
        return fireballExplosionMaxDamageRange;
    }

    /**
     * @return Возвращает радиус области, в которой живые юниты получают какой-либо урон от взрыва <<Огненного шара>>.
     * @see #getFireballExplosionMaxDamage()
     */
    public double getFireballExplosionMinDamageRange() {
        return fireballExplosionMinDamageRange;
    }

    /**
     * @return Возвращает урон <<Огненного шара>> в эпицентре взрыва.
     * <p>
     * Живой юнит получает {@code fireballExplosionMaxDamage} единиц урона, если расстояние от центра взрыва до
     * ближайшей точки этого юнита не превышает {@code fireballExplosionMaxDamageRange}. По мере увеличения расстояния
     * до {@code fireballExplosionMinDamageRange}, урон <<Огненного шара>> равномерно снижается и достигает
     * {@code fireballExplosionMinDamage}. Если расстояние от центра взрыва до ближайшей точки живого юнита превышает
     * {@code fireballExplosionMinDamageRange}, то урон ему не наносится.
     * <p>
     * Если живой юнит получил какой-либо урон от взрыва <<Огненного шара>>, то он загорается ({@code BURNING}).
     */
    public int getFireballExplosionMaxDamage() {
        return fireballExplosionMaxDamage;
    }

    /**
     * @return Возвращает урон <<Огненного шара>> на периферии взрыва.
     * @see #getFireballExplosionMaxDamage()
     */
    public int getFireballExplosionMinDamage() {
        return fireballExplosionMinDamage;
    }

    /**
     * @return Возвращает радиус охранной башни.
     */
    public double getGuardianTowerRadius() {
        return guardianTowerRadius;
    }

    /**
     * @return Возвращает максимальное расстояние (от центра до центра), на котором охранная башня обнаруживает другие
     * объекты.
     */
    public double getGuardianTowerVisionRange() {
        return guardianTowerVisionRange;
    }

    /**
     * @return Возвращает начальное значение жизненной энергии охранной башни.
     */
    public double getGuardianTowerLife() {
        return guardianTowerLife;
    }

    /**
     * @return Возвращает максимальное расстояние (от центра до центра), на котором охранная башня может атаковать
     * другие объекты.
     */
    public double getGuardianTowerAttackRange() {
        return guardianTowerAttackRange;
    }

    /**
     * @return Возвращает урон одной атаки охранной башни.
     */
    public int getGuardianTowerDamage() {
        return guardianTowerDamage;
    }

    /**
     * @return Возвращает минимально возможную задержку между двумя последовательными атаками охранной башни.
     */
    public int getGuardianTowerCooldownTicks() {
        return guardianTowerCooldownTicks;
    }

    /**
     * @return Возвращает радиус базы фракции.
     */
    public double getFactionBaseRadius() {
        return factionBaseRadius;
    }

    /**
     * @return Возвращает максимальное расстояние (от центра до центра), на котором база фракции обнаруживает другие
     * объекты.
     */
    public double getFactionBaseVisionRange() {
        return factionBaseVisionRange;
    }

    /**
     * @return Возвращает начальное значение жизненной энергии базы фракции.
     */
    public double getFactionBaseLife() {
        return factionBaseLife;
    }

    /**
     * @return Возвращает максимальное расстояние (от центра до центра), на котором база фракции может атаковать
     * другие объекты.
     */
    public double getFactionBaseAttackRange() {
        return factionBaseAttackRange;
    }

    /**
     * @return Возвращает урон одной атаки базы фракции.
     */
    public int getFactionBaseDamage() {
        return factionBaseDamage;
    }

    /**
     * @return Возвращает минимально возможную задержку между двумя последовательными атаками базы фракции.
     */
    public int getFactionBaseCooldownTicks() {
        return factionBaseCooldownTicks;
    }

    /**
     * @return Возвращает длительность действия статуса {@code BURNING}.
     */
    public int getBurningDurationTicks() {
        return burningDurationTicks;
    }

    /**
     * @return Возвращает суммарный урон, получаемый живым юнитом за время действия статуса {@code BURNING}.
     */
    public int getBurningSummaryDamage() {
        return burningSummaryDamage;
    }

    /**
     * @return Возвращает длительность действия статуса {@code EMPOWERED}.
     */
    public int getEmpoweredDurationTicks() {
        return empoweredDurationTicks;
    }

    /**
     * @return Возвращает мультипликатор урона, наносимого живым юнитом под действием статуса {@code EMPOWERED}.
     * <p>
     * Мультипликатор применяется к ударам в ближнем бою, прямым попаданиям снарядов, а также взрыву <<Огненного шара>>,
     * но не применяется к урону, получаемому от статусов.
     */
    public double getEmpoweredDamageFactor() {
        return empoweredDamageFactor;
    }

    /**
     * @return Возвращает длительность действия статуса {@code FROZEN}.
     */
    public int getFrozenDurationTicks() {
        return frozenDurationTicks;
    }

    /**
     * @return Возвращает длительность действия статуса {@code HASTENED}.
     */
    public int getHastenedDurationTicks() {
        return hastenedDurationTicks;
    }

    /**
     * @return Возвращает мультилпикатор длительности действия статуса {@code HASTENED} в случае подбора бонуса.
     */
    public double getHastenedBonusDurationFactor() {
        return hastenedBonusDurationFactor;
    }

    /**
     * @return Возвращает относительное увеличение скорости перемещения в результате дествия статуса {@code HASTENED}.
     * <p>
     * Увеличение скорости от действия статуса {@code HASTENED} и увеличение скорости в результате изучения умений,
     * являющихся пререквизитами умения {@code HASTE}, являются аддитивными. Таким образом, максимальное значение
     * скорости волшебника составляет
     * {@code 1.0 + 4.0 * movementBonusFactorPerSkillLevel + hastenedMovementBonusFactor} от базовой.
     */
    public double getHastenedMovementBonusFactor() {
        return hastenedMovementBonusFactor;
    }

    /**
     * @return Возвращает относительное увеличение скорости поворота в результате дествия статуса {@code HASTENED}.
     */
    public double getHastenedRotationBonusFactor() {
        return hastenedRotationBonusFactor;
    }

    /**
     * @return Возвращает длительность действия статуса {@code SHIELDED}.
     */
    public int getShieldedDurationTicks() {
        return shieldedDurationTicks;
    }

    /**
     * @return Возвращает мультилпикатор длительности действия статуса {@code SHIELDED} в случае подбора бонуса.
     */
    public double getShieldedBonusDurationFactor() {
        return shieldedBonusDurationFactor;
    }

    /**
     * @return Возвращает часть урона, поглощаемую щитом.
     * <p>
     * Снижение урона применяется к ударам в ближнем бою, прямым попаданиям снарядов, а также взрыву <<Огненного шара>>,
     * но не применяется к урону, получаемому от статусов.
     */
    public double getShieldedDirectDamageAbsorptionFactor() {
        return shieldedDirectDamageAbsorptionFactor;
    }

    /**
     * @return Возвращает дальность действия аур.
     */
    public double getAuraSkillRange() {
        return auraSkillRange;
    }

    /**
     * @return Возвращает абсолютное увеличение дальности заклинаний волшебника за каждое последовательное изучение
     * умения, являющегося одним из пререквизитов умения {@code ADVANCED_MAGIC_MISSILE}.
     */
    public double getRangeBonusPerSkillLevel() {
        return rangeBonusPerSkillLevel;
    }

    /**
     * @return Возвращает абсолютное увеличение урона, наносимого волшебником в результате прямых попаданий магических
     * снарядов и взрыва <<Огненного шара>>, за каждое последовательное изучение умения, являющегося одним из
     * пререквизитов умения {@code FROST_BOLT}.
     */
    public int getMagicalDamageBonusPerSkillLevel() {
        return magicalDamageBonusPerSkillLevel;
    }

    /**
     * @return Возвращает абсолютное увеличение урона, наносимого волшебником в ближнем бою, за каждое последовательное
     * изучение умения, являющегося одним из пререквизитов умения {@code FIREBALL}.
     */
    public int getStaffDamageBonusPerSkillLevel() {
        return staffDamageBonusPerSkillLevel;
    }

    /**
     * @return Возвращает относительное увеличение скорости перемещения за каждое последовательное изучение умения,
     * являющегося одним из пререквизитов умения {@code HASTE}.
     * <p>
     * Увеличение скорости от действия статуса {@code HASTENED} и увеличение скорости в результате изучения умений,
     * являющихся пререквизитами умения {@code HASTE}, являются аддитивными. Таким образом, максимальное значение
     * скорости волшебника составляет
     * {@code 1.0 + 4.0 * movementBonusFactorPerSkillLevel + hastenedMovementBonusFactor} от базовой.
     */
    public double getMovementBonusFactorPerSkillLevel() {
        return movementBonusFactorPerSkillLevel;
    }

    /**
     * @return Возвращает абсолютное уменьшение урона, получаемого волшебником в результате прямых попаданий магических
     * снарядов, взрыва <<Огненного шара>> и атак строений, за каждое последовательное изучение умения, являющегося
     * одним из пререквизитов умения {@code SHIELD}.
     */
    public int getMagicalDamageAbsorptionPerSkillLevel() {
        return magicalDamageAbsorptionPerSkillLevel;
    }
}
