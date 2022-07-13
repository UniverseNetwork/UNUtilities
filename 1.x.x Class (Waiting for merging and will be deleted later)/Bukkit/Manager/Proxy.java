package id.universenetwork.utilities.bukkit.manager;

import static id.universenetwork.utilities.bukkit.Enums.AddressWhitelister.ADDRESS;
import static id.universenetwork.utilities.bukkit.UNUtilities.prefix;

public class Proxy {
    static String bungeeAddress;

    public static void setup() {
        System.out.println(prefix + " §ePreparing Proxy Manager...");
        setBungeeAddress(Config.AWMessage(ADDRESS));
        System.out.println(prefix + " §aProxy Manager have been prepared");
    }

    public static void reload() {
        System.out.println(prefix + " §eReloading Proxy Manager...");
        setBungeeAddress(Config.AWMessage(ADDRESS));
        System.out.println(prefix + " §aProxy Manager has been reloaded");
    }

    public static void setBungeeAddress(String bungeeAddress) {
        Proxy.bungeeAddress = bungeeAddress;
    }

    public static String getBungeeAddress() {
        return bungeeAddress;
    }

    public static Boolean isValidAddress(String s) {
        if (!s.contains(".")) return false;
        else {
            String[] v1 = s.split("\\.");
            int v2 = v1.length;
            for (String n : v1) {
                try {
                    Integer.valueOf(n);
                } catch (Exception v6) {
                    return false;
                }
            }
        }
        return true;
    }
}
