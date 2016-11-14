package model;

/**
 * Магический статус, влияющий на живого юнита.
 */
public class Status {
    private final long id;
    private final StatusType type;
    private final long wizardId;
    private final long playerId;
    private final int remainingDurationTicks;

    public Status(long id, StatusType type, long wizardId, long playerId, int remainingDurationTicks) {
        this.id = id;
        this.type = type;
        this.wizardId = wizardId;
        this.playerId = playerId;
        this.remainingDurationTicks = remainingDurationTicks;
    }

    /**
     * @return Возвращает уникальный идентификатор статуса.
     */
    public long getId() {
        return id;
    }

    /**
     * @return Возвращает тип магического статуса.
     */
    public StatusType getType() {
        return type;
    }

    /**
     * @return Возвращает идентификатор волшебника, наложившего данный статус, или {code -1}.
     */
    public long getWizardId() {
        return wizardId;
    }

    /**
     * @return Возвращает идентификатор игрока, волшебник которого наложил данный статус, или {code -1}.
     */
    public long getPlayerId() {
        return playerId;
    }

    /**
     * @return Возвращает оставшуюся длительность действия статуса.
     */
    public int getRemainingDurationTicks() {
        return remainingDurationTicks;
    }
}
