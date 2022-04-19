package id.universenetwork.utilities.Bukkit.Utils;

import java.net.URL;
import java.net.URLConnection;

public class SFUpdateChecker {
    final String d;
    final int b;

    public SFUpdateChecker(String name, int build) {
        try {
            URLConnection u = new URL("https://thebusybiscuit.github.io/builds/" + name + "/builds.json").openConnection();
            u.addRequestProperty("User-Agent", "Auto Updater (by TheBusyBiscuit)");
            u.setDoOutput(true);
        }
        b = build;
    }
}