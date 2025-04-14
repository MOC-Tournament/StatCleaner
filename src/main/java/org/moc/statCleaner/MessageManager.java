package org.moc.statCleaner;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.moc.statCleaner.StatCleaner;

import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.logging.Level;

public class MessageManager {
    private final StatCleaner parent;
    private YamlConfiguration messages;
    private File messagesFile;

    public MessageManager(StatCleaner parent) {
        this.parent = parent;
        this.messagesFile = new File(parent.getDataFolder(), "messages.yml");
        reloadMessages();
    }

    public void reloadMessages() {
        if (!messagesFile.exists()) {
            parent.saveResource("messages.yml", false);
        }

        messages = YamlConfiguration.loadConfiguration(messagesFile);

        Reader defConfigStream = new InputStreamReader(Objects.requireNonNull(parent.getResource("messages.yml")), StandardCharsets.UTF_8);
        YamlConfiguration defaultCfg = YamlConfiguration.loadConfiguration(defConfigStream);
        messages.setDefaults(defaultCfg);
    }

    /**
     * 基本的无变量消息
     * @param path 消息ID
     * */
    public String getMessages(String path) {
        if (messages.getString(path) == null) {
            parent.getLogger().log(Level.WARNING, "Can't get message with path \"" + path + "\". Path name is directly returned. ");
        }
        return ChatColor.translateAlternateColorCodes('&', messages.getString(path, "&c<path>"));
    }

    /**
     * 包含变量的消息
     * */
    public String getMessages(String path, Object... args) {
        String raw = getMessages(path);
        return ChatColor.translateAlternateColorCodes('&', String.format(raw, args));
    }
}
