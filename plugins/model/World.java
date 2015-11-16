package model;

import java.util.Arrays;

/**
 * Этот класс описывает игровой мир. Содержит также описания всех игроков и игровых объектов (<<юнитов>>).
 */
public class World {
    private final int tick;
    private final int tickCount;
    private final int lastTickIndex;
    private final int width;
    private final int height;
    private final Player[] players;
    private final Car[] cars;
    private final Projectile[] projectiles;
    private final Bonus[] bonuses;
    private final OilSlick[] oilSlicks;
    private final String mapName;
    private final TileType[][] tilesXY;
    private final int[][] waypoints;
    private final Direction startingDirection;

    public World(
            int tick, int tickCount, int lastTickIndex, int width, int height, Player[] players, Car[] cars,
            Projectile[] projectiles, Bonus[] bonuses, OilSlick[] oilSlicks, String mapName, TileType[][] tilesXY,
            int[][] waypoints, Direction startingDirection) {
        this.tick = tick;
        this.tickCount = tickCount;
        this.lastTickIndex = lastTickIndex;
        this.width = width;
        this.height = height;
        this.players = Arrays.copyOf(players, players.length);
        this.cars = Arrays.copyOf(cars, cars.length);
        this.projectiles = Arrays.copyOf(projectiles, projectiles.length);
        this.bonuses = Arrays.copyOf(bonuses, bonuses.length);
        this.oilSlicks = Arrays.copyOf(oilSlicks, oilSlicks.length);
        this.mapName = mapName;

        this.tilesXY = new TileType[width][];
        for (int x = 0; x < width; ++x) {
            this.tilesXY[x] = Arrays.copyOf(tilesXY[x], tilesXY[x].length);
        }

        this.waypoints = new int[waypoints.length][2];
        for (int waypointIndex = 0; waypointIndex < waypoints.length; ++waypointIndex) {
            this.waypoints[waypointIndex][0] = waypoints[waypointIndex][0];
            this.waypoints[waypointIndex][1] = waypoints[waypointIndex][1];
        }

        this.startingDirection = startingDirection;
    }

    /**
     * @return Возвращает номер текущего тика.
     */
    public int getTick() {
        return tick;
    }

    /**
     * @return Возвращает базовую длительность игры в тиках.
     * Реальная длительность может отличаться от этого значения в меньшую сторону.
     * Поле может быть определено как {@code game.initialFreezeDurationTicks + game.lapCount * game.lapTickCount}.
     * Значение поля не меняется в процессе игры. Эквивалентно {@code game.tickCount}.
     */
    public int getTickCount() {
        return tickCount;
    }

    /**
     * @return Возвращает номер последнего тика игры.
     * Сразу после старта содержит значение {@code tickCount - 1}.
     * В момент завершения трассы любым из кодемобилей получает значение
     * {@code min(tickCount - 1, tick + max(floor(game.burningTimeDurationFactor * game.lapTickCount), 1))}.
     * Таким образом, кодемобиль, отставший от идущего впереди более, чем на {@code game.burningTimeDurationFactor}
     * кругов, рискует не успеть вообще добраться до финиша.
     * <p/>
     * Игра может закончиться раньше, чем наступит {@code lastTickIndex}, если для каждого игрока выполняется одно
     * из двух условий: стратегия игрока <<упала>>, либо все его кодемобили финишировали.
     */
    public int getLastTickIndex() {
        return lastTickIndex;
    }

    /**
     * @return Возвращает ширину мира в тайлах.
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return Возвращает высоту мира в тайлах.
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return Возвращает список игроков (в случайном порядке).
     * После каждого тика объекты, задающие игроков, пересоздаются.
     */
    public Player[] getPlayers() {
        return Arrays.copyOf(players, players.length);
    }

    /**
     * @return Возвращает список кодемобилей (в случайном порядке).
     * После каждого тика объекты, задающие кодемобили, пересоздаются.
     */
    public Car[] getCars() {
        return Arrays.copyOf(cars, cars.length);
    }

    /**
     * @return Возвращает список снарядов (в случайном порядке).
     * После каждого тика объекты, задающие снаряды, пересоздаются.
     */
    public Projectile[] getProjectiles() {
        return Arrays.copyOf(projectiles, projectiles.length);
    }

    /**
     * @return Возвращает список бонусов (в случайном порядке).
     * После каждого тика объекты, задающие бонусы, пересоздаются.
     */
    public Bonus[] getBonuses() {
        return Arrays.copyOf(bonuses, bonuses.length);
    }

    /**
     * @return Возвращает список масляных луж (в случайном порядке).
     * После каждого тика объекты, задающие лужи, пересоздаются.
     */
    public OilSlick[] getOilSlicks() {
        return Arrays.copyOf(oilSlicks, oilSlicks.length);
    }

    /**
     * @return Возвращает краткое уникальное название трассы.
     */
    public String getMapName() {
        return mapName;
    }

    /**
     * @return Возвращает двумерный массив тайлов, где первое измерение --- это позиция X, а второе --- Y.
     * Конвертировать позицию в точные координаты можно, используя значение {@code game.trackTileSize}.
     */
    public TileType[][] getTilesXY() {
        TileType[][] copiedTilesXY = new TileType[tilesXY.length][];
        for (int x = 0; x < tilesXY.length; ++x) {
            copiedTilesXY[x] = Arrays.copyOf(tilesXY[x], tilesXY[x].length);
        }
        return copiedTilesXY;
    }

    /**
     * @return Возвращает массив ключевых тайлов. Каждый тайл задаётся массивом длины 2,
     * где элемент с индексом {@code 0} содержит позицию X, а элемент с индексом {@code 1} --- позицию Y.
     * Конвертировать позицию в точные координаты можно, используя значение {@code game.trackTileSize}.
     * Для прохождения круга кодемобилю необходимо посещать тайлы в указанном порядке.
     * Ключевой тайл с индексом {@code 0} является одновременно начальным тайлом трассы и конечным тайлом каждого круга.
     * Считается, что кодемобиль посетил ключевой тайл, если центр кодемобиля пересёк границу этого тайла.
     */
    public int[][] getWaypoints() {
        int[][] copiedWaypoints = new int[waypoints.length][2];
        for (int waypointIndex = 0; waypointIndex < waypoints.length; ++waypointIndex) {
            copiedWaypoints[waypointIndex][0] = waypoints[waypointIndex][0];
            copiedWaypoints[waypointIndex][1] = waypoints[waypointIndex][1];
        }
        return copiedWaypoints;
    }

    /**
     * @return Направление кодемобиля в начале игры.
     */
    public Direction getStartingDirection() {
        return startingDirection;
    }

    /**
     * @return Возвращает вашего игрока.
     */
    public Player getMyPlayer() {
        for (int playerIndex = players.length - 1; playerIndex >= 0; --playerIndex) {
            Player player = players[playerIndex];
            if (player.isMe()) {
                return player;
            }
        }

        return null;
    }
}
