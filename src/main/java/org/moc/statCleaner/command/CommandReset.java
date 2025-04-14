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
import java.util.List;
import java.util.logging.Level;

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
            for (Player target : targets) {
                resetStat(target);
                playersOutput.append(target.getName()).append(",");
            }
            playersOutput.deleteCharAt(playersOutput.length() - 1);
            sender.sendMessage(parent.getMessageManager().getMessages("success.stat-cleaned", playersOutput.toString()));
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
    private void resetStat(Player target) throws RuntimeException {
        // Read config
        FileConfiguration config = this.parent.getConfig();
        boolean isHealthEnabled = config.getBoolean("reset.health");
        boolean isFoodEnabled = config.getBoolean("reset.food");
        boolean isEffectEnabled = config.getBoolean("reset.effect");
        boolean isAttributeEnabled = config.getBoolean("reset.attribute");
        boolean isFlyEnabled = config.getBoolean("reset.fly");

        // Reset health
        if (isHealthEnabled)
        {
            AttributeInstance targetMaxHealth = target.getAttribute(Attribute.GENERIC_MAX_HEALTH);
            if (targetMaxHealth != null) {
                targetMaxHealth.setBaseValue(20);
                target.setHealth(targetMaxHealth.getValue());
            } else {
                throw new RuntimeException("Can't get player's max health!");
            }
        }

        // Reset food
        if (isFoodEnabled)
        {
            target.setFoodLevel(20);
            target.setSaturation(5.0f);
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
            setAttribute(target, Attribute.GENERIC_ARMOR, 0);
            setAttribute(target, Attribute.GENERIC_ARMOR_TOUGHNESS, 0);
            setAttribute(target, Attribute.GENERIC_ATTACK_DAMAGE, 1);
            setAttribute(target, Attribute.GENERIC_ATTACK_KNOCKBACK, 0);
            setAttribute(target, Attribute.GENERIC_ATTACK_SPEED, 4);
            setAttribute(target, Attribute.GENERIC_BURNING_TIME, 1);
            setAttribute(target, Attribute.GENERIC_EXPLOSION_KNOCKBACK_RESISTANCE, 0);
            setAttribute(target, Attribute.GENERIC_FALL_DAMAGE_MULTIPLIER, 1);
            setAttribute(target, Attribute.GENERIC_GRAVITY, 0.08);
            setAttribute(target, Attribute.GENERIC_JUMP_STRENGTH, 0.42);
            setAttribute(target, Attribute.GENERIC_KNOCKBACK_RESISTANCE, 0);
            setAttribute(target, Attribute.GENERIC_LUCK, 0);
            setAttribute(target, Attribute.GENERIC_MAX_ABSORPTION, 0);
            setAttribute(target, Attribute.GENERIC_MOVEMENT_SPEED, 0.1);
            setAttribute(target, Attribute.GENERIC_OXYGEN_BONUS, 0);
            setAttribute(target, Attribute.GENERIC_SAFE_FALL_DISTANCE, 3);
            setAttribute(target, Attribute.GENERIC_SCALE, 1);
            setAttribute(target, Attribute.GENERIC_STEP_HEIGHT, 0.6);
            setAttribute(target, Attribute.GENERIC_WATER_MOVEMENT_EFFICIENCY, 0);
            setAttribute(target, Attribute.PLAYER_BLOCK_BREAK_SPEED, 1);
            setAttribute(target, Attribute.PLAYER_BLOCK_INTERACTION_RANGE, 4.5);
            setAttribute(target, Attribute.PLAYER_ENTITY_INTERACTION_RANGE, 3);
            setAttribute(target, Attribute.PLAYER_MINING_EFFICIENCY, 0);
            setAttribute(target, Attribute.PLAYER_SNEAKING_SPEED, 0.3);
            setAttribute(target, Attribute.PLAYER_SUBMERGED_MINING_SPEED, 0.2);
            setAttribute(target, Attribute.PLAYER_SWEEPING_DAMAGE_RATIO, 0);
        }

        // Stop flying
        if (isFlyEnabled)
        {
            target.setAllowFlight(false);
            target.setFlying(false);
        }
    }

    /**
     * Set an attribute back to a given value.
     * @param target targeted Attributable instance.
     * @param attribute Attribute to reset.
     * @param value value to set.
     * */
    private void setAttribute(Attributable target, Attribute attribute, double value) {
        AttributeInstance attributeInstance = target.getAttribute(attribute);
        if (attributeInstance != null) {
            attributeInstance.setBaseValue(value);
        }
    }
}
