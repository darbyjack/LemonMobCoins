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

package me.max.lemonmobcoins.sponge.impl.entity;

import me.max.lemonmobcoins.common.abstraction.entity.IWrappedPlayer;
import me.max.lemonmobcoins.common.abstraction.inventory.IWrappedInventory;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.text.Text;

import java.util.UUID;

public class PlayerSpongeImpl implements IWrappedPlayer {

    private final Player player;

    public PlayerSpongeImpl(Player player) {
        this.player = player;
    }

    /**
     * Get the name of the player
     * @return player name
     */
    @Override
    public String getName() {
        return player.getName();
    }

    /**
     * Get the uuid of the player
     * @return player uuid
     */
    @Override
    public UUID getUniqueId() {
        return player.getUniqueId();
    }

    /**
     * Send a message to the player
     * @param message message to player
     */
    @Override
    public void sendMessage(String message) {
        player.sendMessage(Text.of(message));
    }

    /**
     * Make the player open an inventory
     * @param inventory inventory to player
     */
    @Override
    public void openInventory(IWrappedInventory inventory) {
        player.openInventory((Inventory) inventory.getInventory());
    }

}
