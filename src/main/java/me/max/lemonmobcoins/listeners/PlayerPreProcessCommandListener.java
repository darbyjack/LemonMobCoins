/*
 *
 *  *
 *  *  * MobCoins - Earn coins for killing mobs.
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

package me.max.lemonmobcoins.listeners;

import me.max.lemonmobcoins.LemonMobCoins;
import me.max.lemonmobcoins.files.Messages;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerPreProcessCommandListener implements Listener {

    private LemonMobCoins lemonMobCoins;

    public PlayerPreProcessCommandListener(LemonMobCoins lemonMobCoins){
        this.lemonMobCoins = lemonMobCoins;
        lemonMobCoins.getServer().getPluginManager().registerEvents(this, lemonMobCoins);
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event){
        if (event.getMessage().toLowerCase().startsWith(lemonMobCoins.getGuiManager().getCommand().toLowerCase())){
            Player p = event.getPlayer();
            if (p.hasPermission("lemonmobcoins.shop")){
                p.openInventory(lemonMobCoins.getGuiManager().getInventory());
                return;
            }
            p.sendMessage(Messages.NO_PERMISSION_TO_EXECUTE.getMessage(lemonMobCoins, p, null, 0));
        }
    }
}