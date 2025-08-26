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

package org.moc.statCleaner.utils;

import java.util.HashMap;
import java.util.Map;

public record AttributeList() {
    @Deprecated
    public static String[] entries_pre_21_3 = { // Before 1.21.3
            "GENERIC_ARMOR", // 1.9
            "GENERIC_ARMOR_TOUGHNESS", // 1.12
            "GENERIC_ATTACK_DAMAGE", // 1.9
            "GENERIC_ATTACK_KNOCKBACK", // 1.16.1
            "GENERIC_ATTACK_SPEED", // 1.9
            "GENERIC_BURNING_TIME", // 1.21
            "GENERIC_EXPLOSION_KNOCKBACK_RESISTANCE", // 1.21
            "GENERIC_FALL_DAMAGE_MULTIPLIER", // 1.20.5
            "GENERIC_FLYING_SPEED", // 1.12
            "GENERIC_FOLLOW_RANGE", // 1.9
            "GENERIC_GRAVITY", // 1.20.5
            "GENERIC_JUMP_STRENGTH", // 1.20.5
            "GENERIC_KNOCKBACK_RESISTANCE", // 1.9
            "GENERIC_LUCK", // 1.9
            "GENERIC_MAX_ABSORPTION", // 1.20.2
            "GENERIC_MAX_HEALTH", // 1.9
            "GENERIC_MOVEMENT_EFFICIENCY", // 1.21
            "GENERIC_MOVEMENT_SPEED", // 1.9
            "GENERIC_OXYGEN_BONUS", // 1.21
            "GENERIC_SAFE_FALL_DISTANCE", // 1.20.5
            "GENERIC_SCALE", // 1.20.5
            "GENERIC_STEP_HEIGHT", // 1.20.5
            "GENERIC_TEMPT_RANGE", // 1.21.2
            "GENERIC_WATER_MOVEMENT_EFFICIENCY", // 1.21
            "PLAYER_BLOCK_BREAK_SPEED", // 1.20.5
            "PLAYER_BLOCK_INTERACTION_RANGE", // 1.20.5
            "PLAYER_ENTITY_INTERACTION_RANGE", // 1.20.5
            "PLAYER_MINING_EFFICIENCY", // 1.21
            "PLAYER_SNEAKING_SPEED", // 1.21
            "PLAYER_SUBMERGED_MINING_SPEED", // 1.21
            "PLAYER_SWEEPING_DAMAGE_RATIO", // 1.21
            "HORSE_JUMP_STRENGTH", // 1.9 - 1.20.4
            "ZOMBIE_SPAWN_REINFORCEMENTS" // 1.9
    };
    public static Map<String, Double> default_pre_21_3 = new HashMap<>() {{
        put("GENERIC_ARMOR",  0d); // 1.9
        put("GENERIC_ARMOR_TOUGHNESS",  0d); // 1.12
        put("GENERIC_ATTACK_DAMAGE",  1d); // 1.9
        put("GENERIC_ATTACK_KNOCKBACK",  0d);
        put("GENERIC_ATTACK_SPEED",  4d); // 1.9
        put("GENERIC_BURNING_TIME",  1d);
        put("GENERIC_EXPLOSION_KNOCKBACK_RESISTANCE",  0d);
        put("GENERIC_FALL_DAMAGE_MULTIPLIER",  1d);
        put("GENERIC_FLYING_SPEED", 0.4d);
        put("GENERIC_GRAVITY",  0.08d);
        put("GENERIC_JUMP_STRENGTH",  0.42d);
        put("GENERIC_KNOCKBACK_RESISTANCE",  0d); // 1.9
        put("GENERIC_LUCK",  0d); // 1.9
        put("GENERIC_MAX_ABSORPTION",  0d);
        put("GENERIC_MOVEMENT_EFFICIENCY",  0d);
        put("GENERIC_MOVEMENT_SPEED",  0.1d); // 1.9
        put("GENERIC_OXYGEN_BONUS",  0d);
        put("GENERIC_SAFE_FALL_DISTANCE",  3d);
        put("GENERIC_SCALE",  1d);
        put("GENERIC_STEP_HEIGHT",  0.6d);
        put("GENERIC_WATER_MOVEMENT_EFFICIENCY",  0d);
        put("PLAYER_BLOCK_BREAK_SPEED",  1d);
        put("PLAYER_BLOCK_INTERACTION_RANGE",  4.5d);
        put("PLAYER_ENTITY_INTERACTION_RANGE",  3d);
        put("PLAYER_MINING_EFFICIENCY",  0d);
        put("PLAYER_SNEAKING_SPEED",  0.3d);
        put("PLAYER_SUBMERGED_MINING_SPEED",  0.2d);
        put("PLAYER_SWEEPING_DAMAGE_RATIO",  0d);
    }};
    @Deprecated
    public static String[] entries_after_21_3 = {
            "ARMOR", // 1.9
            "ARMOR_TOUGHNESS", // 1.12
            "ATTACK_DAMAGE", // 1.9
            "ATTACK_KNOCKBACK", // 1.16.1
            "ATTACK_SPEED", // 1.9
            "BLOCK_BREAK_SPEED", // 1.20.5
            "BLOCK_INTERACTION_RANGE", // 1.20.5
            "BURNING_TIME", // 1.21
            "CAMERA_DISTANCE", // 1.21.6
            "ENTITY_INTERACTION_RANGE", // 1.20.5
            "EXPLOSION_KNOCKBACK_RESISTANCE", // 1.21
            "FALL_DAMAGE_MULTIPLIER", // 1.20.5
            "FLYING_SPEED", // 1.12
            "FOLLOW_RANGE", // 1.9
            "GRAVITY", // 1.20.5
            "JUMP_STRENGTH", // 1.20.5
            "KNOCKBACK_RESISTANCE", // 1.9
            "LUCK", // 1.9
            "MAX_ABSORPTION", // 1.20.2
            "MAX_HEALTH", // 1.9
            "MINING_EFFICIENCY", // 1.21
            "MOVEMENT_EFFICIENCY", // 1.21
            "MOVEMENT_SPEED", // 1.9
            "OXYGEN_BONUS", // 1.21
            "SAFE_FALL_DISTANCE", // 1.20.5
            "SCALE", // 1.20.5
            "SNEAKING_SPEED", // 1.21
            "SPAWN_REINFORCEMENTS", // 1.9
            "STEP_HEIGHT", // 1.20.5
            "SUBMERGED_MINING_SPEED", // 1.21
            "SWEEPING_DAMAGE_RATIO", // 1.21
            "TEMPT_RANGE", // 1.21.2
            "WATER_MOVEMENT_EFFICIENCY", // 1.21
            "WAYPOINT_RECEIVE_RANGE", // 1.21.6
            "WAYPOINT_TRANSMIT_RANGE" // 1.21.6
    };
    public static Map<String, Double> default_after_21_3 = new HashMap<>() {{
        put("ARMOR",  0d); // 1.9
        put("ARMOR_TOUGHNESS",  0d); // 1.12
        put("ATTACK_DAMAGE",  1d); // 1.9
        put("ATTACK_KNOCKBACK",  0d);
        put("ATTACK_SPEED",  4d); // 1.9
        put("BURNING_TIME",  1d);
        put("EXPLOSION_KNOCKBACK_RESISTANCE",  0d);
        put("FALL_DAMAGE_MULTIPLIER",  1d);
        put("FLYING_SPEED", 0.4d);
        put("GRAVITY",  0.08d);
        put("JUMP_STRENGTH",  0.42d);
        put("KNOCKBACK_RESISTANCE",  0d); // 1.9
        put("LUCK",  0d); // 1.9
        put("MAX_ABSORPTION",  0d);
        put("MOVEMENT_EFFICIENCY",  0d);
        put("MOVEMENT_SPEED",  0.1d); // 1.9
        put("OXYGEN_BONUS",  0d);
        put("SAFE_FALL_DISTANCE",  3d);
        put("SCALE",  1d);
        put("STEP_HEIGHT",  0.6d);
        put("WATER_MOVEMENT_EFFICIENCY",  0d);
        put("BLOCK_BREAK_SPEED",  1d);
        put("BLOCK_INTERACTION_RANGE",  4.5d);
        put("ENTITY_INTERACTION_RANGE",  3d);
        put("MINING_EFFICIENCY",  0d);
        put("SNEAKING_SPEED",  0.3d);
        put("SUBMERGED_MINING_SPEED",  0.2d);
        put("SWEEPING_DAMAGE_RATIO",  0d);
        put("CAMERA_DISTANCE", 0.4d);
        put("WAYPOINT_RECEIVE_RANGE", 60000000d);
        put("WAYPOINT_TRANSMIT_RANGE", 60000000d);
    }};
}
