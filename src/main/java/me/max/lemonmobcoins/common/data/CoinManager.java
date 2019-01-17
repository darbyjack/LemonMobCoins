/*
 *
 *  *
 *  *  * LemonMobCoins - Kill mobs and get coins that can be used to buy awesome things
 *  *  * Copyright (C) 2018 Max Berkelmans AKA LemmoTresto
 *  *  *
 *  *  * This program is free software: you can redistribute it and/or modify
 *  *  * it under the terms of the GNU General Public License as published by
 *  *  * the Free Software Foundation, either version 3 of the License, or
 *  *  * (at your option) any later version.
 *  *  *
 *  *  * This program is distributed in the hope that it will be useful,
 *  *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  *  * GNU General Public License for more details.
 *  *  *
 *  *  * You should have received a copy of the GNU General Public License
 *  *  * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *  *
 *
 */

package me.max.lemonmobcoins.common.data;


import me.max.lemonmobcoins.common.exceptions.DataLoadException;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

public class CoinManager {

    private final DataProvider dataProvider;
    private final Map<UUID, Double> coins;

    public CoinManager(DataProvider dataProvider) throws DataLoadException {
        this.dataProvider = dataProvider;
        try {
            coins = dataProvider.loadData();
        } catch (Throwable t) {
            throw new DataLoadException(t);
        }
    }

    /**
     * Save plugin data
     * @throws IOException
     * @throws SQLException
     */
    public void saveData() throws IOException, SQLException {
        dataProvider.saveData(coins);
    }

    /**
     * Get the coins of a player
     * @param uuid the uuid of the player
     * @return the amount of coins
     */
    public double getCoinsOfPlayer(@NotNull UUID uuid) {
        return coins.getOrDefault(uuid, 0.0);
    }

    /**
     * Set the coins of a player
     * @param uuid the uuid of the player
     * @param coins the amount of coins
     */
    public void setCoinsOfPlayer(@NotNull UUID uuid, double coins) {
        this.coins.put(uuid, coins);
    }

    /**
     * Add coins to a player
     * @param uuid the uuid of the player
     * @param coins the amount of coins
     */
    public void addCoinsToPlayer(@NotNull UUID uuid, double coins) {
        setCoinsOfPlayer(uuid, getCoinsOfPlayer(uuid) + coins);
    }

    /**
     * Not used apparently
     * @param uuid the uuid of the player
     */
    public void incrementPlayerBalance(@NotNull UUID uuid) {
        addCoinsToPlayer(uuid, 1);
    }

    /**
     * Remove coins from the player
     * @param uuid the uuid of the player
     * @param price the amount of coins
     */
    public void deductCoinsFromPlayer(@NotNull UUID uuid, double price) {
        setCoinsOfPlayer(uuid, getCoinsOfPlayer(uuid) - price);
    }

    /**
     * Get a map of all coins
     * @return map of coins
     */
    @NotNull
    public Map<UUID, Double> getCoins() {
        return coins;
    }

}
