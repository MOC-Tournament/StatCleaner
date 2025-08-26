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
