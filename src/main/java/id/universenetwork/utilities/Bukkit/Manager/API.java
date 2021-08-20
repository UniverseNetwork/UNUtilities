package id.universenetwork.utilities.Bukkit.Manager;

import org.bukkit.Bukkit;

import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;

public class API {
    public static String nmsver;
    public static boolean useOldMethods = false;

    public static void ActionBarAPISetup() {
        System.out.println(prefix + " §6Preparing ActionBarAPI...");
        nmsver = Bukkit.getServer().getClass().getPackage().getName();
        nmsver = nmsver.substring(nmsver.lastIndexOf(".") + 1);
        if (nmsver.equalsIgnoreCase("v1_8_R1") || nmsver.startsWith("v1_7_")) {
            useOldMethods = true;
        }
        System.out.println(prefix + " §bActionBarAPI have been prepared");
    }
}
