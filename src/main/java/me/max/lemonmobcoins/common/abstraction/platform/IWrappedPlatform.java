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

package me.max.lemonmobcoins.common.abstraction.platform;

import me.max.lemonmobcoins.common.abstraction.entity.IWrappedOfflinePlayer;
import me.max.lemonmobcoins.common.abstraction.entity.IWrappedPlayer;
import me.max.lemonmobcoins.common.abstraction.inventory.IWrappedInventory;
import me.max.lemonmobcoins.common.abstraction.inventory.IWrappedItemStack;
import me.max.lemonmobcoins.common.gui.ShopItem;

import java.util.List;
import java.util.UUID;

public interface IWrappedPlatform {

    /**
     * Get an array of all online wrapped players
     * @return
     */
    IWrappedPlayer[] getOnlinePlayers();

    /**
     * Get a wrapped player by string
     * @param name the name of the player
     * @return
     */
    IWrappedPlayer getPlayer(String name);

    /**
     * Get a wrapped player by uuid
     * @param uuid the uuid of the player
     * @return
     */
    IWrappedPlayer getPlayer(UUID uuid);

    /**
     * Get a wrapped offline player by uuid
     * @param uuid the uuid of the player
     * @return
     */
    IWrappedOfflinePlayer getOfflinePlayer(UUID uuid);

    /**
     * Get the wrapped offline player by string
     * @param name the name of the player
     * @return
     */
    IWrappedOfflinePlayer getOfflinePlayer(String name);

    /**
     * Enables the platform
     */
    void enable();

    /**
     * Disables the platform
     */
    void disable();

    /**
     * Create a wrapped inventory
     * @param title the name of the inventory
     * @param rows the size of the inventory
     * @param items the items in the inventory
     * @return
     */
    IWrappedInventory createInventory(String title, int rows, List<ShopItem> items);

    /**
     * Create a wrapped item stack
     * @param item the item to be wrapped
     * @return
     */
    IWrappedItemStack toItemStack(ShopItem item);

}
