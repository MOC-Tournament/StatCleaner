/*
 * Copyright (c) 2025. JerryHan3.
 *
 * This is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this software. If not, see <https://www.gnu.org/licenses/>
 * and navigate to version 3 of the GNU Affero General Public License.
 */

package org.moc.statCleaner;

import org.bukkit.plugin.java.JavaPlugin;
import org.moc.statCleaner.command.CommandMain;
import org.moc.statCleaner.command.CommandReset;
import org.moc.statCleaner.command.TabCompleter.TabMain;
import org.moc.statCleaner.command.TabCompleter.TabReset;
import org.moc.statCleaner.utils.VersionDetector;

import java.util.Objects;

public final class StatCleaner extends JavaPlugin {
    private MessageManager messageManager;

    @Override
    public void onEnable() {
        // Config
        this.saveDefaultConfig();
        // Messages
        if (!getDataFolder().exists()) {
            if (!getDataFolder().mkdir()) {
                getLogger().severe("Failed to initialize plugin folder!");
            }
        }
        messageManager = new MessageManager(this);
        // Command
        Objects.requireNonNull(this.getCommand("statreset")).setExecutor(new CommandReset(this));
        Objects.requireNonNull(this.getCommand("statreset")).setTabCompleter(new TabReset());
        Objects.requireNonNull(this.getCommand("statcleaner")).setExecutor(new CommandMain(this));
        Objects.requireNonNull(this.getCommand("statcleaner")).setTabCompleter(new TabMain());
        // Complete log
        getLogger().info("StatCleaner " + getDescription().getVersion() + " has been successfully loaded!");
        // Version warning
        if (!VersionDetector.isVersionAtLeast(9)) getLogger().warning("You are using a version that hasn't be fully supported yet. Some stats like saturation or attributes will be skipped!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public MessageManager getMessageManager() {
        return messageManager;
    }
}
