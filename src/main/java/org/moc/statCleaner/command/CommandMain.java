package org.moc.statCleaner.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
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
                sender.sendMessage(parent.getMessageManager().getMessages("error.no-permission"));
                return false;
            }
            parent.reloadConfig();
            parent.getMessageManager().reloadMessages();
            sender.sendMessage(parent.getMessageManager().getMessages("success.reload"));
            return true;
        }
        else {
            sender.sendMessage(parent.getMessageManager().getMessages("error.wrong-subcommand"));
            return false;
        }
    }
}
