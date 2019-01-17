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

package me.max.lemonmobcoins.common.gui;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ShopItem {

    private final String identifier;
    private final int slot;
    private final String material;
    private final int amount;
    private final String displayname;
    private final boolean glowing;
    private final List<String> lore;
    private final boolean permission;
    private final double price;
    private final List<String> commands;

    private ShopItem(@NotNull String identifier, int slot, @NotNull String material, int amount, @NotNull String displayname, boolean glowing, @NotNull List<String> lore, boolean permission, double price, @NotNull List<String> commands) {
        this.identifier = identifier;
        this.slot = slot;
        this.material = material;
        this.amount = amount;
        this.displayname = displayname;
        this.glowing = glowing;
        this.lore = lore;
        this.permission = permission;
        this.price = price;
        this.commands = commands;
    }

    /**
     * Get the size of the item
     * @return size
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Get the display name of the item
     * @return display name
     */
    @NotNull
    public String getDisplayname() {
        return displayname;
    }

    /**
     * Check if the item is glowing or not
     * @return true / false
     */
    public boolean isGlowing() {
        return glowing;
    }

    /**
     * Check if the item has a permission on it
     * @return true / false
     */
    public boolean isPermission() {
        return permission;
    }

    /**
     * Get the price of an item
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Get the slot of the item
     * @return slot
     */
    public int getSlot() {
        return slot;
    }

    /**
     * Get the lore of an item
     * @return item lore
     */
    @NotNull
    public List<String> getLore() {
        return lore;
    }

    /**
     * Get the commands from an item
     * @return item commands
     */
    @NotNull
    public List<String> getCommands() {
        return commands;
    }

    /**
     * Get the material of an item
     * @return item material
     */
    @NotNull
    public String getMaterial() {
        return material;
    }

    /**
     * Get the identifier of an item
     * @return item identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    static class Builder {

        private final String identifier;
        private int slot;
        private String material;
        private int amount;
        private String displayname;
        private boolean glowing;
        private List<String> lore;
        private boolean permission;
        private double price;
        private List<String> commands;

        Builder(String identifier) {
            this.identifier = identifier;
        }

        @Contract("_ -> this")
        Builder setDisplayname(String displayname) {
            this.displayname = displayname;
            return this;
        }

        @Contract("_ -> this")
        Builder setAmount(int amount) {
            this.amount = amount;
            return this;
        }

        @Contract("_ -> this")
        Builder setPermission(boolean permission) {
            this.permission = permission;
            return this;
        }

        @Contract("_ -> this")
        Builder setCommands(List<String> commands) {
            this.commands = commands;
            return this;
        }

        @Contract("_ -> this")
        Builder setGlowing(boolean glowing) {
            this.glowing = glowing;
            return this;
        }

        @Contract("_ -> this")
        Builder setLore(List<String> lore) {
            this.lore = lore;
            return this;
        }

        @Contract("_ -> this")
        Builder setMaterial(String material) {
            this.material = material;
            return this;
        }

        @Contract("_ -> this")
        Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        @Contract("_ -> this")
        Builder setSlot(int slot) {
            this.slot = slot;
            return this;
        }

        @Contract("-> new")
        ShopItem build() {
            return new ShopItem(identifier, slot, material, amount, displayname, glowing, lore, permission, price, commands);
        }

    }

}
