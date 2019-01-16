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

package me.max.lemonmobcoins.bukkit.impl.entity;

import me.max.lemonmobcoins.common.abstraction.entity.IWrappedPlayer;
import me.max.lemonmobcoins.common.abstraction.inventory.IWrappedInventory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

public class PlayerBukkitImpl implements IWrappedPlayer {

    private final Player player;

    public PlayerBukkitImpl(Player player) {
        this.player = player;
    }

    /**
     * Ge the name of a player
     * @return player name
     */
    @Override
    public String getName() {
        return player.getName();
    }

    /**
     * Get the uuid of a player
     * @return player uuid
     */
    @Override
    public UUID getUniqueId() {
        return player.getUniqueId();
    }

    /**
     * Send a message to a player
     * @param message message to send
     */
    @Override
    public void sendMessage(String message) {
        player.sendMessage(message);
    }

    /**
     * Get wrapped inventory
     * @param inventory inventory to open
     */
    @Override
    public void openInventory(IWrappedInventory inventory) {
        player.openInventory((Inventory) inventory.getInventory());
    }

}
