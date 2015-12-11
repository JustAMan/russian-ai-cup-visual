package model;

/**
 * Класс, определяющий лужу мазута. Содержит также все свойства круглого юнита.
 */
public class OilSlick extends CircularUnit {
    private final int remainingLifetime;

    public OilSlick(
            long id, double mass, double x, double y, double speedX, double speedY, double angle, double angularSpeed,
            double radius, int remainingLifetime) {
        super(id, mass, x, y, speedX, speedY, angle, angularSpeed, radius);

        this.remainingLifetime = remainingLifetime;
    }

    /**
     * @return Возвращает количество тиков, по прошествии которого лужа мазута полностью высохнет.
     */
    public int getRemainingLifetime() {
        return remainingLifetime;
    }
}
