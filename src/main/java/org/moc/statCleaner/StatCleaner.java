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
