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

package me.max.lemonmobcoins.bungee;


import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.max.lemonmobcoins.bungee.listeners.PluginMessagingListener;
import me.max.lemonmobcoins.common.LemonMobCoins;
import me.max.lemonmobcoins.common.data.CoinManager;
import me.max.lemonmobcoins.common.utils.FileUtil;
import net.md_5.bungee.api.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

public final class LemonMobCoinsBungeePlugin extends Plugin {

    private final Logger logger = LoggerFactory.getLogger(LemonMobCoins.class);
    private LemonMobCoins lemonMobCoins;

    /**
     * This method is called when the plugin is loaded (BEFORE) being enabled
     */
    @Override
    public void onLoad() {
        try {
            info("Loading config..");
            FileUtil.saveResource("bungeeConfig.yml", getDataFolder(), "config.yml");
            info("Loaded config!");
        } catch (IOException e) {
            error("Could not load config and files! Stopping plugin!");
            e.printStackTrace();
        }

        lemonMobCoins = new LemonMobCoins(getSLF4JLogger(), getDataFolder(), null);
    }

    /**
     * This method is called when the plugin is being enabled
     */
    @Override
    public void onEnable() {
        try {
            info("Loading listeners..");
            getProxy().getPluginManager()
                      .registerListener(this, new PluginMessagingListener(getProxy(), getCoinManager()));
            info("Loaded listeners!");
        } catch (Exception e) {
            error("Loading Listeners failed! Stopping plugin..");
            e.printStackTrace();
            return;
        }

        for (Map.Entry<UUID, Double> entry : getCoinManager().getCoins().entrySet()) {
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("LemonMobCoins");
            out.writeUTF(entry.getKey().toString());
            out.writeDouble(entry.getValue());
            getProxy().getServers().values().forEach(s -> s.sendData("BungeeCord", out.toByteArray(), true));
            info("Sent information of Player " + entry.getKey() + ". Balance sent: " + entry.getValue());
        }

    }

    /**
     * This method is being called when the plugin gets disabled
     */
    @Override
    public void onDisable() {
        try {
            info("Saving data..");
            lemonMobCoins.disable();
            info("Saved data!");
        } catch (IOException | SQLException e) {
            error("Failed saving data! Retrying..");
            try {
                lemonMobCoins.disable();
                info("Saved data!");
            } catch (IOException | SQLException e1) {
                error("Failed saving data again! Data will be lost ;(");
                e.printStackTrace();
            }
        }
        info("Disabled successfully!");
    }

    /**
     * Logs things to console as INFO
     * @param s the string to log
     */
    private void info(String s) {
        getSLF4JLogger().info(s);
    }

    /**
     * Log things to console as WARN
     * @param s the string to log
     */
    private void warn(String s) {
        getSLF4JLogger().warn(s);
    }

    /**
     * Log things to console as ERROR
     * @param s the string to log
     */
    private void error(String s) {
        getSLF4JLogger().error(s);
    }

    /**
     * Get the plugin logger
     * @return logger
     */
    private Logger getSLF4JLogger() {
        return logger;
    }

    /**
     * Get the coin manager
     * @return coin manager
     */
    @NotNull
    private CoinManager getCoinManager() {
        return lemonMobCoins.getCoinManager();
    }

}
