package org.moc.statCleaner.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.Collection;

public class CommandReset implements CommandExecutor {
    /**
     * Execute when command `/statreset` is performed.
     * @param args command arguments
     * @return true if command is properly execute, false otherwise.
     * */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Refuse if no permission
        if (!sender.hasPermission("statcleaner.reset")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission!");
            return false;
        }
        Player target = Bukkit.getPlayer(args[0]);
        // Abort if player doesn't exist
        if (target == null) {
            sender.sendMessage(ChatColor.RED + "This player doesn't exist! ");
            return false;
        }
        try {
            resetStat(target);
        }
        catch (RuntimeException e) {
            // TODO: Send error to console
            sender.sendMessage(ChatColor.RED + "An unexpected error happened. Detailed information: " + e.getMessage());
        }
        sender.sendMessage(ChatColor.GREEN + "Successfully reset " + target.getName() + "'s stat. ");
        return true;
    }

    /**
     * Reset a player's stat.
     * @param target targeted Player instance.
     * */
    private void resetStat(Player target) throws RuntimeException {
        // Reset health and hunger
        AttributeInstance target_max_health = target.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        if (target_max_health != null) {
            target_max_health.setBaseValue(20);
            target.setHealth(target_max_health.getValue());
        }
        else {
            throw new RuntimeException("Can't get player's max health!");
        }
        target.setFoodLevel(20);
        target.setSaturation(5.0f);

        // Clear potion effects
        Collection<PotionEffect> active_effects = target.getActivePotionEffects();
        for (PotionEffect effect : active_effects) {
            target.removePotionEffect(effect.getType());
        }

        // Reset all attributes
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

        // Stop flying
        target.setAllowFlight(false);
        target.setFlying(false);
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
