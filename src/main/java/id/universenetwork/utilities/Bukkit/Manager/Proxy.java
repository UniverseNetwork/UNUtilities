package id.universenetwork.utilities.Bukkit.Manager;

import id.universenetwork.utilities.Bukkit.Enums.Features.AddressWhitelister;

import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;

public class Proxy {
    private static String bungeeAddress;

    public static void setup() {
        System.out.println(prefix + " §ePreparing Proxy Manager...");
        setBungeeAddress(Config.AWMessage(AddressWhitelister.ADDRESS));
        System.out.println(prefix + " §aProxy Manager have been prepared");
    }

    public static void reload() {
        System.out.println(prefix + " §eReloading Proxy Manager...");
        setBungeeAddress(Config.AWMessage(AddressWhitelister.ADDRESS));
        System.out.println(prefix + " §aProxy Manager has been reloaded");
    }

    public static void setBungeeAddress(String bungeeAddress) {
        Proxy.bungeeAddress = bungeeAddress;
    }

    public static String getBungeeAddress() {
        return bungeeAddress;
    }

    public static Boolean isValidAddress(String s) {
        if (!s.contains(".")) {
            return false;
        } else {
            String[] v1 = s.split(".");
            int v2 = v1.length;
            for (int v3 = 0; v3 < v2; ++v3) {
                String n = v1[v3];
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
