package org.moc.statCleaner.utils;

import org.bukkit.attribute.Attribute;

public class AttributeEntry {
    private Attribute attr;
    private int start_ver;
    private int start_ver_minor = 0;
    private boolean is_deprecated = false;
    private int end_ver = -1;
    private int end_ver_minor = -1;

    public AttributeEntry(Attribute attribute, double start_vers){
        this.attr = attribute;
        this.start_ver = (int) start_vers;
        this.start_ver_minor = (int)((start_vers % 1) * 10);
    }

    public AttributeEntry(Attribute attribute, double start_vers, double end_vers) {
        this.attr = attribute;
        this.start_ver = (int) start_vers;
        this.start_ver_minor = (int)((start_vers % 1) * 10);
        this.is_deprecated = true;
        this.end_ver = (int) end_vers;
        this.end_ver_minor = (int)((end_vers % 1) * 10);
    }

    public boolean is_valid() {
        return VersionDetector.isVersionAtLeast(start_ver, start_ver_minor) && (!is_deprecated || VersionDetector.getMajorVersion() <= end_ver);
    }
}

