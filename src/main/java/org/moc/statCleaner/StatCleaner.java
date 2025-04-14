package org.moc.statCleaner;

import org.bukkit.plugin.java.JavaPlugin;
import org.moc.statCleaner.command.CommandMain;
import org.moc.statCleaner.command.CommandReset;

import java.util.Objects;

public final class StatCleaner extends JavaPlugin {
    private MessageManager messageManager;

    @Override
    public void onEnable() {
        // Config
        this.saveDefaultConfig();
        // Messages
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        messageManager = new MessageManager(this);
        // Command
        Objects.requireNonNull(this.getCommand("statreset")).setExecutor(new CommandReset(this));
        Objects.requireNonNull(this.getCommand("statcleaner")).setExecutor(new CommandMain(this));
        // Complete log
        getLogger().info("StatCleaner " + getDescription().getVersion() + " has been successfully loaded!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public MessageManager getMessageManager() {
        return messageManager;
    }
}
