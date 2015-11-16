package model;

import java.util.Arrays;

/**
 * Предоставляет доступ к различным игровым константам.
 */
public class Game {
    private final long randomSeed;

    private final int tickCount;

    private final int worldWidth;
    private final int worldHeight;

    private final double trackTileSize;
    private final double trackTileMargin;

    private final int lapCount;
    private final int lapTickCount;

    private final int initialFreezeDurationTicks;
    private final double burningTimeDurationFactor;

    private final int[] finishTrackScores;
    private final int finishLapScore;
    private final double lapWaypointsSummaryScoreFactor;
    private final double carDamageScoreFactor;
    private final int carEliminationScore;

    private final double carWidth;
    private final double carHeight;

    private final double carEnginePowerChangePerTick;
    private final double carWheelTurnChangePerTick;

    private final double carAngularSpeedFactor;

    private final double carMovementAirFrictionFactor;
    private final double carRotationAirFrictionFactor;

    private final double carLengthwiseMovementFrictionFactor;
    private final double carCrosswiseMovementFrictionFactor;
    private final double carRotationFrictionFactor;

    private final int throwProjectileCooldownTicks;
    private final int useNitroCooldownTicks;
    private final int spillOilCooldownTicks;

    private final double nitroEnginePowerFactor;
    private final int nitroDurationTicks;

    private final int carReactivationTimeTicks;

    private final double buggyMass;
    private final double buggyEngineForwardPower;
    private final double buggyEngineRearPower;

    private final double jeepMass;
    private final double jeepEngineForwardPower;
    private final double jeepEngineRearPower;

    private final double bonusSize;
    private final double bonusMass;

    private final int pureScoreAmount;

    private final double washerRadius;
    private final double washerMass;
    private final double washerInitialSpeed;
    private final double washerDamage;

    private final double sideWasherAngle;

    private final double tireRadius;
    private final double tireMass;
    private final double tireInitialSpeed;
    private final double tireDamageFactor;

    private final double tireDisappearSpeedFactor;

    private final double oilSlickInitialRange;
    private final double oilSlickRadius;
    private final int oilSlickLifetime;
    private final int maxOiledStateDurationTicks;

