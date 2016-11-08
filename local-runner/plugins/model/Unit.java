package model;


import static java.lang.StrictMath.PI;
import static java.lang.StrictMath.atan2;
import static java.lang.StrictMath.hypot;

/**
 * Базовый класс для определения объектов (<<юнитов>>) на игровом поле.
 */
@SuppressWarnings({"AbstractClassWithoutAbstractMethods", "WeakerAccess"})
public abstract class Unit {
    private final long id;
    private final double x;
    private final double y;
    private final double speedX;
    private final double speedY;
    private final double angle;
    private final Faction faction;

    protected Unit(long id, double x, double y, double speedX, double speedY, double angle, Faction faction) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
        this.angle = angle;
        this.faction = faction;
    }

    /**
     * @return Возвращает уникальный идентификатор объекта.
     */
    public long getId() {
        return id;
    }

    /**
     * @return Возвращает X-координату центра объекта. Ось абсцисс направлена слева направо.
     */
    public final double getX() {
        return x;
    }

    /**
     * @return Возвращает Y-координату центра объекта. Ось ординат направлена сверху вниз.
     */
    public final double getY() {
        return y;
    }

    /**
     * @return Возвращает X-составляющую скорости объекта. Ось абсцисс направлена слева направо.
     * <p>
     * Для юнитов, способных мгновенно менять свою скорость, возвращается значение перемещения за последний тик.
     */
    public final double getSpeedX() {
        return speedX;
    }

    /**
     * @return Возвращает Y-составляющую скорости объекта. Ось ординат направлена сверху вниз.
     * <p>
     * Для юнитов, способных мгновенно менять свою скорость, возвращается значение перемещения за последний тик.
     */
    public final double getSpeedY() {
        return speedY;
    }

    /**
     * @return Возвращает угол поворота объекта в радианах. Нулевой угол соответствует направлению оси абсцисс.
     * Положительные значения соответствуют повороту по часовой стрелке.
     */
    public final double getAngle() {
        return angle;
    }

    /**
     * @return Возвращает фракцию, к которой принадлежит данный юнит.
     */
    public Faction getFaction() {
        return faction;
    }

    /**
     * @param x X-координата точки.
     * @param y Y-координата точки.
     * @return Возвращает ориентированный угол [{@code -PI}, {@code PI}] между направлением
     * данного объекта и вектором из центра данного объекта к указанной точке.
     */
    public double getAngleTo(double x, double y) {
        double absoluteAngleTo = atan2(y - this.y, x - this.x);
        double relativeAngleTo = absoluteAngleTo - angle;

        while (relativeAngleTo > PI) {
            relativeAngleTo -= 2.0D * PI;
        }

        while (relativeAngleTo < -PI) {
            relativeAngleTo += 2.0D * PI;
        }

        return relativeAngleTo;
    }

    /**
     * @param unit Объект, к центру которого необходимо определить угол.
     * @return Возвращает ориентированный угол [{@code -PI}, {@code PI}] между направлением
     * данного объекта и вектором из центра данного объекта к центру указанного объекта.
     */
    public double getAngleTo(Unit unit) {
        return getAngleTo(unit.x, unit.y);
    }

    /**
     * @param x X-координата точки.
     * @param y Y-координата точки.
     * @return Возвращает расстояние до точки от центра данного объекта.
     */
    public double getDistanceTo(double x, double y) {
        return hypot(x - this.x, y - this.y);
    }

    /**
     * @param unit Объект, до центра которого необходимо определить расстояние.
     * @return Возвращает расстояние от центра данного объекта до центра указанного объекта.
     */
    public double getDistanceTo(Unit unit) {
        return getDistanceTo(unit.x, unit.y);
    }
}
