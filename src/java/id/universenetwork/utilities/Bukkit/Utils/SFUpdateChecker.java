package id.universenetwork.utilities.Bukkit.Utils;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class SFUpdateChecker {
    final String d;
    final int b;

    public SFUpdateChecker(String name, String d, int build) throws IOException {
        this.d = d;
        URLConnection u = new URL("https://thebusybiscuit.github.io/builds/" + name + "/builds.json").openConnection();
        u.addRequestProperty("User-Agent", "Auto Updater (by TheBusyBiscuit)");
        u.setDoOutput(true);
        b = build;
    }
}