    public Game(
            long randomSeed, int tickCount, int worldWidth, int worldHeight, double trackTileSize,
            double trackTileMargin, int lapCount, int lapTickCount, int initialFreezeDurationTicks,
            double burningTimeDurationFactor, int[] finishTrackScores, int finishLapScore,
            double lapWaypointsSummaryScoreFactor, double carDamageScoreFactor, int carEliminationScore,
            double carWidth, double carHeight, double carEnginePowerChangePerTick, double carWheelTurnChangePerTick,
            double carAngularSpeedFactor, double carMovementAirFrictionFactor, double carRotationAirFrictionFactor,
            double carLengthwiseMovementFrictionFactor, double carCrosswiseMovementFrictionFactor,
            double carRotationFrictionFactor, int throwProjectileCooldownTicks, int useNitroCooldownTicks,
            int spillOilCooldownTicks, double nitroEnginePowerFactor, int nitroDurationTicks,
            int carReactivationTimeTicks, double buggyMass, double buggyEngineForwardPower, double buggyEngineRearPower,
            double jeepMass, double jeepEngineForwardPower, double jeepEngineRearPower, double bonusSize,
            double bonusMass, int pureScoreAmount, double washerRadius, double washerMass, double washerInitialSpeed,
            double washerDamage, double sideWasherAngle, double tireRadius, double tireMass, double tireInitialSpeed,
            double tireDamageFactor, double tireDisappearSpeedFactor, double oilSlickInitialRange,
            double oilSlickRadius, int oilSlickLifetime, int maxOiledStateDurationTicks) {
        this.randomSeed = randomSeed;
        this.tickCount = tickCount;
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        this.trackTileSize = trackTileSize;
        this.trackTileMargin = trackTileMargin;
        this.lapCount = lapCount;
        this.lapTickCount = lapTickCount;
        this.initialFreezeDurationTicks = initialFreezeDurationTicks;
        this.burningTimeDurationFactor = burningTimeDurationFactor;
        this.carRotationFrictionFactor = carRotationFrictionFactor;
        this.throwProjectileCooldownTicks = throwProjectileCooldownTicks;
        this.useNitroCooldownTicks = useNitroCooldownTicks;
        this.spillOilCooldownTicks = spillOilCooldownTicks;
        this.nitroEnginePowerFactor = nitroEnginePowerFactor;
        this.nitroDurationTicks = nitroDurationTicks;
        this.carReactivationTimeTicks = carReactivationTimeTicks;
        this.buggyMass = buggyMass;
        this.buggyEngineForwardPower = buggyEngineForwardPower;
        this.buggyEngineRearPower = buggyEngineRearPower;
        this.jeepMass = jeepMass;
        this.jeepEngineForwardPower = jeepEngineForwardPower;
        this.jeepEngineRearPower = jeepEngineRearPower;
        this.bonusSize = bonusSize;
        this.bonusMass = bonusMass;
        this.pureScoreAmount = pureScoreAmount;
        this.washerRadius = washerRadius;
        this.washerMass = washerMass;
        this.washerInitialSpeed = washerInitialSpeed;
        this.washerDamage = washerDamage;
        this.sideWasherAngle = sideWasherAngle;
        this.tireRadius = tireRadius;
        this.tireMass = tireMass;
        this.tireInitialSpeed = tireInitialSpeed;
        this.tireDamageFactor = tireDamageFactor;
        this.tireDisappearSpeedFactor = tireDisappearSpeedFactor;
        this.oilSlickInitialRange = oilSlickInitialRange;
        this.oilSlickRadius = oilSlickRadius;
        this.oilSlickLifetime = oilSlickLifetime;
        this.maxOiledStateDurationTicks = maxOiledStateDurationTicks;
        this.finishTrackScores = Arrays.copyOf(finishTrackScores, finishTrackScores.length);
        this.finishLapScore = finishLapScore;
        this.lapWaypointsSummaryScoreFactor = lapWaypointsSummaryScoreFactor;
        this.carDamageScoreFactor = carDamageScoreFactor;
        this.carEliminationScore = carEliminationScore;
        this.carWidth = carWidth;
        this.carHeight = carHeight;
        this.carEnginePowerChangePerTick = carEnginePowerChangePerTick;
        this.carWheelTurnChangePerTick = carWheelTurnChangePerTick;
        this.carAngularSpeedFactor = carAngularSpeedFactor;
        this.carMovementAirFrictionFactor = carMovementAirFrictionFactor;
        this.carRotationAirFrictionFactor = carRotationAirFrictionFactor;
        this.carLengthwiseMovementFrictionFactor = carLengthwiseMovementFrictionFactor;
        this.carCrosswiseMovementFrictionFactor = carCrosswiseMovementFrictionFactor;
    }

    /**
     * @return Возвращает некоторое число, которое ваша стратегия может использовать для инициализации генератора
     * случайных чисел. Данное значение имеет рекомендательный характер, однако позволит более точно
     * воспроизводить прошедшие игры.
     */
    public long getRandomSeed() {
        return randomSeed;
    }

    /**
     * @return Возвращает базовую длительность игры в тиках.
     * Реальная длительность может отличаться от этого значения в меньшую сторону.
     * Поле может быть определено как {@code game.initialFreezeDurationTicks + game.lapCount * game.lapTickCount}.
     * Значение поля не меняется в процессе игры. Эквивалентно {@code world.tickCount}.
     */
    public int getTickCount() {
        return tickCount;
    }

    /**
     * @return Возвращает ширину игрового мира в тайлах.
     */
    public int getWorldWidth() {
        return worldWidth;
    }

    /**
     * @return Возвращает высоту игрового мира в тайлах.
     */
    public int getWorldHeight() {
        return worldHeight;
    }

    /**
     * @return Возвращает размер (ширину и высоту) одного тайла.
     */
    public double getTrackTileSize() {
        return trackTileSize;
    }

    /**
     * @return Возвращает отступ от границы тайла до границы прямого участка трассы, проходящего через этот тайл.
     * Радиусы всех закруглённых сочленений участков трассы также равны этому значению.
     */
    public double getTrackTileMargin() {
        return trackTileMargin;
    }

