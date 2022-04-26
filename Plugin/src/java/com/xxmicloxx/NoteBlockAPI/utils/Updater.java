package com.xxmicloxx.NoteBlockAPI.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Updater {
    public static boolean checkUpdate(String resource, String actualVersion) throws IOException {
        boolean snapshot = false;
        if (actualVersion.contains("-SNAPSHOT")) {
            snapshot = true;
            actualVersion = actualVersion.replace("-SNAPSHOT", "");
        }
        Float version = getVersionNumber(actualVersion);
        URLConnection con = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + resource).openConnection();
        String newVersionString = new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
        int first = newVersionString.indexOf("(");
        String newVersion = first == -1 ? newVersionString : newVersionString.substring(0, first);
        Float newVer = getVersionNumber(newVersion);
        return snapshot ? newVer >= version : newVer > version;
    }

    static Float getVersionNumber(String version) {
        String[] versionParts = version.split("\\.");
        StringBuilder versionString = new StringBuilder("0.");
        for (String vpart : versionParts) {
            if (vpart.length() < 2) versionString.append("0");
            versionString.append(vpart);
        }
        return Float.parseFloat(versionString.toString());
    }
}