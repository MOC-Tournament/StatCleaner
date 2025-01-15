package org.moc.statCleaner;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.moc.statCleaner.command.CommandMain;
import org.moc.statCleaner.command.CommandReset;

import java.util.Objects;

public final class StatCleaner extends JavaPlugin {

    @Override
    public void onEnable() {
        // Config
        this.saveDefaultConfig();
        FileConfiguration config = this.getConfig();
        // Command
        Objects.requireNonNull(this.getCommand("statreset")).setExecutor(new CommandReset(this));
        Objects.requireNonNull(this.getCommand("statcleaner")).setExecutor(new CommandMain(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