    /**
     * @return Возвращает количество кругов (циклов прохождения списка ключевых точек {@code world.waypoints}),
     * которое необходимо пройти для завершения трассы.
     */
    public int getLapCount() {
        return lapCount;
    }

    /**
     * @return Возвращает количество тиков, которое выделяется кодемобилям на прохождение одного круга.
     * Значение является составной частью выражения для нахождения базовой длительности игры ({@code game.tickCount}) и
     * не используется в целях ограничения на прохождение одного отдельного круга.
     */
    public int getLapTickCount() {
        return lapTickCount;
    }

    /**
     * @return Возвращает количество тиков в начале игры, в течение которых кодемобиль не может изменять своё положение.
     * Значение является составной частью выражения для нахождения базовой длительности игры ({@code game.tickCount}).
     */
    public int getInitialFreezeDurationTicks() {
        return initialFreezeDurationTicks;
    }

    /**
     * @return Возвращает коэффициент, определяющий количество тиков до завершения игры после финиширования трассы
     * очередным кодемобилем. Для получения более подробной информации смотрите документацию к
     * {@code world.lastTickIndex}.
     */
    public double getBurningTimeDurationFactor() {
        return burningTimeDurationFactor;
    }

    /**
     * @return Возвращает 0-индексированный массив, содержащий количество баллов, зарабатываемых кодемобилями при
     * завершении трассы. Кодемобиль, финишировавший первым, приносит владельцу {@code finishTrackScores[0]} баллов,
     * вторым --- {@code finishTrackScores[1]} и так далее.
     */
    public int[] getFinishTrackScores() {
        return Arrays.copyOf(finishTrackScores, finishTrackScores.length);
    }

    /**
     * @return Возвращает количество баллов, зарабатываемых кодемобилем при прохождении одного круга.
     * Баллы начисляются не единовременно, а постепенно, по мере прохождения ключевых точек.
     */
    public int getFinishLapScore() {
        return finishLapScore;
    }

    /**
     * @return Возвращает долю от баллов за круг ({@code game.finishLapScore}), которую кодемобиль заработает при
     * прохождении всех ключевых точек круга, кроме последней. Баллы равномерно распределены по ключевым точкам.
     */
    public double getLapWaypointsSummaryScoreFactor() {
        return lapWaypointsSummaryScoreFactor;
    }

    /**
     * @return Возвращает количество баллов, зарабатываемых кодемобилем при нанесении {@code 1.0} урона кодемобилю
     * другого игрока. При нанесении меньшего урона количество баллов пропорционально падает. Результат всегда
     * округляется в меньшую сторону.
     */
    public double getCarDamageScoreFactor() {
        return carDamageScoreFactor;
    }

    /**
     * @return Возвращает количество баллов, зарабатываемых кодемобилем при уничтожении кодемобиля другого игрока.
     */
    public int getCarEliminationScore() {
        return carEliminationScore;
    }

    /**
     * @return Возвращает ширину кодемобиля.
     */
    public double getCarWidth() {
        return carWidth;
    }

    /**
     * @return Возвращает высоту кодемобиля.
     */
    public double getCarHeight() {
        return carHeight;
    }

    /**
     * @return Возвращает максимальное значение, на которое может измениться относительная мощность двигателя кодемобиля
     * ({@code car.enginePower}) за один тик.
     */
    public double getCarEnginePowerChangePerTick() {
        return carEnginePowerChangePerTick;
    }

    /**
     * @return Возвращает максимальное значение, на которое может измениться относительный угол поворота колёс
     * кодемобиля ({@code car.wheelTurn}) за один тик.
     */
    public double getCarWheelTurnChangePerTick() {
        return carWheelTurnChangePerTick;
    }

    /**
     * @return Возвращает коэффициент, используемый для вычисления составляющей угловой скорости кодемобиля, порождаемой
     * движением кодемобиля при ненулевом относительном угле поворота колёс. Для получения более подробной информации
     * смотрите документацию к {@code move.wheelTurn}.
     */
    public double getCarAngularSpeedFactor() {
        return carAngularSpeedFactor;
    }

    /**
     * @return Возвращает относительную потерю модуля скорости кодемобиля за один тик.
     */
    public double getCarMovementAirFrictionFactor() {
        return carMovementAirFrictionFactor;
    }

