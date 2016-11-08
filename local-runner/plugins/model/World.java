package model;

import java.util.Arrays;

/**
 * Этот класс описывает игровой мир. Содержит также описания всех игроков и игровых объектов (<<юнитов>>).
 */
public class World {
    private final int tickIndex;
    private final int tickCount;
    private final double width;
    private final double height;
    private final Player[] players;
    private final Wizard[] wizards;
    private final Minion[] minions;
    private final Projectile[] projectiles;
    private final Bonus[] bonuses;
    private final Building[] buildings;
    private final Tree[] trees;

    public World(
            int tickIndex, int tickCount, double width, double height, Player[] players, Wizard[] wizards,
            Minion[] minions, Projectile[] projectiles, Bonus[] bonuses, Building[] buildings, Tree[] trees) {
        this.tickIndex = tickIndex;
        this.tickCount = tickCount;
        this.width = width;
        this.height = height;
        this.players = Arrays.copyOf(players, players.length);
        this.wizards = Arrays.copyOf(wizards, wizards.length);
        this.minions = Arrays.copyOf(minions, minions.length);
        this.projectiles = Arrays.copyOf(projectiles, projectiles.length);
        this.bonuses = Arrays.copyOf(bonuses, bonuses.length);
        this.buildings = Arrays.copyOf(buildings, buildings.length);
        this.trees = Arrays.copyOf(trees, trees.length);
    }

    /**
     * @return Возвращает номер текущего тика.
     */
    public int getTickIndex() {
        return tickIndex;
    }

    /**
     * @return Возвращает базовую длительность игры в тиках. Реальная длительность может отличаться от этого значения в
     * меньшую сторону. Эквивалентно {@code game.tickCount}.
     */
    public int getTickCount() {
        return tickCount;
    }

    /**
     * @return Возвращает ширину мира.
     */
    public double getWidth() {
        return width;
    }

    /**
     * @return Возвращает высоту мира.
     */
    public double getHeight() {
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
     * @return Возвращает список видимых волшебников (в случайном порядке).
     * После каждого тика объекты, задающие волшебников, пересоздаются.
     */
    public Wizard[] getWizards() {
        return Arrays.copyOf(wizards, wizards.length);
    }

    /**
     * @return Возвращает список видимых последователей (в случайном порядке).
     * После каждого тика объекты, задающие последователей, пересоздаются.
     */
    public Minion[] getMinions() {
        return Arrays.copyOf(minions, minions.length);
    }

    /**
     * @return Возвращает список видимых магических снарядов (в случайном порядке).
     * После каждого тика объекты, задающие снаряды, пересоздаются.
     */
    public Projectile[] getProjectiles() {
        return Arrays.copyOf(projectiles, projectiles.length);
    }

    /**
     * @return Возвращает список видимых бонусов (в случайном порядке).
     * После каждого тика объекты, задающие бонусы, пересоздаются.
     */
    public Bonus[] getBonuses() {
        return Arrays.copyOf(bonuses, bonuses.length);
    }

    /**
     * @return Возвращает список видимых строений (в случайном порядке).
     * После каждого тика объекты, задающие строения, пересоздаются.
     */
    public Building[] getBuildings() {
        return Arrays.copyOf(buildings, buildings.length);
    }

    /**
     * @return Возвращает список видимых деревьев (в случайном порядке).
     * После каждого тика объекты, задающие деревья, пересоздаются.
     */
    public Tree[] getTrees() {
        return Arrays.copyOf(trees, trees.length);
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
