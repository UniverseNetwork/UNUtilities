package id.universenetwork.utilities.bukkit.utils;

import lombok.var;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Version implements Comparable<Version> {
    final int major;
    final int minor;
    final int build;

    public Version(int major, int minor, int build) {
        this.major = major;
        this.minor = minor;
        this.build = build;
    }

    public int getMajor() {
        return major;
    }

    public int getMinor() {
        return minor;
    }

    public int getBuild() {
        return build;
    }

    @Override
    public int compareTo(@NotNull Version other) {
        int majorResult = Integer.compare(major, other.major);
        if (majorResult == 0) return Integer.compare(minor, other.minor);
        else return majorResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Version version = (Version) o;
        return major == version.major && minor == version.minor && build == version.build;
    }

    @Override
    public int hashCode() {
        return Objects.hash(major, minor, build);
    }

    @Override
    public String toString() {
        return "Version{" + "major=" + major + ", minor=" + minor + ", build=" + build + '}';
    }

    @Nullable
    public static Version parseVersion(@NotNull final String bukkitVersion) {
        final Pattern versionPattern = Pattern.compile("^(?<major>\\d+)(\\.(?<minor>\\d+)(\\.(?<build>\\d)+)?)?.*");
        final Matcher matcher = versionPattern.matcher(bukkitVersion);
        if (matcher.find()) {
            var majorString = matcher.group("major");
            var major = Integer.parseInt(majorString);
            var minorString = matcher.group("minor");
            var minor = minorString != null ? Integer.parseInt(minorString) : 0;
            var buildString = matcher.group("build");
            var build = buildString != null ? Integer.parseInt(buildString) : 0;
            return new Version(major, minor, build);
        } else return null;
    }
}