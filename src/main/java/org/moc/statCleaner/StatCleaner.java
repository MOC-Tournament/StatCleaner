package org.moc.statCleaner;

import org.bukkit.plugin.java.JavaPlugin;
import org.moc.statCleaner.command.CommandReset;

import java.util.Objects;

public final class StatCleaner extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Objects.requireNonNull(this.getCommand("statreset")).setExecutor(new CommandReset());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
