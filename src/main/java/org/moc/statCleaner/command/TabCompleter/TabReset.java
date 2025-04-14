package org.moc.statCleaner.command.TabCompleter;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TabReset implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        List<String> completion = new ArrayList<>();
        Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
        if (args.length == 1) {
            // Players
            for (Player player : onlinePlayers) {
                if (player.getName().toLowerCase().startsWith(args[0].toLowerCase())) {
                    completion.add(player.getName());
                }
            }
            // Selectors
            if ("@".startsWith(args[0])) {
                completion.add("@a");
                completion.add("@p");
                completion.add("@r");
                completion.add("@s");
            }
            return completion;
        }
        else if (args.length == 0) {
            // Players
            for (Player player : onlinePlayers) {
                completion.add(player.getName());
            }
            // Selectors
            completion.add("@a");
            completion.add("@p");
            completion.add("@r");
            completion.add("@s");
            return completion;
        }
        return Collections.emptyList();
    }
}
