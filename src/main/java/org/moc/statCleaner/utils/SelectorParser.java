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

package org.moc.statCleaner.utils;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SelectorParser {

    public static List<Player> parsePlayers(CommandSender sender, String selector) throws IllegalArgumentException {
        if (selector == null || selector.isEmpty()) {
            return Collections.emptyList();
        }

        if (selector.startsWith("@")) {
            return parseSelector(sender, selector);
        }
        else {
            return parsePlayerName(selector);
        }
    }

    private static List<Player> parseSelector(CommandSender sender, String selector) {
        try {
            List<Entity> entities = Bukkit.selectEntities(sender, selector);
            List<Player> players = new ArrayList<>();
            for (Entity entity : entities) {
                if (entity instanceof Player) {
                    players.add((Player) entity);
                }
            }
            return players;
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(selector, e);
        }
    }

    private static List<Player> parsePlayerName(String name) {
        Player player = Bukkit.getPlayerExact(name);
        if (player != null) {
            return Collections.singletonList(player);
        }
        throw new IllegalArgumentException(name);
    }
}