    /**
     * @return Возвращает относительную потерю модуля угловой скорости кодемобиля за один тик. Относительная потеря
     * применяется только к составляющей угловой скорости кодемобиля, не порождаемой движением кодемобиля при ненулевом
     * относительном угле поворота колёс. Для получения более подробной информации смотрите документацию к
     * {@code move.wheelTurn}.
     */
    public double getCarRotationAirFrictionFactor() {
        return carRotationAirFrictionFactor;
    }

    /**
     * @return Возвращает абсолютную потерю составляющей скорости кодемобиля, направленной вдоль продольной оси
     * кодемобиля, за один тик.
     */
    public double getCarLengthwiseMovementFrictionFactor() {
        return carLengthwiseMovementFrictionFactor;
    }

    /**
     * @return Возвращает абсолютную потерю составляющей скорости кодемобиля, направленной вдоль поперечной оси
     * кодемобиля, за один тик.
     */
    public double getCarCrosswiseMovementFrictionFactor() {
        return carCrosswiseMovementFrictionFactor;
    }

    /**
     * @return Возвращает абсолютную потерю модуля угловой скорости кодемобиля за один тик. Абсолютная потеря
     * применяется только к составляющей угловой скорости кодемобиля, не порождаемой движением кодемобиля при ненулевом
     * относительном угле поворота колёс. Для получения более подробной информации смотрите документацию к
     * {@code move.wheelTurn}.
     */
    public double getCarRotationFrictionFactor() {
        return carRotationFrictionFactor;
    }

    /**
     * @return Возвращает длительность задержки в тиках, применяемой к кодемобилю после метания им снаряда.
     * В течение этого времени кодемобиль не может метать новые снаряды.
     */
    public int getThrowProjectileCooldownTicks() {
        return throwProjectileCooldownTicks;
    }

    /**
     * @return Возвращает длительность задержки в тиках, применяемой к кодемобилю после использования им ускорения
     * <<нитро>>. В течение этого времени кодемобиль не может повторно использовать систему закиси азота.
     */
    public int getUseNitroCooldownTicks() {
        return useNitroCooldownTicks;
    }

    /**
     * @return Возвращает длительность задержки в тиках, применяемой к кодемобилю после использования им канистры с
     * мазутом. В течение этого времени кодемобиль не может разлить ещё одну канистру.
     */
    public int getSpillOilCooldownTicks() {
        return spillOilCooldownTicks;
    }

    /**
     * @return Возвращает относительную мощность двигателя кодемобиля, мгновенно устанавливаемую при использовании
     * системы закиси азота для ускорения кодемобиля.
     */
    public double getNitroEnginePowerFactor() {
        return nitroEnginePowerFactor;
    }

    /**
     * @return Возвращает длительность ускорения <<нитро>> в тиках.
     */
    public int getNitroDurationTicks() {
        return nitroDurationTicks;
    }

    /**
     * @return Возвращает длительность интервала в тиках, по прошествии которого сильно повреждённый кодемобиль
     * (значение {@code car.durability} равно нулю) будет восстановлен.
     */
    public int getCarReactivationTimeTicks() {
        return carReactivationTimeTicks;
    }

    /**
     * @return Возвращает массу кодемобиля типа багги ({@code CarType.BUGGY}).
     */
    public double getBuggyMass() {
        return buggyMass;
    }

    /**
     * @return Возвращает максимальную мощность двигателя кодемобиля типа багги ({@code CarType.BUGGY}) в направлении,
     * совпадающем с направлением кодемобиля.
     */
    public double getBuggyEngineForwardPower() {
        return buggyEngineForwardPower;
    }

    /**
     * @return Возвращает максимальную мощность двигателя кодемобиля типа багги ({@code CarType.BUGGY}) в направлении,
     * противоположном направлению кодемобиля.
     */
    public double getBuggyEngineRearPower() {
        return buggyEngineRearPower;
    }

    /**
     * @return Возвращает массу кодемобиля типа джип ({@code CarType.JEEP}).
     */
    public double getJeepMass() {
        return jeepMass;
    }

    /**
     * @return Возвращает максимальную мощность двигателя кодемобиля типа джип ({@code CarType.JEEP}) в направлении,
     * совпадающем с направлением кодемобиля.
     */
    public double getJeepEngineForwardPower() {
        return jeepEngineForwardPower;
    }

