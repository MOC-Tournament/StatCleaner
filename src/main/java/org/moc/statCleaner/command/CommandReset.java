package org.moc.statCleaner.command;

import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.moc.statCleaner.StatCleaner;
import org.moc.statCleaner.utils.SelectorParser;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import static org.moc.statCleaner.utils.AttributeList.default_pre_21_3;
import static org.moc.statCleaner.utils.AttributeList.default_after_21_3;
import static org.moc.statCleaner.utils.VersionDetector.isVersionAtLeast;

public class CommandReset implements CommandExecutor {
    private final StatCleaner parent;
    public CommandReset(StatCleaner parent) {
        this.parent = parent;
    }
    /**
     * Execute when command `/statreset` is performed.
     * @param args command arguments
     * @return true if command is properly execute, false otherwise.
     * */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Refuse if no permission
        if (!sender.hasPermission("statcleaner.reset")) {
            sender.sendMessage(parent.getMessageManager().getMessages("error.no-permission"));
            return false;
        }
        if (args[0].startsWith("@e") || args[0].startsWith("@n")) {
            parent.getLogger().log(Level.WARNING, "Entity selector will not affect on non-player entities! ");
            sender.sendMessage(parent.getMessageManager().getMessages("warn.entity-selector"));
        }
        try {
            List<Player> targets = SelectorParser.parsePlayers(sender, args[0]);
            StringBuilder playersOutput = new StringBuilder();
            boolean isPartialFailed = false;
            for (Player target : targets) {
                if (resetStat(target)) isPartialFailed = true;
                playersOutput.append(target.getName()).append(",");
                target.sendMessage(parent.getMessageManager().getMessages("success.target-notify"));
            }
            playersOutput.deleteCharAt(playersOutput.length() - 1);
            sender.sendMessage(parent.getMessageManager().getMessages("success.stat-cleaned", playersOutput.toString()));
            if (isPartialFailed) sender.sendMessage(parent.getMessageManager().getMessages("warn.version-not-supported"));
        }
        catch (IllegalArgumentException e) {
            sender.sendMessage(parent.getMessageManager().getMessages("error.player-not-found"));
        }
        catch (RuntimeException e) {
            parent.getLogger().log(Level.SEVERE, "An unexpected error occurred", e);
            sender.sendMessage(parent.getMessageManager().getMessages("error.unexpected", e.getMessage()));
        }
        return true;
    }

    /**
     * Reset a player's stat.
     * @param target targeted Player instance.
     * @exception RuntimeException Throws when can't get player's GENERIC_MAX_HEALTH attribute.
     * */
    private boolean resetStat(Player target) throws RuntimeException {
        // Read config
        FileConfiguration config = this.parent.getConfig();
        boolean isHealthEnabled = config.getBoolean("reset.health");
        boolean isFoodEnabled = config.getBoolean("reset.food");
        boolean isEffectEnabled = config.getBoolean("reset.effect");
        boolean isAttributeEnabled = config.getBoolean("reset.attribute");
        boolean isFlyEnabled = config.getBoolean("reset.fly");
        boolean isPartialFailed = false;

        // Reset health
        if (isHealthEnabled)
        {
            if (isVersionAtLeast(9)) {
                AttributeInstance targetMaxHealth = target.getAttribute(Attribute.GENERIC_MAX_HEALTH);
                if (targetMaxHealth != null) {
                    targetMaxHealth.setBaseValue(20);
                    target.setHealth(targetMaxHealth.getValue());
                } else {
                    throw new RuntimeException("Can't get player's max health!");
                }
            }
            else {
                // 1.8 回退机制
                try {
                    target.setMaxHealth(20);
                } catch (NoSuchMethodError ignored) {}
                target.setHealth(target.getMaxHealth());
            }
        }

        // Reset food
        if (isFoodEnabled)
        {
            target.setFoodLevel(20);
            if (isVersionAtLeast(8)) {
                target.setSaturation(5.0f);
            }
            else {
                // TODO: 在1.8以下恢复饱和度
                parent.getLogger().warning("Can't recover saturation on version below 1.8 yet!");
                isPartialFailed = true;
            }
        }

        // Clear potion effects
        if (isEffectEnabled)
        {
            Collection<PotionEffect> active_effects = target.getActivePotionEffects();
            for (PotionEffect effect : active_effects) {
                target.removePotionEffect(effect.getType());
            }
        }

        // Reset all attributes
        if (isAttributeEnabled)
        {
            if (isVersionAtLeast(9)) {
//                String[] attributes = AttributeList.entries_pre_21_3;
//                if (VersionDetector.isVersionAtLeast(21, 3)) {
//                    attributes = AttributeList.entries_after_21_3;
//                }
                Map<String, Double> defaults = new HashMap<>(default_pre_21_3);
                if (isVersionAtLeast(21, 3)) {
                    defaults = new HashMap<>(default_after_21_3);
                }
                int fail_count = 0;
                for (Map.Entry<String, Double> entry : defaults.entrySet()) {
                    try {
                        setAttribute(target, entry.getKey(), entry.getValue());
                    }
                    catch (IllegalArgumentException ignored) {
                        fail_count++;
                    }
                }
                if (fail_count > 0 ) parent.getLogger().info(fail_count + "attribute(s) doesn't exist on current version. Ignored.");;
//                setAttribute(target, "GENERIC_ARMOR", 0);
//                setAttribute(target, "GENERIC_ARMOR_TOUGHNESS", 0);
//                setAttribute(target, "GENERIC_ATTACK_DAMAGE", 1);
//                setAttribute(target, "GENERIC_ATTACK_KNOCKBACK", 0);
//                setAttribute(target, "GENERIC_ATTACK_SPEED", 4);
//                setAttribute(target, "GENERIC_BURNING_TIME", 1);
//                setAttribute(target, "GENERIC_EXPLOSION_KNOCKBACK_RESISTANCE", 0);
//                setAttribute(target, "GENERIC_FALL_DAMAGE_MULTIPLIER", 1);
//                setAttribute(target, "GENERIC_GRAVITY", 0.08);
//                setAttribute(target, "GENERIC_JUMP_STRENGTH", 0.42);
//                setAttribute(target, "GENERIC_KNOCKBACK_RESISTANCE", 0);
//                setAttribute(target, "GENERIC_LUCK", 0);
//                setAttribute(target, "GENERIC_MAX_ABSORPTION", 0);
//                setAttribute(target, "GENERIC_MOVEMENT_SPEED", 0.1);
//                setAttribute(target, "GENERIC_OXYGEN_BONUS", 0);
//                setAttribute(target, "GENERIC_SAFE_FALL_DISTANCE", 3);
//                setAttribute(target, "GENERIC_SCALE", 1);
//                setAttribute(target, "GENERIC_STEP_HEIGHT", 0.6);
//                setAttribute(target, "GENERIC_WATER_MOVEMENT_EFFICIENCY", 0);
//                setAttribute(target, "PLAYER_BLOCK_BREAK_SPEED", 1);
//                setAttribute(target, "PLAYER_BLOCK_INTERACTION_RANGE", 4.5);
//                setAttribute(target, "PLAYER_ENTITY_INTERACTION_RANGE", 3);
//                setAttribute(target, "PLAYER_MINING_EFFICIENCY", 0);
//                setAttribute(target, "PLAYER_SNEAKING_SPEED", 0.3);
//                setAttribute(target, "PLAYER_SUBMERGED_MINING_SPEED", 0.2);
//                setAttribute(target, "PLAYER_SWEEPING_DAMAGE_RATIO", 0);
            }
            else {
                // TODO: 在1.9以下恢复属性
                parent.getLogger().warning("Can't modify attributes on version below 1.9 yet!");
                isPartialFailed = true;
            }
        }

        // Stop flying
        if (isFlyEnabled)
        {
            target.setAllowFlight(false);
            target.setFlying(false);
        }

        target.saveData();
        return isPartialFailed;
    }

    /**
     * Set an attribute back to a given value.
     * @param target targeted Attributable instance.
     * @param name Attribute's name to reset.
     * @param value value to set.
     * */
    private void setAttribute(Attributable target, String name, double value) throws IllegalArgumentException {
        Attribute attribute = null;
        attribute = Attribute.valueOf(name);
        AttributeInstance attributeInstance = target.getAttribute(attribute);
        if (attributeInstance != null) {
            attributeInstance.setBaseValue(value);
        }
        else {
            parent.getLogger().info("Attribute " + name + "doesn't exist on current version. Ignored.");
            throw new IllegalArgumentException("");
        }
    }
}
