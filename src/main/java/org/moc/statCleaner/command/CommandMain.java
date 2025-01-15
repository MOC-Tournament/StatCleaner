package org.moc.statCleaner.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.moc.statCleaner.StatCleaner;

public class CommandMain implements CommandExecutor {
    private final StatCleaner parent;
    public CommandMain(StatCleaner parent) {
        this.parent = parent;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args[0].equals("reload"))
        {
            if (!sender.hasPermission("statcleaner.reload")) {
                sender.sendMessage(ChatColor.RED + "You don't have permission!");
                return false;
            }
            parent.reloadConfig();
            sender.sendMessage(ChatColor.GREEN + "Successfully reloaded the config.");
            return true;
        }
        else {
            sender.sendMessage(ChatColor.RED + "Unknown subcommand!");
            return false;
        }
    }
}
