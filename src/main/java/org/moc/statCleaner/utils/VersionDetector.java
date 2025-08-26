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
