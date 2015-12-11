package model;

/**
 * Содержит данные о текущем состоянии игрока.
 */
public class Player {
    private final long id;
    private final boolean me;
    private final String name;
    private final boolean strategyCrashed;
    private final int score;

    public Player(long id, boolean me, String name, boolean strategyCrashed, int score) {
        this.id = id;
        this.me = me;
        this.name = name;
        this.strategyCrashed = strategyCrashed;
        this.score = score;
    }

    /**
     * @return Возвращает уникальный идентификатор игрока.
     */
    public long getId() {
        return id;
    }

    /**
     * @return Возвращает {@code true} в том и только в том случае, если этот игрок ваш.
     */
    public boolean isMe() {
        return me;
    }

    /**
     * @return Возвращает имя игрока.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Возвращает специальный флаг --- показатель того, что стратегия игрока <<упала>>.
     * Более подробную информацию можно найти в документации к игре.
     */
    public boolean isStrategyCrashed() {
        return strategyCrashed;
    }

    /**
     * @return Возвращает количество баллов, набранное игроком.
     */
    public int getScore() {
        return score;
    }
}