    /**
     * @return Возвращает максимальную мощность двигателя кодемобиля типа джип ({@code CarType.JEEP}) в направлении,
     * противоположном направлению кодемобиля.
     */
    public double getJeepEngineRearPower() {
        return jeepEngineRearPower;
    }

    /**
     * @return Возвращает размер (ширину и высоту) бонуса.
     */
    public double getBonusSize() {
        return bonusSize;
    }

    /**
     * @return Возвращает массу бонуса.
     */
    public double getBonusMass() {
        return bonusMass;
    }

    /**
     * @return Возвращает количество баллов, мгновенно получаемых игроком, кодемобиль которого подобрал бонусные баллы
     * ({@code BonusType.PURE_SCORE}).
     */
    public int getPureScoreAmount() {
        return pureScoreAmount;
    }

    /**
     * @return Возвращает радиус шайбы ({@code ProjectileType.WASHER}).
     */
    public double getWasherRadius() {
        return washerRadius;
    }

    /**
     * @return Возвращает массу шайбы ({@code ProjectileType.WASHER}).
     */
    public double getWasherMass() {
        return washerMass;
    }

    /**
     * @return Возвращает начальную скорость шайбы ({@code ProjectileType.WASHER}).
     */
    public double getWasherInitialSpeed() {
        return washerInitialSpeed;
    }

    /**
     * @return Возвращает урон шайбы ({@code ProjectileType.WASHER}).
     */
    public double getWasherDamage() {
        return washerDamage;
    }

    /**
     * @return Возвращает модуль отклонения направления полёта двух шайб от направления кодемобиля.
     * Направление третьей шайбы совпадает с направлением кодемобиля.
     */
    public double getSideWasherAngle() {
        return sideWasherAngle;
    }

    /**
     * @return Возвращает радиус шины ({@code ProjectileType.TIRE}).
     */
    public double getTireRadius() {
        return tireRadius;
    }

    /**
     * @return Возвращает массу шины ({@code ProjectileType.TIRE}).
     */
    public double getTireMass() {
        return tireMass;
    }

    /**
     * @return Возвращает начальную скорость шины ({@code ProjectileType.TIRE}).
     */
    public double getTireInitialSpeed() {
        return tireInitialSpeed;
    }

    /**
     * @return Возвращает количество урона, которое шина нанесёт неподвижно стоящему кодемобилю при попадании в него с
     * начальной скоростью ({@code game.tireInitialSpeed}) и под прямым углом к поверхности кодемобиля. Движение
     * кодемобиля в направлении, совпадающем с направлением движения шины, уменьшает урон, движение в противоположном
     * направлении --- увеличивает.
     */
    public double getTireDamageFactor() {
        return tireDamageFactor;
    }

    /**
     * @return Возвращает отношение текущей скорости шины к начальной ({@code game.tireInitialSpeed}), при превышении
     * которого в момент столкновения с другим объектом шина отскакивает и продолжает свой полёт. В противном случае
     * шина убирается из игрового мира.
     */
    public double getTireDisappearSpeedFactor() {
        return tireDisappearSpeedFactor;
    }

    /**
     * @return Возвращает расстояние между ближайшими точками лужи мазута и кодемобиля при использовании канистры с
     * мазутом.
     */
    public double getOilSlickInitialRange() {
        return oilSlickInitialRange;
    }

    /**
     * @return Возвращает радиус лужи мазута.
     */
    public double getOilSlickRadius() {
        return oilSlickRadius;
    }

    /**
     * @return Возвращает длительность высыхания лужи мазута в тиках.
     */
    public int getOilSlickLifetime() {
        return oilSlickLifetime;
    }

    /**
     * @return Возвращает максимально возможную длительность высыхания кодемобиля, центр которого попал в лужу мазута.
     * При этом, длительность высыхания лужа мазута сокращается на то же количество тиков. Таким образом, реальная
     * длительность высыхания кодемобиля не может превышать оставшуюся длительность высыхания лужи.
     */
    public int getMaxOiledStateDurationTicks() {
        return maxOiledStateDurationTicks;
    }
}
