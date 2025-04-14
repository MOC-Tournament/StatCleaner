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
