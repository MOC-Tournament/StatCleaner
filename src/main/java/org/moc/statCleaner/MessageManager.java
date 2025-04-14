package org.moc.statCleaner;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.logging.Level;

public class MessageManager {
    private final StatCleaner parent;
    private YamlConfiguration messages;
    private final File messagesFile;

    public MessageManager(StatCleaner parent) {
        this.parent = parent;
        this.messagesFile = new File(parent.getDataFolder(), "messages.yml");
        reloadMessages();
    }

    public void reloadMessages() {
        // 不存在时创建新文件
        if (!messagesFile.exists()) {
            parent.saveResource("messages.yml", false);
        }
        // 读取语言文件
        messages = YamlConfiguration.loadConfiguration(messagesFile);
        // 合并、更新语言文件
        Reader defaultMsgStream = new InputStreamReader(Objects.requireNonNull(parent.getResource("messages.yml")), StandardCharsets.UTF_8);
        YamlConfiguration defaultMsg = YamlConfiguration.loadConfiguration(defaultMsgStream);
        boolean needUpdate = false;

        for (String key : defaultMsg.getKeys(true)) {
            if (!messages.contains(key)) {
                needUpdate = true;
                messages.set(key, defaultMsg.get(key));
                parent.getLogger().info("Appended new string " + key);
            }
        }

        if (needUpdate) {
            try {
                messages.save(messagesFile);
                parent.getLogger().info("New message file saved. ");
            } catch (IOException e) {
                parent.getLogger().severe("Failed to save new message file: " + e.getMessage());
            }
        }
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
