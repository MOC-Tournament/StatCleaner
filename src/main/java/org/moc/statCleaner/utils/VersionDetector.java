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

import org.bukkit.Bukkit;

public class VersionDetector {
    public static boolean isVersionAtLeast(int major) {
        String version = Bukkit.getBukkitVersion().split("-")[0];
        String[] parts = version.split("\\.");
        if (Integer.parseInt(parts[0]) > 1) return true;
        return Integer.parseInt(parts[1]) >= major;
    }

    public static boolean isVersionAtLeast(int major, int minor) {
        String version = Bukkit.getBukkitVersion().split("-")[0];
        String[] parts = version.split("\\.");
        if (parts[2] == null) parts[2] = "0";
        // 可能的2.0版本
        if (Integer.parseInt(parts[0]) > 1) return true;
        // 大版本不相同比大版本
        if (Integer.parseInt(parts[1]) != major) {
            return Integer.parseInt(parts[1]) > major;
        }
        // 大版本相同比小版本
        else {
            return Integer.parseInt(parts[2]) > minor;
        }
    }

    public static int getMajorVersion() {
        String version = Bukkit.getBukkitVersion().split("-")[0];
        String[] parts = version.split("\\.");
        return Integer.parseInt(parts[1]);
    }

    public static int getMinorVersion() {
        String version = Bukkit.getBukkitVersion().split("-")[0];
        String[] parts = version.split("\\.");
        if (parts[2] == null) return 0;
        return Integer.parseInt(parts[2]);
    }
}
