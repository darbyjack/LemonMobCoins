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

package me.max.lemonmobcoins.common.abstraction.pluginmessaging;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.max.lemonmobcoins.common.abstraction.entity.IWrappedPlayer;
import me.max.lemonmobcoins.common.data.CoinManager;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class AbstractPluginMessageManager {

    private final List<UUID> cache = new ArrayList<>();
    private final CoinManager coinManager;
    private final Logger logger;

    public AbstractPluginMessageManager(CoinManager coinManager, Logger logger) {
        this.coinManager = coinManager;
        this.logger = logger;
    }

    /**
     * This method sends a message via a players uuid
     * @param uuid the uuid of the player
     */
    public void sendPluginMessage(UUID uuid) {

    }

    /**
     * Get the plugin message for a player
     * @param p the player the message is going to
     * @param uuid the uuid of the player
     * @param balance the balance of the player
     * @return message for player
     */
    protected ByteArrayDataOutput getPluginMessage(IWrappedPlayer p, UUID uuid, double balance) {
        if (p == null) {
            if (getCache().contains(uuid)) return null;
            getCache().add(uuid);
            return null;
        }

        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("LemonMobCoins");
        out.writeUTF(uuid.toString());
        out.writeDouble(balance);
        return out;
    }

    /**
     * This method will send all the cached
     */
    void sendPendingPluginMessages() {
        getCache().forEach(this::sendPluginMessage);
    }

    /**
     * Get the uuid cache for the messages
     * @return uuid cache
     */
    List<UUID> getCache() {
        return cache;
    }

    /**
     * Get the coin manager
     * @return coin manager
     */
    public CoinManager getCoinManager() {
        return coinManager;
    }

    /**
     * Get the plugin logger
     * @return logger
     */
    public Logger getLogger() {
        return logger;
    }

}
