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

import org.bukkit.attribute.Attribute;

@Deprecated
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

